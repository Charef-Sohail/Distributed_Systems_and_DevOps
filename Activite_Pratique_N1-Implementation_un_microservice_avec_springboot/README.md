# Activité pratique N°1 — Implémentation d’un microservice avec Spring Boot

## Objectif

Cette activité consiste à concevoir et implémenter un microservice REST avec
Spring Boot, puis à le tester et à le documenter.

## Prérequis

- Java 17 ou version ultérieure
- Maven 3.8+ (ou le Maven Wrapper du projet)
- Un IDE compatible avec Spring Boot

## Démarrage

```bash
./mvnw spring-boot:run
```

Sous Windows :

```powershell
.\\mvnw.cmd spring-boot:run
```

L’application est ensuite accessible à l’adresse :

```text
http://localhost:8080
```

## Tests

```bash
./mvnw test
```

## Structure indicative

```text
src/
├── main/
│   ├── java/          # Code source du microservice
│   └── resources/     # Configuration et ressources
└── test/              # Tests unitaires et d’intégration
```

## Technologies

- Spring Boot
- Spring Web
- Maven
- Java
