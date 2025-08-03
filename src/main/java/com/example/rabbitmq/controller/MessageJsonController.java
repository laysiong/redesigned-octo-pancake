package com.example.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.rabbitmq.dto.User;

import com.example.rabbitmq.publisher.RabbitMQJsonProducer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping ("/api/v1/json")
public class MessageJsonController {

    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public  ResponseEntity<String> sendMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
        return  ResponseEntity.ok("JSON message sent to RabbitMQ: " + user);
    }
    
}
