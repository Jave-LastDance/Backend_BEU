package com.beu.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BeaconsContoller {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //aca vienen las direcciones para los otros
    @GetMapping("/gateBeacons/ndjasdjsdnjadjkabd")
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b) {
        String response = restTemplate.getForObject("http://sumador/suma?a={a)&b=íb)", String.class, a, b);
        return response;
    }

}
