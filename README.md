# AutoLoc API

Spring Boot API for the AutoLoc vehicle-rental domain.

## Requirements

- Java 25
- Maven (or the included Maven Wrapper)
- MySQL running locally or on an accessible host

## Database configuration

The application reads its database settings from environment variables.

For example:

```bash
export DB_URL='jdbc:mysql://localhost:3306/autoloc_db?createDatabaseIfNotExist=true'
export DB_USER=root
export DB_PASSWORD='your_password'
```

Do not commit credentials or `.env` files. `.env` is ignored by Git, but Spring Boot does not load it automatically; export the variables in your shell or configure your IDE's environment.

Hibernate is configured to use the MySQL dialect. The schema is managed according to the active Spring configuration.

## Running the application

```bash
./mvnw spring-boot:run
```

The default configuration listens on port `8080` unless another port is configured.

The repository includes a development configuration at `src/main/resources/appliation-dev.properties`. Rename it to `application-dev.properties` before activating the `dev` profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The development profile enables SQL logging, Hibernate SQL formatting, and automatic schema updates, and uses port `59919`.

## Domain model

The staged domain model includes:

- `BaseEntity`, a mapped superclass with an auto-generated ID
- `Vehicle`, a JPA entity stored in the `vehicles` table
- `VehicleCategory`enum
- `VehicleStatus`enum

Vehicles are created with a registration number, make, model, category, and daily rental rate. New vehicles default to `AVAILABLE`.
