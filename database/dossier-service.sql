CREATE DATABASE IF NOT EXISTS dossier_db;
USE dossier_db;

CREATE TABLE IF NOT EXISTS dossier_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_patient VARCHAR(255),
    medecin_traitant VARCHAR(255),
    traitement VARCHAR(255),
    date_miseajour VARCHAR(255),
    type_etablissement VARCHAR(255)
);

-- Dossier Sami (Urgence Vitale - CŒUR)
INSERT INTO dossier_entity (id_patient, medecin_traitant, traitement, date_miseajour, type_etablissement)
VALUES ('1', 'Dr. Ancien', 'Traitement pour insuffisance COEUR et Hypertension', '01/01/2025', 'Clinique les Oliviers');

-- Dossier Karim (Sain - Vide)
-- Pas d'insertion pour l'ID 2 (Karim) car il n'a pas d'antécédents.