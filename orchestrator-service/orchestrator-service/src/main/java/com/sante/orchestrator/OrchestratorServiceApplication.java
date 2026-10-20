package com.sante.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // <--- Import important
import org.springframework.web.reactive.function.client.WebClient; // <--- Import important

@SpringBootApplication
public class OrchestratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorServiceApplication.class, args);
    }

    // AJOUTE CE BLOC ICI
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}