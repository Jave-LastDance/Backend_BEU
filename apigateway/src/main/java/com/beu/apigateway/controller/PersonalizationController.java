package com.beu.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/personalizacionPUJ")
public class PersonalizationController {
    @Autowired
    RestTemplate restTemplate;


    //http://localhost:8042/personalizacionPUJ/preferencias/{{nameCenter}}
    @GetMapping("/preferencias/{nameCenter}")
    public String getByPreferenceNameCenter(@PathVariable String nameCenter) {
        return restTemplate.getForObject("http://Personalization/personalizacionPUJ/preferencias/"+nameCenter, String.class);
    }

    //http://localhost:8042/personalizationPUJ/eventos/usuario/{{id_beacon}}/{{id_user}}
    @GetMapping("/eventos/usuario/{id_beacon}/{id_user}")
    public String getByPreferenceEventUser(@PathVariable int id_beacon, @PathVariable int id_user) {
        return restTemplate.getForObject("http://Personalization/personalizationPUJ/eventos/usuario/"+id_beacon+"/"+id_user, String.class);
    }
}
