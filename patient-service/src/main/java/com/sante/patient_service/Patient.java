package com.sante.patient_service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // --- SPÉCIFICITÉS TUNISIENNES ---
    @Column(length = 8)
    private String cin;           // Ex: "09876543"
    private String matriculeCnam; // Ex: "12345678-00"
    private String gouvernorat;   // Ex: "Tunis", "Sfax"
}