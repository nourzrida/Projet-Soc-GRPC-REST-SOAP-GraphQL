package com.sante.dossier;

import com.sante.dossier.model.DossierEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DossierMedicalSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(DossierMedicalSoapApplication.class, args);
	}
    @Bean
    CommandLineRunner initDossiers(DossierRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Dossier pour Sami (ID Patient "1")
                repository.save(new DossierEntity(null, "1", "05/12/2025", "Scanner IRM + Repos", "Dr. Ben Salem", "Clinique les Oliviers (Sousse)"));

                // Dossier pour l'étudiant (ID Patient "2")
                repository.save(new DossierEntity(null, "2", "01/12/2025", "Antibiotiques + Vitamine C", "Dr. Mzoughi", "Hôpital Hédi Chaker (Sfax)"));

                System.out.println("✅ Base MySQL Dossiers initialisée !");
            }
        };
    }
    }


