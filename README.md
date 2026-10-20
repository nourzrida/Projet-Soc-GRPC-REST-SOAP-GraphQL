# Projet SOC — Architecture distribuée de services de santé

Application de santé distribuée basée sur une architecture microservices. Le projet met en œuvre plusieurs styles de communication — **REST**, **SOAP**, **GraphQL** et **gRPC** — derrière une passerelle unique et un service d’orchestration.

## Vue d’ensemble

Le système expose plusieurs services spécialisés :

- **Patient Service** : gestion des patients via une API REST.
- **Dossier Médical Service** : gestion des dossiers médicaux via SOAP.
- **Praticien Service** : gestion des praticiens via GraphQL.
- **Urgence Service** : gestion des urgences via gRPC.
- **Orchestrator Service** : coordination des appels entre les différents services.
- **Gateway Service** : point d’entrée unique pour les clients.
- **MySQL** : base de données persistante utilisée par les services concernés.

## Architecture

```text
Client
  |
  v
Gateway Service :8888
  |
  +--> Patient Service       REST     :8081
  +--> Dossier Médical       SOAP     :8082
  +--> Praticien Service     GraphQL  :8083
  +--> Orchestrator Service           :8084
                                      |
                                      +--> Urgence Service (gRPC) :9090

MySQL :3307 -> 3306
```

Le client utilise principalement le port `8888` de la gateway. Les autres services communiquent entre eux sur le réseau Docker Compose.

## Technologies utilisées

- Java 17
- Spring Boot
- Spring Cloud Gateway
- Spring Data JPA
- MySQL 8
- REST / HTTP
- SOAP / Web Services
- GraphQL
- gRPC et Protocol Buffers
- Maven
- Docker et Docker Compose
- Lombok

## Structure du projet

```text
.
├── database/
│   ├── dossier-service.sql
│   ├── patient-service.sql
│   └── praticien-service.sql
├── dossier-medical-soap/
├── gateway-service/
├── orchestrator-service/
├── patient-service/
├── praticien-service-graphql/
├── urgence-service-grpc/
├── DesignPatterns/
└── docker-compose.yml
```

> Certains services contiennent un sous-dossier portant le même nom que le module Maven. Vérifiez le chemin exact avant d’exécuter les commandes Maven.

## Prérequis

- JDK 17 ou une version compatible avec les modules Maven du projet ;
- Maven 3.8+ ou le Maven Wrapper fourni dans les services ;
- Docker Desktop avec Docker Compose ;
- Git.

Vérifiez votre environnement :

```bash
java -version
mvn -version
docker --version
docker compose version
```

## Démarrage avec Docker Compose

Les images référencées dans `docker-compose.yml` doivent être construites avant le démarrage de la stack.

### 1. Construire les services

Depuis la racine du dépôt :

```bash
cd patient-service
./mvnw clean package -DskipTests
cd ..

cd dossier-medical-soap
./mvnw clean package -DskipTests
cd ..

cd praticien-service-graphql/praticien-service-graphql
./mvnw clean package -DskipTests
cd ../..

cd urgence-service-grpc/urgence-service-grpc
./mvnw clean package -DskipTests
cd ../..

cd orchestrator-service/orchestrator-service
./mvnw clean package -DskipTests
cd ../..

cd gateway-service/gateway-service
./mvnw clean package -DskipTests
cd ../..
```

Sur Windows, remplacez `./mvnw` par `mvnw.cmd`.

Construisez ensuite les images Docker avec les noms attendus par Compose :

```bash
docker build -t patient-service:v1 ./patient-service
docker build -t dossier-service:v1 ./dossier-medical-soap
docker build -t praticien-service:v1 ./praticien-service-graphql/praticien-service-graphql
docker build -t urgence-service:v1 ./urgence-service-grpc/urgence-service-grpc
docker build -t orchestrator-service:v1 ./orchestrator-service/orchestrator-service
docker build -t gateway-service:v1 ./gateway-service/gateway-service
```

### 2. Démarrer la plateforme

```bash
docker compose up -d
```

Consultez l’état des conteneurs :

```bash
docker compose ps
```

Consultez les logs :

```bash
docker compose logs -f gateway-service
docker compose logs -f orchestrator-service
```

Arrêtez la plateforme avec :

```bash
docker compose down
```

Pour supprimer également le volume MySQL et les données persistées :

```bash
docker compose down -v
```

## Ports et points d’accès

| Composant | Technologie | Port local | Point d’accès indicatif |
|---|---:|---:|---|
| Gateway | Spring Cloud Gateway | `8888` | `http://localhost:8888` |
| Patient Service | REST | `8081` | `http://localhost:8081` |
| Dossier Médical | SOAP | `8082` | `http://localhost:8082/ws` |
| Praticien Service | GraphQL | `8083` | `http://localhost:8083/graphql` |
| Orchestrator | REST / WebFlux | `8084` | `http://localhost:8084` |
| Urgence Service | gRPC | `9090` | endpoint gRPC |
| MySQL | MySQL | `3307` | `localhost:3307` |

Les routes exactes dépendent des contrôleurs, résolveurs GraphQL, endpoints SOAP et fichiers de configuration présents dans chaque module. La gateway est le point d’entrée recommandé pour les clients.

## Base de données

Le fichier `docker-compose.yml` démarre MySQL 8 avec les paramètres de développement suivants :

```text
Base de données : patient_db
Utilisateur     : root
Mot de passe    : root
Port hôte       : 3307
Port conteneur  : 3306
```

Les scripts SQL d’initialisation se trouvent dans le dossier `database/` :

- `patient-service.sql`
- `dossier-service.sql`
- `praticien-service.sql`

> Ces identifiants sont destinés au développement local. Ne les utilisez pas tels quels en production.

## Exécution locale d’un service

Chaque module Maven peut être lancé indépendamment. Exemple pour le service patient :

```bash
cd patient-service
./mvnw spring-boot:run
```

Pour compiler et exécuter les tests :

```bash
./mvnw clean test
```

Pour les modules imbriqués, utilisez notamment :

```bash
cd praticien-service-graphql/praticien-service-graphql
cd urgence-service-grpc/urgence-service-grpc
cd orchestrator-service/orchestrator-service
cd gateway-service/gateway-service
```

## Communication interservices

- Le **Patient Service** expose des opérations HTTP/REST.
- Le **Dossier Médical Service** expose un endpoint SOAP à l’adresse interne `/ws`.
- Le **Praticien Service** expose une API GraphQL à `/graphql`.
- Le **Urgence Service** expose un serveur gRPC sur le port `9090`.
- L’**Orchestrator Service** centralise les appels vers les services métiers.
- Le **Gateway Service** fournit un point d’entrée externe unique sur le port `8888`.

Dans Docker Compose, l’orchestrateur utilise les noms DNS des conteneurs :

```text
http://patient-service:8081
http://dossier-service:8082/ws
http://praticien-service:8083/graphql
urgence-service:9090
```

## Design Patterns

Le dossier `DesignPatterns/` contient un projet démonstratif JavaFX consacré à plusieurs patrons de conception :

- State ;
- Decorator ;
- Composite ;
- Factory.

Consultez le [README du module DesignPatterns](DesignPatterns/README.md) pour les prérequis et les commandes spécifiques.

## Dépannage

### Les conteneurs ne démarrent pas

Vérifiez que les images portent exactement les noms utilisés dans `docker-compose.yml` :

```bash
docker images
```

Reconstruisez une image si nécessaire :

```bash
docker build --no-cache -t <nom-image>:v1 <chemin-du-service>
```

### MySQL n’est pas encore disponible

Le démarrage du conteneur ne garantit pas toujours que MySQL accepte immédiatement les connexions. Consultez les logs :

```bash
docker compose logs -f mysqldb
```

### Un port est déjà utilisé

Arrêtez le processus utilisant le port concerné ou modifiez le mapping dans `docker-compose.yml`. Le port MySQL exposé côté hôte est `3307` pour éviter un conflit avec un MySQL local sur `3306`.

## Contribution

1. Créez une branche dédiée :

   ```bash
   git checkout -b feature/ma-fonctionnalite
   ```

2. Effectuez vos modifications et ajoutez des tests lorsque cela est pertinent.
3. Vérifiez que les modules Maven compilent correctement.
4. Créez un commit descriptif.
5. Ouvrez une pull request.

## Licence

Aucune licence open source n’est actuellement déclarée dans ce dépôt. Ajoutez un fichier `LICENSE` si vous souhaitez définir les conditions de réutilisation du projet.

## Auteur

Projet maintenu par [nourzrida](https://github.com/nourzrida).
