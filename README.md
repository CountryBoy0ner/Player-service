
# Player Service

## 🏗️ Architecture

This project follows a **Clean Architecture** approach with an additional **Encryption** feature.

### Layers:

- **domain** — Core business logic (e.g., `Player` entity).
- **application** — Use cases and business rules (e.g., `PlayerDeleteUseCase`, `PlayerQueryUseCase`, etc.).
- **infrastructure** — Database access, encryption logic (e.g., `JpaPlayerRepositoryAdapter`, `AesEncryptor`).
- **presentation** — Controllers and DTOs for REST API.
- **config** — Application configuration.

## 🔐 Encryption

The service includes an encryption module using AES for sensitive data:
- `Encryptor` (interface)
- `AesEncryptor` (implementation)

## 🌐 API Endpoints

| Method | Endpoint                  | Description                      |
|--------|---------------------------|----------------------------------|
| POST   | `/players/register`       | Register a new player            |
| GET    | `/players/all`            | Get all players                  |
| GET    | `/players/{id}`           | Get player details by ID         |
| DELETE | `/players/{id}`           | Delete a player by ID            |

## 📊 Metrics

Metrics are exposed via **Spring Actuator**:

- Prometheus scrape endpoint: `http://localhost:8080/actuator/prometheus`
- Metrics info: `http://localhost:8080/actuator/metrics`

Includes:
- JVM (memory, GC, threads)
- HTTP (requests, response times)
- Database (connection pool)
- Application (startup, readiness)

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Spring Actuator
- Prometheus + Grafana
- Checkstyle / PMD / SpotBugs

## 📂 Folder Structure

```
src/
├── application/
│   ├── repository/         // Interfaces for repositories
│   ├── usecase/            // Use cases (interfaces + implementations)
├── config/
│   ├── BeanConfig
│   └── SecurityConfig
├── domain/
│   └── model/              // Core domain model (Player)
├── infrastructure/
│   ├── crypto/             // AES encryption logic
│   └── persistence/        // Database access (JPA, Mapper)
├── presentation/
│   ├── controller/         // REST Controllers
│   ├── dto/                // DTOs
│   ├── exception/          // Error handling
│   └── mapper/             // DTO-entity mappers
```

## 📝 Notes

- Follows **Clean Architecture** principles.
- Sensitive data is encrypted with AES.
- Metrics available for monitoring via Prometheus.
- CI/CD pipeline integrated via GitHub Actions.

