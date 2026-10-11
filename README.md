# Java Clean Architecture + MVC API

A backend REST API built with **Spring Boot**, structured using **Clean Architecture** with an **MVC** presentation layer. Currently implements user registration and login, with a **PostgreSQL** database accessed through **Hibernate (JPA)**.

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java 25 |
| Framework | Spring Boot 4.1 |
| Architecture | Clean Architecture (Domain / Data / Presentation) |
| Web layer | Spring MVC (`@RestController`) |
| Persistence | Spring Data JPA, Hibernate |
| Database | **PostgreSQL 18** (JDBC driver `org.postgresql:postgresql`) |
| Security | Spring Security, BCrypt password hashing |
| Validation | Jakarta Bean Validation |
| Build tool | Maven |

## Database (PostgreSQL)

The app stores data in **PostgreSQL**. Hibernate creates and updates the `users` table automatically (`ddl-auto: update`), so you only need to create the empty database.

| Setting | Value |
|---|---|
| Database name | `tododb` |
| Host / Port | `localhost` / `5432` |
| Table | `users` (unique constraints on `username` and `email`) |
| Passwords | Stored as BCrypt hashes (`$2a$10$...`), never plain text |

Tip: you can browse the data with **pgAdmin 4** under `tododb > Schemas > public > Tables > users`.

## Architecture

The project follows the dependency rule: **outer layers depend on inner layers, never the other way around.** The domain layer has no dependency on Spring, JPA, or Hibernate.

```
presentation  ──►  domain  ◄──  data
                      ▲
                    config   (wires everything together)
```

| Layer | Responsibility | Contents |
|---|---|---|
| **Domain** | Business rules, framework-free | Models, repository interfaces, use cases, domain exceptions, `PasswordHasher` interface |
| **Data** | Database and external implementations | JPA entities, Spring Data repositories, mappers, repository implementations, BCrypt implementation |
| **Presentation** | HTTP (MVC) | Controllers, request/response DTOs, global exception handler |
| **Config** | Dependency wiring | Use case beans, Spring Security rules |

### Project structure

```
src/main/java/com/example/todoapi/todo_api/
├── config/             # SecurityConfig, UseCaseConfig
├── domain/
│   ├── exception/      # DuplicateUsernameException, InvalidCredentialsException, ...
│   ├── model/          # User
│   ├── repository/     # UserRepository (interface)
│   ├── security/       # PasswordHasher (interface)
│   └── usecase/        # RegisterUseCase, LoginUseCase
├── data/
│   ├── entity/         # UserEntity (@Entity)
│   ├── jpa/            # UserJpaRepository
│   ├── mapper/         # UserMapper
│   ├── repository/     # UserRepositoryImpl
│   └── security/       # BCryptPasswordHasher
├── presentation/
│   ├── controller/     # AuthController
│   ├── dto/            # RegisterRequest, LoginRequest, UserResponse
│   └── exception/      # GlobalExceptionHandler
└── utils/              # Constant
```

## API Endpoints

Base path: `/api/auth`

| Method | Endpoint | Description | Success | Errors |
|---|---|---|---|---|
| POST | `/api/auth/register` | Register a new user | `201 Created` | `400` validation, `409` duplicate username or email |
| POST | `/api/auth/login` | Log in with username and password | `200 OK` | `400` validation, `401` invalid credentials |

### Register

```json
{
  "firstName": "Juan",
  "middleName": "Santos",
  "lastName": "Dela Cruz",
  "username": "juan123",
  "password": "secret123",
  "age": 25,
  "address": "Taguig City",
  "email": "juan@mail.com"
}
```

- `username` and `email` must be unique.
- Passwords are hashed with BCrypt and never returned in responses.

### Login

```json
{
  "username": "juan123",
  "password": "secret123"
}
```

## Getting Started

### Prerequisites

- JDK 25
- Maven (or use the included `mvnw`)
- PostgreSQL

### 1. Create the database

```sql
CREATE DATABASE tododb;
```

### 2. Configure `src/main/resources/application.yaml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tododb
    username: your_db_username
    password: your_db_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3. Run the app

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API starts on `http://localhost:8080`. Tables are created automatically by Hibernate.

## Roadmap

- [x] User registration with validation and duplicate checks
- [x] User login with BCrypt password verification
- [x] Global exception handling
- [ ] JWT authentication
- [ ] Role-based access control (RBAC)
- [ ] Todo CRUD endpoints with pagination
- [ ] Unit and integration tests
- [ ] Docker setup

## Author

James Jude Bautista
