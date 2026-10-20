package com.sante.dossier;

import com.sante.dossier.model.DossierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component


public interface DossierRepository extends JpaRepository<DossierEntity, Long> {
    // Méthode magique pour trouver le dossier d'un patient spécifique
    Optional<DossierEntity> findByIdPatient(String idPatient);
}