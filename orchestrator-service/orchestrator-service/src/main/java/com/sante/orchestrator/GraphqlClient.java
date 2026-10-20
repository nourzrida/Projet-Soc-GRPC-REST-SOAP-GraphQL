package com.sante.orchestrator;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GraphqlClient {

    private final HttpGraphQlClient graphQlClient;

    public GraphqlClient(@Value("${service.praticien.url}") String praticienUrl) {
        this.graphQlClient = HttpGraphQlClient.builder()
                .url(praticienUrl)
                .build();
    }

    public @Nullable List<Map> getAllPraticiens() {
        // CORRECTION ICI : On ajoute "ville" pour pouvoir filtrer !
        String query = """
            query {
                allPraticiens {
                    nom
                    specialite
                    hopital
                    ville
                }
            }
        """;

        return graphQlClient.document(query)
                .retrieve("allPraticiens")
                .toEntityList(Map.class)
                .block();
    }
}