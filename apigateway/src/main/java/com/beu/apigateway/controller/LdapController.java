package com.beu.apigateway.controller;

import com.beu.apigateway.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;



import java.util.List;

@RestController
public class LdapController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String cuenta, @RequestParam String passw) {
        ObjectMapper objectMapper = new ObjectMapper();
        String response = restTemplate.getForObject("http://authusers/login?cuenta={cuenta}&pass={passw}", String.class, cuenta, passw);

        try {
            List<Person> persons = objectMapper.readValue(response, new TypeReference<List<Person>>() {});
            return ResponseEntity.ok(persons);
        } catch (JsonProcessingException e) {

            return ResponseEntity.ok(response);
        }
    }

}

