package com.beu.apigateway.controller;

import com.beu.apigateway.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/eventosPUJ")
public class EventsController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/eventos")
    public String getallevents() {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/eventos", String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }


    @GetMapping("/evento/{idEvent}")
    public String getEventById(@PathVariable int idEvent) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/" + idEvent, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //http://localhost:8081/eventosPUJ/evento/centro/{{nameCenter}}
    @GetMapping("/evento/centro/{nameCenter}")
    public String getEventById(@PathVariable String nameCenter) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/centro/" + nameCenter, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //http://localhost:8081/eventosPUJ/beacon/{{id_beacon}}
    @GetMapping("/beacon/{id_beacon}")
    public String getEventByBeaconId(@PathVariable Integer id_beacon) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/beacon/" + id_beacon, String.class);
        } catch (Exception exeption) {
            throw new RuntimeException("Este es un error personalizado.");
        }


    }

    //http://localhost:8081/eventosPUJ/evento/estado/centro/{{status}}/{{nameCenter}}
    @GetMapping("/evento/estado/centro/{status}/{nameCenter}")
    public String getEventByStateCenter(@PathVariable String status, @PathVariable String nameCenter) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/estado/centro/" + status + "/" + nameCenter, String.class);
        } catch (Exception exepcion) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //http://localhost:8081/eventosPUJ/evento/estado/{{idEvent}}
    @PutMapping("/evento/estado/centro/{idEvent}")
    public String updateevent(@PathVariable Integer idEvent) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/estado/" + idEvent, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }

    }

    //http://localhost:8081/eventosPUJ/evento/{{idEvent}}
    @DeleteMapping("/evento/estado/centro/{idEvent}")
    public String getEventByDateStart5(@PathVariable Integer idEvent) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/" + idEvent, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //http://localhost:8081/eventosPUJ/evento/rating
    @PostMapping("/evento/nuevo/rating")
    public ResponseEntity<String> postnewRating(@RequestBody ratingevent ratingEvent) {
        String url = "http://eventosCRUD/eventosPUJ/ratingPUJ/evento/rating";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, ratingEvent, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Ya calificaste el evento. Puedes modificar la calificacion", HttpStatus.OK);
        }
    }


    //    POST http://localhost:8081/eventosPUJ/comentarioPUJ/comentario/evento
//
//
//    PUT http://localhost:8081/eventosPUJ/comentarioPUJ/comentario/evento
    @PutMapping("/comentario/actualizar")
    public ResponseEntity<String> putComentario(@RequestBody commentevent cooment) {
        String url = "http://eventosCRUD/eventosPUJ/comentarioPUJ/comentario/evento";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<commentevent> requestEntity = new HttpEntity<>(cooment, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("comentarios actualizadso correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Verifique los datos del comentario", HttpStatus.OK);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("comentario no actualizado", HttpStatus.OK);
        }
    }

    //    PUT http://localhost:8081/eventosPUJ/ratingPUJ/evento/rating
    @PutMapping("/rating/actualizar")
    public ResponseEntity<String> putRating(@RequestBody ratingevent ratingEvent) {
        String url = "http://eventosCRUD/eventosPUJ/ratingPUJ/evento/rating";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<ratingevent> requestEntity = new HttpEntity<>(ratingEvent, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("comentarios actualizadso correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Verifique los datos del comentario", HttpStatus.OK);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("comentario no actualizado", HttpStatus.OK);
        }
    }

    //    GET http://localhost:8081/eventosPUJ/ratingPUJ/evento/usuario/{{idUser}}/{{idEvent}}
    @GetMapping("/evento/usuario/{idUser}/{idEvent}")
    public ResponseEntity<?> getRatingEventByUser(@PathVariable Integer idUser, @PathVariable Integer idEvent) {
        try {
            String response = restTemplate.getForObject("http://eventosCRUD/eventosPUJ/ratingPUJ/evento/usuario/" + idUser + "/" + idEvent, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception exepcion) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //    GET http://localhost:8081/eventosPUJ/eventos/activos
    @GetMapping("/evento/activos")
    public ResponseEntity<?> getEventByUser() {
        try {
            String response = restTemplate.getForObject("http://eventosCRUD/eventosPUJ/eventos/activos", String.class);
            return ResponseEntity.ok(response);
        } catch (Exception exepcion) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }

    //    POST http://localhost:8081/eventosPUJ/evento
    @PostMapping("/evento/nuevo")
    public ResponseEntity<String> postnewEvent(@RequestBody event newEvent) {
        String url = "http://eventosCRUD/eventosPUJ/evento";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, newEvent, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Verifique los datos del evento", HttpStatus.OK);
        }
    }

    //    PUT http://localhost:8081/eventosPUJ/evento
    @PutMapping("/evento/actualizar")
    public ResponseEntity<String> putnewEvent(@RequestBody event newEvent) {
        String url = "http://eventosCRUD/eventosPUJ/evento";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<event> requestEntity = new HttpEntity<>(newEvent, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("Evento actualizado correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Verifique los datos del evento", HttpStatus.OK);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("evento incorrecto", HttpStatus.OK);
        }


    }
//
//
//    DELETE http://localhost:8081/eventosPUJ/evento/{{idEvent}}
//
//
//    PUT http://localhost:8081/eventosPUJ/actividadesPUJ/actividades
@PutMapping("/actividad/actualizar")
public ResponseEntity<String> putActivity(@RequestBody activity activi) {
    String url = "http://eventosCRUD/eventosPUJ/evento";
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<activity> requestEntity = new HttpEntity<>(activi, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>("actividad actualizado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verifique los datos de la actividad", HttpStatus.OK);
        }

    } catch (HttpClientErrorException ex) {
        return new ResponseEntity<>("actividad incorrecto", HttpStatus.OK);
    }


}

//    POST http://localhost:8081/eventosPUJ/actividadesPUJ/actividad
@PostMapping("/actividad/nuevo")
public ResponseEntity<String> postnewEvent(@RequestBody activity activi) {
    String url = "http://eventosCRUD/eventosPUJ/actividadesPUJ/actividad";
    try {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, activi, String.class);
        return responseEntity;
    } catch (HttpClientErrorException ex) {
        return new ResponseEntity<>("Verifique los datos del evento", HttpStatus.OK);
    }
}
//
//
//    POST http://localhost:8081/eventosPUJ/actividadesPUJ/actividades
//
//
//    DELETE http://localhost:8081/eventosPUJ/actividadesPUJ/actividad/{{idActivity}}
}
