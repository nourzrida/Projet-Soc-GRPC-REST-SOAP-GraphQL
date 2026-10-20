package com.sante.praticien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticienRepository extends JpaRepository<Praticien, Long> {
    // On pourra ajouter des recherches par spécialité plus tard
}