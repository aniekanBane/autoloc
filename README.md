# AutoLoc API

Spring Boot API for the AutoLoc vehicle-rental domain.

## Requirements

- Java 25
- Maven (or the included Maven Wrapper)
- MySQL running locally or on an accessible host

## Database configuration

The application reads its database settings from environment variables or a local `.env` file (imported automatically via `spring.config.import=optional:file:.env[.properties]`).

Copy `.env.example` to `.env` and fill in your local credentials:

```bash
cp .env.example .env
```

```properties
DB_URL=jdbc:mysql://localhost/autoloc_db?createDatabaseIfNotExist=true
DB_USER=root
DB_PASSWORD=<your_password>
```

Do not commit credentials or `.env` files (`.env` is ignored by Git).

By default (`application.properties`), Hibernate validates the database schema at startup (`spring.jpa.hibernate.ddl-auto=validate`).

## Running the application

```bash
./mvnw spring-boot:run
```

The default configuration listens on port `8080` unless another port is configured.

To run with the development configuration (`src/main/resources/application-dev.properties`), activate the `dev` profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The `dev` profile:
- Listens on port `59919`
- Enables automatic schema updates (`spring.jpa.hibernate.ddl-auto=update`)
- Enables formatted Hibernate SQL debug logging (`logging.level.org.hibernate.SQL=DEBUG`) and `INFO` logging for `fr.ece`
- Seeds initial vehicles from `src/main/resources/seed/vehicles.json` when the `vehicles` table is empty

## Domain model

The domain model includes:

- `BaseEntity`, a mapped superclass with an auto-generated ID
- `Vehicle` (`vehicles`), `Agency` (`agencies`), `Customer` (`customers`), `Employee` (`employees`), `Reservation` (`reservations`), `Contract` (`contracts`), `Payment` (`payments`), `Maintenance` (`maintenances`), and `Equipment` (`equipments`)
- Domain enums: `VehicleCategory`, `VehicleStatus`, `ReservationStatus`, `EmployeeRole`, and `PaymentMethod`

Vehicles are created with a registration number, make, model, category, and daily rental rate. New vehicles default to `AVAILABLE`.
