package com.sante.praticien;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Praticien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String specialite; // ex: Cardiologue
    private String hopital;    // ex: Hôpital Sahloul

    // --- NOUVEAUX CHAMPS POUR LA LOGIQUE MÉTIER ---
    private String ville;      // ex: Sousse, Tunis (Indispensable pour la recherche)
    private String description;// ex: "Conventionné CNAM", "Privé"
}