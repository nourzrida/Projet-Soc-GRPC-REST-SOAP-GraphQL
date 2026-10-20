CREATE DATABASE IF NOT EXISTS praticien_db;
USE praticien_db;

CREATE TABLE IF NOT EXISTS praticien (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    specialite VARCHAR(255),
    ville VARCHAR(255),
    hopital VARCHAR(255),
    description VARCHAR(255)
);

-- Médecins Géolocalisés
INSERT INTO praticien (nom, prenom, specialite, ville, hopital, description)
VALUES ('Sassi', 'Dr.', 'Cardiologue', 'Sousse', 'Hôpital Sahloul', 'Conventionné');

INSERT INTO praticien (nom, prenom, specialite, ville, hopital, description)
VALUES ('Amri', 'Dr.', 'Généraliste', 'Bizerte', 'Hôpital Militaire de Bizerte', 'Public');

INSERT INTO praticien (nom, prenom, specialite, ville, hopital, description)
VALUES ('Ben Ali', 'Dr.', 'Cardiologue', 'Tunis', 'La Rabta', 'Public');