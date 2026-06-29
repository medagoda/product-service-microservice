# Product Service

A Spring Boot microservice for managing products in the e-commerce backend system.

## Tech Stack
- Java 17, Spring Boot 3.x
- Spring Web, Spring Data JPA, Hibernate
- PostgreSQL (Dockerized)
- Swagger/OpenAPI
- JUnit 5 + Mockito
- Docker

## API Endpoints
| Method | Endpoint              | Description           |
|--------|-----------------------|------------------------|
| POST   | `/api/products`       | Create a new product   |
| GET    | `/api/products/{id}`  | Get product by ID      |
| DELETE | `/api/products/{id}`  | Delete product by ID   |

## Run Locally with Docker Compose
```bash
docker compose up --build
```
App runs on `http://localhost:8081`
Swagger UI: `http://localhost:8081/swagger-ui.html`

## Docker Hub
Public image: https://hub.docker.com/r/nimesha123/product-service

Run it directly:
```bash
docker pull nimesha123/product-service:latest
docker run -p 8081:8081 \
  -e DB_URL=jdbc:postgresql://<your-postgres-host>:5432/product_db \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=postgres \
  nimesha123/product-service:latest
```

## Performance Testing
JMeter load test plan available at `performance-tests/get-product-load-test.jmx`
- 50 threads, 10s ramp-up, 10 loop count → 500 requests, 0% error rate, ~18ms avg response time