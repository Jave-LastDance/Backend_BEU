package com.beu.apigateway.controller;

import com.beu.apigateway.entity.Beacon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/beacon")
public class BeaconsContoller {

   @Autowired
    RestTemplate restTemplate;


    @GetMapping("/neighbors")
    public String getneighbors(@RequestParam String beaconId) {
        String response = restTemplate.getForObject("http://beaconsservice/beacons/neighbors?beaconId={beaconId}", String.class,beaconId);
        return response;
    }

    @GetMapping("/id")
    public Beacon getbeaconbyid(@RequestParam String beaconId) {
        Beacon response = restTemplate.getForObject("http://beaconsservice/beacons/beacon?beaconId={beaconId}", Beacon.class,beaconId);
        return response;
    }

}
