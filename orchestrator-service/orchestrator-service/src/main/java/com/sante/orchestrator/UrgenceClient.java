package com.sante.orchestrator;

import com.sante.urgence.stubs.Alerte;
import com.sante.urgence.stubs.AlerteReponse;
import com.sante.urgence.stubs.UrgenceServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient; // L'annotation vient d'ici
import org.springframework.stereotype.Service;

@Service
public class UrgenceClient {

    @GrpcClient("urgenceService")
    private UrgenceServiceGrpc.UrgenceServiceBlockingStub urgencyStub;

    // Modifie cette méthode pour accepter ville et motif
    public String envoyerAlerte(String id, String ville, String motif) {
        try {
            Alerte request = Alerte.newBuilder()
                    .setIdPatient(id)
                    .setLocalisation(ville)    // Si ton proto a ce champ
                    .setTypeUrgence(motif)     // Si ton proto a ce champ
                    .build();

            AlerteReponse response = urgencyStub.envoyerAlerte(request);
            return response.getMessage();
        } catch (Exception e) {
            return "Erreur gRPC: " + e.getMessage();
        }
    }
}
