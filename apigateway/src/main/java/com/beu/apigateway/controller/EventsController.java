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

    @GetMapping("/eventos")
    public ResponseEntity<List<event>> getAllEvents() {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/eventos",
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {}
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/evento/{idEvent}")
    public ResponseEntity<event> getEventById(@PathVariable int idEvent) {
        try {
            event event = restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/" + idEvent, event.class);
            return ResponseEntity.ok(event);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // or handle error response in another way
        }
    }

    //http://localhost:8081/eventosPUJ/evento/centro/{{nameCenter}}
    @GetMapping("/evento/centro/{nameCenter}")
    public ResponseEntity<List<event>> getEventsByCenter(@PathVariable String nameCenter) {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/evento/centro/" + nameCenter,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {}
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //http://localhost:8081/eventosPUJ/beacon/{{id_beacon}}
    @GetMapping("/beaconlocation/{location_beacon}")
    public ResponseEntity<List<event>> getEventsByBeaconId(@PathVariable String location_beacon) {
        try {
            ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                    "http://eventosCRUD/eventosPUJ/beacon/" + location_beacon,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<event>>() {}
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //http://localhost:8081/eventosPUJ/evento/estado/centro/{{status}}/{{nameCenter}}
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
                    new ParameterizedTypeReference<List<event>>() {}
            );

            List<event> events = responseEntity.getBody();
            return ResponseEntity.ok(events);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // or handle error response in another way
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
    @PostMapping("/evento/nuevo/comment")
    public ResponseEntity<?> postnewComment(@RequestBody commentevent comment) {
        String url = "http://eventosCRUD/eventosPUJ/comentarioPUJ/comentario/evento";
        try {
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, comment, String.class);
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>("Ya calificaste el evento. Puedes modificar la calificacion", HttpStatus.OK);
        }
    }
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
                    .body(null); // or handle error response in another way
        }
    }

    //    GET http://localhost:8081/eventosPUJ/eventos/activos
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
            return new ResponseEntity<>("actividad actualizado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verifique los datos de la actividad", HttpStatus.OK);
        }

    } catch (HttpClientErrorException ex) {
        return new ResponseEntity<>("actividad incorrecto", HttpStatus.OK);
    }


}

    @GetMapping("/actividad/{idEvent}")
    public ResponseEntity<List<activity>> getActivity(@PathVariable int idEvent) {
        String url = "http://eventosCRUD/eventosPUJ/actividadesPUJ/actividades/evento/" + idEvent;
        try {
            ResponseEntity<List<activity>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<activity>>() {}
            );
            return responseEntity;
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // or handle error response in another way
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
