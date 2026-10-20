package com.sante.dossier.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class DossierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idPatient;

    private String dateMiseAJour;
    private String traitement;
    private String medecinTraitant;
    private String typeEtablissement; // "Clinique" ou "Hôpital Public"
}