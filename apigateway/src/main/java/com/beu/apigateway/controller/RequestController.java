package com.beu.apigateway.controller;

import java.util.UUID;

import com.beu.apigateway.entity.Request;
import com.beu.apigateway.kafka.RequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RequestController {
    private RequestProducer requestProducer;

    // Constructor to inject a RequestProducer into this class.
    public RequestController(RequestProducer requestProducer) {
        this.requestProducer = requestProducer;
    }

    // This method handles POST requests at the "/api/v1/notifications" route.
    @PostMapping("/notifications")
    public ResponseEntity<String> placeNotification(@RequestBody Request request) {

        // Generate a unique ID for the notification using UUID.
        request.setRequestId(UUID.randomUUID().toString());

        // Send the 'request' object to the Kafka producer.
        requestProducer.sendMessage(request);

        // Return a successful HTTP response with a message.
        return ResponseEntity.ok("Request sent to Kafka topic...");
    }

}