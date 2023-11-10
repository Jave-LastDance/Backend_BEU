package co.edu.javeriana.beu.notificationmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import co.edu.javeriana.beu.notificationmicroservice.model.Event;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getAll")
    public List<Event> getAllEvents(int idbeacon, int iduser) {
        ResponseEntity<List<Event>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/personalizacionPUJ/eventos/usuario/"+idbeacon+"/"+iduser,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Event>>() {
                });

        List<Event> events = responseEntity.getBody();
        return events;
    }
}
