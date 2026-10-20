CREATE DATABASE IF NOT EXISTS patient_db;
USE patient_db;

CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    cin VARCHAR(255),
    email VARCHAR(255),
    telephone VARCHAR(255),
    matricule_cnam VARCHAR(255),
    gouvernorat VARCHAR(255)
);

-- Données de Démo Smart City
INSERT INTO patient (id, nom, prenom, cin, email, telephone, matricule_cnam, gouvernorat)
VALUES (1, 'Trabelsi', 'Sami', '09123456', 'sami@tn.tn', '22000000', 'CNAM-SOUSSE-01', 'Sousse');

INSERT INTO patient (id, nom, prenom, cin, email, telephone, matricule_cnam, gouvernorat)
VALUES (2, 'Jelassi', 'Karim', '11111111', 'karim@tn.tn', '98123456', 'CNAM-BIZ-01', 'Bizerte');