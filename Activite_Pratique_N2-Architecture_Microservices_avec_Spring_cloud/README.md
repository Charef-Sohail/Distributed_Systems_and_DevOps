# Activité pratique N°2 : Architecture Microservices avec Spring Cloud

## Objectif

Cette activité initialise un microservice client destiné à s'intégrer dans une
architecture de microservices basée sur Spring Cloud.

Le projet constitue le socle du service : l'application Spring Boot est
préparée pour utiliser un serveur de configuration, s'enregistrer auprès de
Eureka et exposer ses informations de santé avec Actuator.

## Fonctionnalités préparées

- Application Spring Boot dédiée au service client
- Accès aux données avec Spring Data JPA et H2
- Intégration Spring Cloud Config Client
- Intégration Eureka Client pour la découverte de services
- Endpoints de supervision avec Spring Boot Actuator
- Exposition REST avec Spring Web et Spring Data REST

## Technologies

- Java 17
- Spring Boot
- Spring Cloud 2025.1.3
- Spring Web MVC
- Spring Data JPA et Spring Data REST
- H2 Database
- Spring Cloud Config Client
- Netflix Eureka Client
- Spring Boot Actuator
- Lombok
- Maven

## Prérequis

- Java 17 ou une version ultérieure
- Maven 3.8+ ou le Maven Wrapper fourni
- Un IDE compatible avec Spring Boot
- Un serveur Config et un serveur Eureka si l'intégration distribuée est activée

## Démarrage

Depuis ce dossier :

```bash
./mvnw spring-boot:run
```

Sous Windows :

```powershell
.\mvnw.cmd spring-boot:run
```

## Tests

```bash
./mvnw test
```

## Structure

```text
src/
├── main/java/       # Classe principale du service client
├── main/resources/  # Configuration Spring Boot
└── test/java/       # Tests du projet
```

## État actuel

Le projet contient actuellement le squelette Spring Boot du service client et
ses dépendances Spring Cloud. Les serveurs Config, Eureka et les autres
microservices de l'architecture devront être ajoutés ou configurés dans les
étapes suivantes.