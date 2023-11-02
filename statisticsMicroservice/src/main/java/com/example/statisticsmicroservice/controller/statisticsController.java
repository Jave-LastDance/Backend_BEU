package com.example.statisticsmicroservice.controller;

import com.example.statisticsmicroservice.entity.*;
import com.example.statisticsmicroservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estadisticasPUJ")
public class statisticsController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    com.example.statisticsmicroservice.service.eventscenterService eventscenterService;

    @Autowired
    com.example.statisticsmicroservice.service.eventsheadstatisticService eventsheadstatisticService;

    @Autowired
    com.example.statisticsmicroservice.service.eventxcategoryService eventxcategoryService;

    @Autowired
    stateventsService stateeventsService;

    @Autowired
    com.example.statisticsmicroservice.service.topeventsService topeventsService;


    public List<event> getAllEvents(){
        List<event> eventsSystem= new ArrayList<>();
        ResponseEntity<List<event>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/eventosPUJ/eventos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<event>>() {}
        );
        eventsSystem=responseEntity.getBody();
        return eventsSystem;
    }

    @GetMapping("/eventos/centros")
    public List<eventscenter> getAll(){
        return eventscenterService.getAll(getAllEvents());
    }

    @GetMapping("/eventos/encargados/{center}")
    public List<eventsheadstatistics> getAllHead(@PathVariable String center){
        return eventsheadstatisticService.getAll(center,getAllEvents());
    }

    @GetMapping("/eventos/categorias/{center}")
    public List<eventxcategory> getAllCategory(@PathVariable String center){
        return eventxcategoryService.getStatistic(center,getAllEvents());
    }

    @GetMapping("/eventos/estado/{center}")
    public stateevents getAllState(@PathVariable String center){
        return stateeventsService.getStateCenter(getAllEvents(), center);
    }

    @GetMapping("/eventos/ranking/{center}")
    public List<topevents> getTopFive(@PathVariable String center){
        return topeventsService.getAllCenter( center, getAllEvents());
    }

}
