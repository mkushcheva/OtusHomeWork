package ru.otus.salebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SalebooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalebooksApplication.class, args);
    }
}
