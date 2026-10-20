package com.sante.patient_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}
    @Bean
    CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            // On vérifie si la base est vide avant d'insérer
            if (patientRepository.count() == 0) {
                patientRepository.save(new Patient(null, "Trabelsi", "Sami", "sami@tn.tn", "22333444", "09111222", "12345678-00", "Tunis"));
                patientRepository.save(new Patient(null, "Ben Amor", "Leila", "leila@tn.tn", "55666777", "08222333", "87654321-11", "Sfax"));
                patientRepository.save(new Patient(null, "Jelassi", "Karim", "karim@tn.tn", "99888777", "14555666", "00000000-00", "Bizerte"));
                System.out.println("✅ Base MySQL initialisée avec des patients tunisiens !");
            }
        };
    }
}
