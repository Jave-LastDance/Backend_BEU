package com.beu.apigateway.controller;


import com.beu.apigateway.entity.preference;
import com.beu.apigateway.entity.preferencexuser;
import com.beu.apigateway.entity.priorityxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/personalizacionPUJ")
public class PersonalizationController {
    @Autowired
    RestTemplate restTemplate;


    //LIST OF USER PREFERENCES
    @GetMapping("/preferencias/usuario/{idUser}")
    public ResponseEntity<List<preferencexuser>>getByPreferenceUser(@PathVariable String idUser) {
        String url = "http://Personalization/personalizacionPUJ/usuario/preferencias/" + idUser;
        try {
            ResponseEntity<List<preferencexuser>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<preferencexuser>>() {}
            );
            return responseEntity;
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // or handle error response in another way
        }
    }

    @GetMapping("/preferencias/centro/{nameCenter}")
    public ResponseEntity<List<preference>>getByPreferenceNameCenter(@PathVariable String nameCenter) {
        String url = "http://Personalization/personalizacionPUJ/preferencias/" + nameCenter;
        try {
            ResponseEntity<List<preference>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<preference>>() {}
            );
            return responseEntity;
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // or handle error response in another way
        }
    }

   // ADD PRIORITY USER
    @PostMapping("/prioridad/usuario")
    public ResponseEntity<?> postPriority(@RequestBody List<priorityxuser> newPriority) {
        String url = "http://Personalization/personalizacionPUJ/prioridad/usuario";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, newPriority, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("error de creacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  POST PREFERENCE USER
    @PostMapping("/usuario/preferencias")
    public ResponseEntity<?> postPreference(@RequestBody  List<preferencexuser> newPreference) {
        String url = "http://Personalization/personalizacionPUJ/usuario/preferencias";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, newPreference, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("error de creacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //
//    UPDATE PRIORITY
    @PutMapping("/prioridad/usuario")
    public ResponseEntity<String> putPriority(@RequestBody  List<priorityxuser> updatePriority) {
        String url = "http://Personalization/personalizacionPUJ/prioridad/usuario";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<List<priorityxuser>> requestEntity = new HttpEntity<>(updatePriority, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // DELETE PREFERENCES
   @DeleteMapping("/usuario/preferencias")
   public ResponseEntity<?> deleteNotification(@RequestBody List<preferencexuser> deletePreference) {
       String url= "http://Personalization/personalizacionPUJ/usuario/preferencias";
       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Type", "application/json");
       HttpEntity<List<preferencexuser>> requestEntity = new HttpEntity<>(deletePreference, headers);
       try {
           ResponseEntity<String> responseEntity = restTemplate.exchange(
                   url,
                   HttpMethod.DELETE,
                   requestEntity,
                   String.class
           );
           return ResponseEntity.ok(responseEntity.getBody());

       } catch (Exception exception) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
       }
   }

}