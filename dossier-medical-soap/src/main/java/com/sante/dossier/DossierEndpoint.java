package com.sante.dossier;

// 1. Imports pour la Base de Données
import com.sante.dossier.model.DossierEntity;
import com.sante.dossier.DossierRepository;

// 2. Imports pour SOAP (JAXB généré)
// (Note : Si ta classe Dossier est dans le même package, l'import n'est pas nécessaire, mais je le précise pour la clarté)
// import com.sante.dossier.Dossier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class DossierEndpoint {

    private static final String NAMESPACE_URI = "http://www.sante.com/dossier";

    @Autowired
    private DossierRepository dossierRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDossierRequest")
    @ResponsePayload
    public GetDossierResponse getDossier(@RequestPayload GetDossierRequest request) {

        System.out.println("--- REQUÊTE SOAP REÇUE ---");
        System.out.println("ID demandé : " + request.getIdPatient());

        GetDossierResponse response = new GetDossierResponse();
        Dossier dossierXml = new Dossier(); // L'objet que SOAP va renvoyer

        // 1. Chercher dans MySQL via le Repository JPA
        Optional<DossierEntity> dossierDbOpt = dossierRepository.findByIdPatient(request.getIdPatient());

        if (dossierDbOpt.isPresent()) {
            DossierEntity entity = dossierDbOpt.get();
            dossierXml.setIdPatient(entity.getIdPatient());
            dossierXml.setDateMiseAJour(entity.getDateMiseAJour());

            // --- SMART LOGIC: RISK ANALYSIS ---
            String traitement = entity.getTraitement();
            String alerte = "";

            if (traitement != null) {
                String t = traitement.toUpperCase();
                if (t.contains("COEUR") || t.contains("CARDIAQUE")) alerte = " [⚠️ ALERTE: RISQUE CARDIAQUE ÉLEVÉ]";
                if (t.contains("INSULINE") || t.contains("DIABETE")) alerte = " [⚠️ ALERTE: DIABÉTIQUE]";
            }

            dossierXml.setTraitement(traitement + alerte);

        } else {
            // SI PAS TROUVÉ
            System.out.println("❌ Aucun dossier trouvé en base pour cet ID.");

            dossierXml.setIdPatient(request.getIdPatient());
            dossierXml.setTraitement("DOSSIER INEXISTANT");
            dossierXml.setMedecinTraitant("N/A");
            dossierXml.setDateMiseAJour("N/A");
        }

        // 2. On met l'objet rempli dans la réponse
        response.setDossier(dossierXml);
        return response;
    }
}