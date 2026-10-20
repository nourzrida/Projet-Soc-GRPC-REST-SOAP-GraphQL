package com.sante.patient_service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Operation(summary = "Récupérer tous les patients")
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // NEW: Search by CIN (Real world scenario)
    @GetMapping("/search")
    public Patient searchByCin(@RequestParam String cin) {
        return patientRepository.findByCin(cin)
                .orElseThrow(() -> new RuntimeException("Patient introuvable avec ce CIN"));
    }

    // Keep get by ID but make it smart too
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientSmart(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    Map<String, Object> response = new HashMap<>();
                    // Map Entity to Map to add extra fields without changing Entity
                    response.put("id", patient.getId());
                    response.put("nom", patient.getNom());
                    response.put("prenom", patient.getPrenom());
                    response.put("ville", patient.getGouvernorat()); // Mapping gouvernorat to ville
                    response.put("cin", patient.getCin());
                    response.put("email", patient.getEmail());

                    // Smart Logic
                    response.put("statut_cnam", "Actif"); // Simulated check
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
}