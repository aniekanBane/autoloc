# AutoLoc API

Spring Boot API for the AutoLoc vehicle-rental domain.

## Technology stack

- Java 25
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA and Hibernate
- MySQL
- Spring Security
- Spring Boot Actuator and Micrometer Prometheus
- Maven (the Maven Wrapper is included)

## Requirements

- JDK 25
- A running MySQL server
- Docker, if you want to run the monitoring stack

## Configuration

The application imports an optional local `.env` file and reads these database
variables:

```properties
DB_URL=jdbc:mysql://localhost/autoloc_db?createDatabaseIfNotExist=true
DB_USER=root
DB_PASSWORD=<your_password>
```

Create `.env` in the project root and set values appropriate for your local
database.

The default profile uses `spring.jpa.hibernate.ddl-auto=validate`, so the
database schema must already exist and match the JPA mappings.

## Running the application

Start the API with the default profile:

```bash
./mvnw spring-boot:run
```

It listens on `http://localhost:8080` by default.

For local development, activate the `dev` profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The `dev` profile:

- Updates the schema automatically with Hibernate.
- Enables formatted Hibernate SQL logging.
- Sets `fr.ece` logging to `INFO`.
- Seeds `src/main/resources/seed/vehicles.json` when the `vehicles` table is
  empty.

## API

The current controller endpoints are:

| Method | Path | Access |
| --- | --- | --- |
| `GET` | `/api/public/ping` | Public |
| `GET` | `/api/hello` | Authenticated users |
| `GET` | `/api/admin/report` | Users with the `ADMIN` role |

The project includes example requests in
`src/main/java/fr/ece/autoloc/AutolocApi.http`.

Authentication uses HTTP Basic with in-memory demonstration users:

| Username | Password | Role |
| --- | --- | --- |
| `student` | `student123` | `USER` |
| `admin` | `admin123` | `ADMIN` |

These credentials are for development and demonstrations only. The users are
recreated on every application start and are not suitable for production.

Example:

```bash
curl http://localhost:8080/api/public/ping
curl -u student:student123 http://localhost:8080/api/hello
curl -u admin:admin123 http://localhost:8080/api/admin/report
```

Invalid domain input is returned as an RFC 9457 `ProblemDetail` response.
Application errors include a machine-readable `code` property.

## Monitoring

The API exposes these unauthenticated actuator endpoints:

- `GET /actuator/health`
- `GET /actuator/prometheus`

Start Prometheus and Grafana with:

```bash
docker compose up -d
```

This starts:

- Prometheus at `http://localhost:9090`
- Grafana at `http://localhost:3000`

Prometheus is configured to scrape the API at
`host.docker.internal:8080/actuator/prometheus`. Start the API on the host
before starting the monitoring stack.

Stop the monitoring services with:

```bash
docker compose down
```

## Domain model

The model is organized into these areas:

- **Inventory:** `Vehicle`, `VehicleImage`, `Equipment`, `Maintenance`, and
  vehicle categories/statuses.
- **Organization:** `Agency`, `Employee`, and employee roles.
- **Customer:** `Customer`.
- **Rental:** `Reservation`, `Contract`, and reservation statuses.
- **Billing:** `Payment` and payment methods.
- **Shared domain types:** `BaseEntity`, `Person`, `EmailAddress`,
  `MonetaryAmount`, and domain validation exceptions.

Entity relationship fetch and cascade decisions are documented in
[`docs/fetch-strategy.md`](docs/fetch-strategy.md). Authentication and
authorization details are documented in
[`docs/security-notes.md`](docs/security-notes.md).

## Testing

No test configured yet.
