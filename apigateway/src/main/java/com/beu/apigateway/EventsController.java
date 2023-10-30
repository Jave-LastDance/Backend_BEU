package com.beu.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class EventsController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

   // http://localhost:8081/eventosPUJ/eventos
    @GetMapping("/gateBeacons/eventosPUJ/eventos")
    public String getallevents() {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/eventos", String.class);
    }
/*
    //http://localhost:8081/eventosPUJ/evento/{{idEvent}}
    @GetMapping("/gateBeacons/eventosPUJ/evento")
    public String getEventById(@RequestParam int idEvent ) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento?idEvent={idEvent)", String.class,idEvent);
    }

    //http://localhost:8081/eventosPUJ/evento/centro/{{nameCenter}}
    @GetMapping("/gateBeacons/eventosPUJ/evento/centro")
    public String getEventById(@RequestParam String nameCenter) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/centro?nameCenter={nameCenter)", String.class,nameCenter);
    }

    //http://localhost:8081/eventosPUJ/evento/palabra/{{keyword}}
    @GetMapping("/gateBeacons/eventosPUJ/evento/palabra")
    public String getEventByKeyword(@RequestParam String keyword) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/palabra?keyword={keyword)", String.class,keyword);
    }

    //http://localhost:8081/eventosPUJ/beacon/{{id_beacon}}
    @GetMapping("/gateBeacons/eventosPUJ/beacon")
    public String getEventByBeaconId(@RequestParam String id_beacon) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/beacon?id_beacon={id_beacon)", String.class,id_beacon);
    }

    //http://localhost:8081/eventosPUJ/evento/estado/centro/{{status}}/{{nameCenter}}
    @GetMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
    public String getEventByStateCenter(@RequestParam String status,@RequestParam String nameCenter) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/estado/centro/ACAME FALTA", String.class,status,nameCenter);
    }

     //http://localhost:8081/eventosPUJ/evento/fecha/{{dateStart}}
     @GetMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
     public String getEventByDateStart(@RequestParam String dateStart) {
         return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/fecha?dateStart={dateStart)", String.class,dateStart);
     }

    //http://localhost:8081/eventosPUJ/evento
    @PostMapping("/gateBeacons/eventosPUJ/evento")
    public String getEventByDateStart2() {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento", String.class);
    }

    //http://localhost:8081/eventosPUJ/evento
    @PutMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
    public String getEventByDateStart3(@RequestParam String dateStart) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/fecha?dateStart={dateStart)", String.class,dateStart);
    }

   //http://localhost:8081/eventosPUJ/evento/estado/{{idEvent}}
    @PutMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
    public String getEventByDateStart4(@RequestParam String dateStart) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/fecha?dateStart={dateStart)", String.class,dateStart);
    }

    //http://localhost:8081/eventosPUJ/evento/{{idEvent}}
    @DeleteMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
    public String getEventByDateStart5(@RequestParam String dateStart) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/fecha?dateStart={dateStart)", String.class,dateStart);
    }

    //http://localhost:8081/eventosPUJ/evento/nuevo/rating
    @PostMapping("/gateBeacons/eventosPUJ/evento/estado/centro")
    public String getEventByDateStart6(@RequestParam String dateStart) {
        return restTemplate.getForObject("http://eventosCRUD/eventosPUJ/evento/fecha?dateStart={dateStart)", String.class,dateStart);
    }*/
}
