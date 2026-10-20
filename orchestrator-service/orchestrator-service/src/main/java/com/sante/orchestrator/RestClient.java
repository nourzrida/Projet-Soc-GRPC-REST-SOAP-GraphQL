package com.sante.orchestrator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RestClient {

    private final WebClient webClient;

    public RestClient(WebClient.Builder builder, @Value("${service.patient.url}") String patientUrl) {
        // On configure l'adresse de base (http://patient-service:8081)
        this.webClient = builder.baseUrl(patientUrl).build();
    }

    public String getPatientData(Long id) {
        return webClient.get()
                .uri("/api/patients/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public PatientBean getPatientByCin(String cin) {
        return webClient.get()
                // On appelle la route de recherche qu'on a créée dans le Service Patient
                .uri("/api/patients/search?cin=" + cin)
                .retrieve()
                // C'est ici la magie : Spring transforme le JSON reçu en objet PatientBean
                .bodyToMono(PatientBean.class)
                .block();
    }
}