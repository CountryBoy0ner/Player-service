
# 🏆 Player Service

**Player Service** is a Spring Boot microservice designed to manage players. It provides a REST API to:

- Register new players
- Retrieve a list of all players
- Get player details by ID
- Delete players by ID

The service uses **PostgreSQL** as its database and exposes **metrics** for monitoring via **Spring Actuator** and **Prometheus**.

---

## 🚀 API Endpoints

| HTTP Method | URL                  | Description                |
|-------------|----------------------|----------------------------|
| `POST`      | `/players/register`  | Register a new player      |
| `GET`       | `/players/all`       | Get all players            |
| `GET`       | `/players/{id}`      | Get player details by ID   |
| `DELETE`    | `/players/{id}`      | Delete a player by ID      |

---

## 📊 Metrics

Metrics are available at:

```
http://localhost:8080/actuator/prometheus
```

Example metrics exposed:

- **JVM**: Memory, GC, threads (`jvm.memory.used`, `jvm.gc.*`)
- **HTTP**: Requests and response times (`http.server.requests`)
- **Database**: Connection pool status (`hikaricp.connections.active`)
- **Application**: Startup time, readiness (`application.ready.time`, `application.started.time`)

Full list of metrics:

```
http://localhost:8080/actuator/metrics
```

---

## 🧩 Monitoring Integration

- Metrics can be scraped by **Prometheus**.
- Visualization and dashboards via **Grafana**.

---

## 📌 Technology Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL**
- **Spring Actuator**
- **Prometheus & Grafana** (monitoring)
- **Maven Checkstyle / PMD / SpotBugs** (linting and static analysis)
