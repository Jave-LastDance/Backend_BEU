package com.beu.apigateway.controller;

import com.beu.apigateway.entity.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController

@RequestMapping("/auth")
public class LoginControler {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/login")
    public ResponseEntity<?> postnewRating(@RequestBody LoginRequest request) {
        try {
        String url = "http://registerservice/auth/login"; // Reemplaza 'puerto' con el puerto de tu aplicación

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(request, headers);


        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println(response.getBody());
        return ResponseEntity.ok(response.getBody());


        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Error de autenticacion", HttpStatus.OK);
        }
    }


}
