package com.beu.apigateway.controller;

import com.beu.apigateway.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/eventosPUJ")
public class EventsController {

    @Autowired
    RestTemplate restTemplate;

    //LIST OF ALL EVENTS
    @GetMapping("/eventos")
    public ResponseEntity<List<event>> getAllEvents() {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/eventos",
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {
                    }
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //OBJECT EVENT BY ID
    @GetMapping("/evento/{idEvent}")
    public ResponseEntity<event> getEventById(@PathVariable int idEvent) {
        try {
            event event = restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/" + idEvent, event.class);
            return ResponseEntity.ok(event);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //LIST OF EVENTS ACCORDING TO THE CENTER NAME
    @GetMapping("/evento/centro/{nameCenter}")
    public ResponseEntity<List<event>> getEventsByCenter(@PathVariable String nameCenter) {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/evento/centro/" + nameCenter,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {
                    }
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //LIST OF EVENTS ACCORDING TO THE LOCATION OF THE BEACON
    @GetMapping("/ubicacionbeacon/{location_beacon}")
    public ResponseEntity<List<event>> getEventsByBeaconId(@PathVariable String location_beacon) {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/beacon/" + location_beacon,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {
                    }
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //LIST OF EVENTS ACCORDING TO THE STATUS AND THE CENTER NAME
    @GetMapping("/evento/estado/centro/{status}/{nameCenter}")
    public ResponseEntity<List<event>> getEventsByStateCenter(
            @PathVariable String status,
            @PathVariable String nameCenter
    ) {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/evento/estado/centro/" + status + "/" + nameCenter,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {
                    }
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // RATING OF A USER GIVEN THE ID OF THE USER AND THE EVENT
    @GetMapping("/califacion/evento/usuario/{idUser}/{idEvent}")
    public ResponseEntity<ratingevent> getRatingEventByUser(
            @PathVariable Integer idUser,
            @PathVariable Integer idEvent
    ) {
        try {
            ratingevent ratingEvent = restTemplate.getForObject(
                    "http://eventosCRUD/eventosPUJ/ratingPUJ/evento/usuario/" + idUser + "/" + idEvent,
                    ratingevent.class
            );
            return ResponseEntity.ok(ratingEvent);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //   LIST OF EVENT WITH STATUS ACTIVO
    @GetMapping("/eventos/activos")
    public ResponseEntity<?> getEventByUser() {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/eventos/activos",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {
                    }
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // or handle error response in another way
        }

    }

    //LIST OF ACTIVITIES OF AN EVENT GIVEN THE ID OF THE EVENT
    @GetMapping("/actividad/{idEvent}")
    public ResponseEntity<List<activity>> getActivity(@PathVariable int idEvent) {
        String url = "http://eventosCRUD/eventosPUJ/actividadesPUJ/actividades/evento/" + idEvent;
        try {
            ResponseEntity<List<activity>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<activity>>() {
                    }
            );
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //ADD A NEW RATING GIVEN THE REQUEST BODY
    @PostMapping("/evento/rating")
    public ResponseEntity<String> postnewRating(@RequestBody ratingevent ratingEvent) {
        String url = "http://eventosCRUD/eventosPUJ/ratingPUJ/evento/rating";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, ratingEvent, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Existe", HttpStatus.BAD_REQUEST);
        }
    }


    // ADD NEW COMMENT
    @PostMapping("/evento/comentario")
    public ResponseEntity<?> postnewComment(@RequestBody commentevent comment) {
        String url = "http://eventosCRUD/eventosPUJ/comentarioPUJ/comentario/evento";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, comment, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Existe", HttpStatus.BAD_REQUEST);
        }
    }

    //    ADD NEW EVENT
    @PostMapping("/evento")
    public ResponseEntity<String> postnewEvent(@RequestBody event newEvent) {
        String url = "http://eventosCRUD/eventosPUJ/evento";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, newEvent, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
        }
    }

    //  ADD NEW ACTIVITY
    @PostMapping("/evento/actividad/")
    public ResponseEntity<String> postnewActivity(@RequestBody activity newActivity) {
        String url = "http://eventosCRUD/eventosPUJ/actividadesPUJ/actividad";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, newActivity, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
        }
    }

     //    UPDATE COMMENT
    @PutMapping("c")
    public ResponseEntity<String> putComment(@RequestBody commentevent updateComment) {
        String url = "http://eventosCRUD/eventosPUJ/comentarioPUJ/comentario/evento";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<commentevent> requestEntity = new HttpEntity<>(updateComment,headers);

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

    //    UPDATE RATING
    @PutMapping("/rating")
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
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE EVENT STATE
    @PutMapping("/evento/estado/centro/{idEvent}")
    public String updateEvent(@PathVariable Integer idEvent) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/estado/" + idEvent, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }

    }


    //    UPDATE EVENT
    @PutMapping("/evento")
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
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

 //  UPDATE ACTIVITY
    @PutMapping("/actividad")
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
                return new ResponseEntity<>("OK", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Datos", HttpStatus.BAD_REQUEST);
            }

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    //DELETE EVENT
    @DeleteMapping("/evento/{idEvent}")
    public String getEventByDateStart5(@PathVariable Integer idEvent) {
        try {
            return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/" + idEvent, String.class);
        } catch (Exception exception) {
            throw new RuntimeException("Este es un error personalizado.");
        }
    }
//
//
//    POST http://localhost:8081/eventosPUJ/actividadesPUJ/actividades
//
//
//    DELETE http://localhost:8081/eventosPUJ/actividadesPUJ/actividad/{{idActivity}}
}
