package com.beu.apigateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {


    // This method defines a Kafka topic bean
    @Bean
    public NewTopic topic() {
        // Create a new Kafka topic with the specified name and return it as a bean
        return TopicBuilder.name("notificationsRequests")
                .build();
    }

}