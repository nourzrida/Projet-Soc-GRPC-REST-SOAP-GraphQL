package com.sante.orchestrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
    // Cette classe doit avoir les mêmes champs que ton Patient Service
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String telephone;
    private String ville;
    private String gouvernorat;
}