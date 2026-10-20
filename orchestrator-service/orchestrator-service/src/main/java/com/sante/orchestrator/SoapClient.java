package com.sante.orchestrator;

import com.sante.orchestrator.soap.Dossier;
import com.sante.orchestrator.soap.GetDossierRequest;
import com.sante.orchestrator.soap.GetDossierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public Dossier getDossier(String idPatient) {
        // 1. On remplit le formulaire (la requête)
        GetDossierRequest request = new GetDossierRequest();
        request.setIdPatient(idPatient);
        GetDossierResponse response = (GetDossierResponse) webServiceTemplate.marshalSendAndReceive(request);
        return response.getDossier();
    }
}