package com.sante.dossier;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getDossierResponse", namespace = "http://www.sante.com/dossier")
public class GetDossierResponse {

    @XmlElement(required = true)
    private Dossier dossier;
}