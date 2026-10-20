package com.sante.praticien;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PraticienServiceGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraticienServiceGraphqlApplication.class, args);
	}

    @Bean
    CommandLineRunner initPraticiens(PraticienRepository repository) {
        return args -> {
            // On ne remplit que si la base est vide pour éviter les doublons
            if (repository.count() == 0) {

                // 1. LE MÉDECIN HÉROS (Celui qu'on veut voir dans la démo)
                // Il est à SOUSSE et il est CARDIOLOGUE
                repository.save(new Praticien(
                        null,
                        "Sassi", "Dr.",
                        "Cardiologue",
                        "Hôpital Sahloul",
                        "Sousse",
                        "Conventionné CNAM"
                ));

                // 2. LE MÉDECIN DISTRACTEUR (Pour prouver que le filtre marche)
                // Il est CARDIOLOGUE mais à TUNIS (Trop loin !)
                repository.save(new Praticien(
                        null,
                        "Ben Ali", "Dr.",
                        "Cardiologue",
                        "Clinique La Rabta",
                        "Tunis",
                        "Public"
                ));

                // 3. UN GÉNÉRALISTE (Pour les autres cas)
                repository.save(new Praticien(
                        null,
                        "Karray", "Dr.",
                        "Généraliste",
                        "Cabinet Privé",
                        "Sfax",
                        "Privé"
                ));

                System.out.println("✅ Base MySQL Praticien initialisée avec les données Smart City !");
            }
        };
    }
}
