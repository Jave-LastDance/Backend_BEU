package com.beu.apigateway.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.beu.apigateway.entity.Notification;
import com.beu.apigateway.entity.Request;
import com.beu.apigateway.kafka.RequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/notificacionPUJ")
public class RequestController {
    @Autowired
    RestTemplate restTemplate;
    private RequestProducer requestProducer;

    // Constructor to inject a RequestProducer into this class.
    public RequestController(RequestProducer requestProducer) {
        this.requestProducer = requestProducer;
    }

    // This method handles POST requests at the "/api/v1/notifications" route.
    @PostMapping("/up")
    public ResponseEntity<String> placeNotification(@RequestBody Request request) {


        // Generate a unique ID for the notification using UUID.
        request.setRequestId(UUID.randomUUID().toString());

        // Send the 'request' object to the Kafka producer.
        requestProducer.sendMessage(request);

        // Return a successful HTTP response with a message.
        return ResponseEntity.ok("Request sent to Kafka topic...");
    }
    //LIST OF THE NOTIFICATIONS OF ONE USER
    @GetMapping("/notificaciones/{userId}")
    public ResponseEntity<List<Notification>> getNotificationByUser(@PathVariable int userId) {
        String url= "http://notificationMicroservice/notificationsPUJ/notifications/user/"+userId;
        try {
            ResponseEntity<List<Notification>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Notification>>() {}
            );
            List<Notification> notifications = responseEntity.getBody();
            return  ResponseEntity.ok(notifications);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }


    //OBJECT NOTIFICATION GIVEN A USER
   @GetMapping("/notificacion/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable("notificationId") int notificationId) {
        String url= "http://notificationMicroservice/notificationsPUJ/notification/"+notificationId;
      try {
          Notification notification = restTemplate.getForObject(url, Notification.class);
            return  ResponseEntity.ok(notification);
        } catch (Exception exception) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //DELETE NOTIFICATION BY ID
    @DeleteMapping("/notificacion/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable int notificationId) {
        String url= "http://notificationMicroservice/notificationsPUJ/notification/"+notificationId;
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            return ResponseEntity.ok(responseEntity.getBody());

    } catch (Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }
    }



}