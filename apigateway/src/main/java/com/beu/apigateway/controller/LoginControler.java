package com.beu.apigateway.controller;

import com.beu.apigateway.entity.LoginRequest;

import com.beu.apigateway.entity.User;
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
    public ResponseEntity<User> postnewRating(@RequestBody LoginRequest request) {
        try {
        String url = "http://registerservice/auth/login"; // Reemplaza 'puerto' con el puerto de tu aplicación

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(request, headers);


        User response = restTemplate.postForEntity(url, requestEntity, User.class).getBody();

        return ResponseEntity.ok(response);


        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }




}
