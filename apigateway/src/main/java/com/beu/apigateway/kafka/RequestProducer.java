package com.beu.apigateway.kafka;

import com.beu.apigateway.entity.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class RequestProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProducer.class);

    @Autowired
    private KafkaTemplate<String, Request> kafkaTemplate;

    // Method to send a message to Kafka with the provided Request object.
    public void sendMessage(Request event) {
        LOGGER.info(String.format("Request Sent => %s", event.toString()));

        // Create a Kafka message with the Request object and set the topic.
        Message<Request> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "notificationsRequests")
                .build();

        // Send the message to the Kafka topic.
        kafkaTemplate.send(message);
    }
}
