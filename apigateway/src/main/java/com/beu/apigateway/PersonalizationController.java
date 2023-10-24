package com.beu.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PersonalizationController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    //http://localhost:8082/personalizacionPUJ/preferencias/{{nameCenter}}
    @GetMapping("/gateBeacons/personalizacionPUJ/preferencias")
    public String getByPreferenceNameCenter(@RequestParam String nameCenter) {
        return restTemplate.getForObject("http://personalicePUJ/personalizacionPUJ/preferencias?nameCenter={nameCenter})", String.class, nameCenter);
    }

    //http://localhost:8082/personalizationPUJ/eventos/usuario/{{id_beacon}}/{{id_user}}
    @GetMapping("/gateBeacons/personalizationPUJ/eventos/usuario")
    public String getByPreferenceEventUser(@RequestParam int id_beacon, @RequestParam int id_user) {
        return restTemplate.getForObject("http://personalicePUJ/personalizationPUJ/eventos/usuario?id_beacon={id_beacon)&id_user={id_user})", String.class, id_beacon, id_user);
    }
}
