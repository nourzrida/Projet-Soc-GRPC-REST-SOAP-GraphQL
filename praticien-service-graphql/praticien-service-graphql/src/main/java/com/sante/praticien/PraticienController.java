package com.sante.praticien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PraticienController {

    @Autowired
    private PraticienRepository praticienRepository;

    // 1. Récupérer tous les médecins (Base MySQL)
    @QueryMapping
    public List<Praticien> allPraticiens() {
        return praticienRepository.findAll();
    }

    // 2. Récupérer un médecin par ID (Base MySQL)
    @QueryMapping
    public Praticien praticienById(@Argument Long id) {
        return praticienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Praticien non trouvé avec l'ID : " + id));
    }

    // 3. Créer un médecin (Mise à jour avec les nouveaux champs)
    @MutationMapping
    public Praticien createPraticien(@Argument String nom,
                                     @Argument String prenom,
                                     @Argument String specialite,
                                     @Argument String hopital,
                                     @Argument String ville,       // Nouveau champ
                                     @Argument String description) // Nouveau champ
    {
        Praticien p = new Praticien();
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setSpecialite(specialite);
        p.setHopital(hopital);
        p.setVille(ville);              // On enregistre la ville
        p.setDescription(description);  // On enregistre la description

        return praticienRepository.save(p);
    }

    // 4. Recherche intelligente par Ville (Sur la Base MySQL)
    // 4. Recherche intelligente par Ville ET Spécialité (Smart City Logic)
    @QueryMapping
    public List<Praticien> trouverMedecinUrgence(@Argument String ville, @Argument String specialite) {
        return praticienRepository.findAll().stream()
                .filter(p -> p.getVille() != null && p.getVille().equalsIgnoreCase(ville))
                .filter(p -> p.getSpecialite() != null && p.getSpecialite().equalsIgnoreCase(specialite))
                .collect(Collectors.toList());
    }
}