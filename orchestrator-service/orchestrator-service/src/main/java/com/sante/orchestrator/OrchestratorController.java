package com.sante.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrchestratorController {

    @Autowired
    private RestClient restClient;
    @Autowired
    private GraphqlClient graphqlClient;
    @Autowired
    private UrgenceClient urgenceClient;
    @Autowired
    private SoapClient soapClient;

    @GetMapping("/dossier-complet")
    public String assemblerDossier(@RequestParam Long id) {
        // ... (Ton ancien code reste ici si tu veux, mais on va utiliser le nouveau)
        return "Utilisez plutot /smart-intervention";
    }

    // --- NOUVELLE METHODE : SMART CITY (Celle utilisée par le ) ---
    @GetMapping("/smart-intervention")
    public Map<String, Object> smartIntervention(
            @RequestParam String cin,  // On ne demande plus l'ID ni la ville !
            @RequestParam(defaultValue = "MALAISE") String motif) {

        Map<String, Object> rapport = new HashMap<>();

        // 1. DYNAMIQUE : On interroge la base Patient pour avoir l'ID et la Ville
        PatientBean patient;
        try {
            patient = restClient.getPatientByCin(cin);
            rapport.put("patient", patient); // On renvoie tout l'objet (Nom, Prenom, Ville...)
        } catch (Exception e) {
            rapport.put("error", "CIN introuvable dans la base nationale");
            return rapport;
        }

        // On extrait les vraies données de la base
        Long idReel = patient.getId();
        String villeReelle = patient.getGouvernorat(); // ou .getVille() selon ton bean

        // 2. DYNAMIQUE : On cherche le dossier avec l'ID récupéré
        String niveauUrgence = "NORMAL";
        try {
            var dossier = soapClient.getDossier(String.valueOf(idReel));
            String historique = dossier.getTraitement();

            // Analyse dynamique du texte de la base de données
            if (historique.toUpperCase().contains("COEUR") || historique.toUpperCase().contains("CARDIAQUE")) {
                niveauUrgence = "URGENCE VITALE";
                historique = "⚠️ ALERTE VITALE DÉTECTÉE || " + historique;
            }
            rapport.put("dossier", historique);
        } catch (Exception e) {
            rapport.put("dossier", "Dossier inaccessible");
        }

        // 3. DYNAMIQUE : On cherche les médecins
        try {
            List<Map> liste = (List<Map>) graphqlClient.getAllPraticiens(); // List<Map>
            StringBuilder sb = new StringBuilder();
            boolean trouve = false;

            if (liste != null) {
                for (Map m : liste) {
                    String v = (String) m.get("ville");
                    if (v != null && v.equalsIgnoreCase(villeReelle)) {
                        sb.append(m.get("nom")).append(" (").append(m.get("specialite")).append(") ");
                        trouve = true;
                    }
                }
            }
            // Si trouvé, on envoie le texte propre. Sinon, message d'erreur.
            rapport.put("medecins", trouve ? sb.toString() : "Aucun médecin à " + villeReelle);

        } catch (Exception e) { rapport.put("medecins", "Erreur GraphQL"); }

        // 4. DYNAMIQUE : On déclenche le SAMU vers la Ville du Patient
        try {
            String action = urgenceClient.envoyerAlerte(String.valueOf(idReel), villeReelle, motif + " - " + niveauUrgence);
            rapport.put("urgence", action);
        } catch (Exception e) {
            rapport.put("urgence", "Erreur gRPC");
        }

        return rapport;
    }
}