package com.example.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private RabbitMQProducer producer;
    
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
        logger.info("MessageController initialized with RabbitMQProducer");
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up!");
    }
    
    // localhost:8080/api/v1/publish?message=HelloWorld
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        try {
            logger.info("Attempting to send message: {}", message);
            if (producer == null) {
                logger.error("RabbitMQProducer is null");
                return ResponseEntity.internalServerError().body("RabbitMQProducer not initialized");
            }
            producer.sendMessage(message);
            logger.info("Successfully sent message to RabbitMQ: {}", message);
            return ResponseEntity.ok("Message sent to RabbitMQ: " + message);
        } catch (Exception e) {
            logger.error("Failed to send message: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to send message: " + e.getMessage());
        }
    }


}
