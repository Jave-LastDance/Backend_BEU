package com.beu.apigateway.controller;

import java.util.UUID;

import com.beu.apigateway.entity.Request;
import com.beu.apigateway.kafka.RequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class RequestController {
    private RequestProducer requestProducer;

    // Constructor to inject a RequestProducer into this class.
    public RequestController(RequestProducer requestProducer) {
        this.requestProducer = requestProducer;
    }

    // This method handles POST requests at the "/api/v1/notifications" route.
    @PostMapping("/up")
    public ResponseEntity<String> placeNotification(@RequestParam String tokenDevice,@RequestParam int beaconId,@RequestParam int userId) {

        Request request = new Request();
        request.setBeaconId(beaconId);
        request.setTokenDevice(tokenDevice);
        request.setUserId(userId);
        // Generate a unique ID for the notification using UUID.
        request.setRequestId(UUID.randomUUID().toString());

        // Send the 'request' object to the Kafka producer.
        requestProducer.sendMessage(request);

        // Return a successful HTTP response with a message.
        return ResponseEntity.ok("Request sent to Kafka topic...");
    }

}