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


    //http://localhost:8042/personalizacionPUJ/preferencias/{{nameCenter}}
    @GetMapping("/preferencias/{idUser}")
    public ResponseEntity<List<preferencexuser>>getByPreferenceNameCenter(@PathVariable String idUser) {
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

    //http://localhost:8042/personalizationPUJ/eventos/usuario/{{id_beacon}}/{{id_user}}
    @GetMapping("/eventos/usuario/{id_beacon}/{id_user}")
    public ResponseEntity<List<preferencexuser>>  getByPreferenceEventUser(@PathVariable int id_beacon, @PathVariable int id_user) {
        String url="http://Personalization/personalizacionPUJ/eventos/usuario/" + id_beacon + "/" + id_user;
        try {
            ResponseEntity<List<preferencexuser>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<preferencexuser>>() {}
            );
            return responseEntity;
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // POST http://localhost:8080/personalizacionPUJ/prioridad/usuario
    @PostMapping("/prioridad/usuario")
    public ResponseEntity<?> postPrioriodad(@RequestBody List<priorityxuser> newPriority) {
        String url = "http://Personalization/personalizacionPUJ/prioridad/usuario";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, newPriority, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("error de creacion", HttpStatus.OK);
        }
    }
    //
//
//    PUT http://localhost:8080/personalizacionPUJ/prioridad/usuario
    @PutMapping("/prioridad/usuario")
    public ResponseEntity<String> putComentario(@RequestBody  List<priorityxuser> updatePriority) {
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
                return new ResponseEntity<>("prioridad actualizadso correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Verifique los datos", HttpStatus.OK);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("comentario no actualizado", HttpStatus.OK);
        }
    }

    //  POST http://localhost:8080/personalizacionPUJ/usuario/preferencias
    @PostMapping("/usuario/preferencias")
    public ResponseEntity<?> postPreferencias(@RequestBody  List<preferencexuser> newPreference) {
        String url = "http://Personalization/personalizacionPUJ/usuario/preferencias";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, newPreference, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("error de creacion", HttpStatus.OK);
        }
    }


   // DELETE http://localhost:8080/personalizacionPUJ/usuario/preferencias
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