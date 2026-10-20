package com.sante.urgence;

import com.sante.urgence.stubs.Alerte;
import com.sante.urgence.stubs.AlerteReponse;
import com.sante.urgence.stubs.UrgenceServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UrgenceServiceImpl extends UrgenceServiceGrpc.UrgenceServiceImplBase {

    @Override
    public void envoyerAlerte(Alerte request, StreamObserver<AlerteReponse> responseObserver) {
        String motif = request.getTypeUrgence().toUpperCase();
        String ville = request.getLocalisation();
        String action;

        System.out.println("DEBUG gRPC: Motif reçu = " + motif);
        if (motif.contains("VITAL") || motif.contains("COEUR") || motif.contains("ARRÊT")) {
            action = "🚁 DÉCOLLAGE HÉLICOPTÈRE SAMU 190 vers " + ville;
        } else {
            // Tout ce qui n'est pas VITAL part en Ambulance
            action = "🚑 ENVOI AMBULANCE STANDARD vers " + ville;
        }

        AlerteReponse response = AlerteReponse.newBuilder()
                .setMessage(action)
                .setEquipeEnvoyee(true)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}