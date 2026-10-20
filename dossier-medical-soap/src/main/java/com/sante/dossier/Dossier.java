package com.sante.dossier;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID technique de la base (1, 2, 3...)

    // Le lien avec le Service Patient
    private String idPatient;

    private String dateMiseAJour;
    private String traitement;
    private String medecinTraitant;

    // Ajout spécifique pour la logique Tunisienne
    private String typeEtablissement;
}