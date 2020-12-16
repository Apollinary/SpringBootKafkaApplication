package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageHandler;

@Configuration
public class KafkaProducerConfig {
    @Value("${kafka.topicTo}")
    private String topicTo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    @ServiceActivator(inputChannel = "kafkaChannel")
    public MessageHandler messageHandler(){
        KafkaProducerMessageHandler<String, String> handler = new KafkaProducerMessageHandler<>(kafkaTemplate);
        handler.setTopicExpression(new LiteralExpression(topicTo));
        return handler;
    }
}