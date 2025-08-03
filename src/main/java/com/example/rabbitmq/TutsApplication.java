package com.example.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TutsApplication {
    public static void main(String[] args) { SpringApplication.run(TutsApplication.class, args);}

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            // This will keep the application running
            Thread.currentThread().join();
        };
    }
}