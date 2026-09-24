# SmartFleet - Rental Car Service

A robust Spring Boot RESTful microservice for managing car rentals, dynamic pricing calculations, and audit logging with dual-database persistence (PostgreSQL + MongoDB) and role-based Spring Security.

---

## 🚀 Features

* **Car Fleet Management**: Create, inspect, rent, and return rental vehicles.
* **Dynamic Pricing Engine**: Automated rental rate adjustments based on vehicle brand (e.g., +15% premium for Tesla, 10% discount for Toyota).
* **Dual-Database Architecture**:
  * **PostgreSQL (JPA / Hibernate)**: Stores relational entity data for rental cars.
  * **MongoDB (Spring Data Mongo)**: Stores audit log document records for all rental transactions (`RENT` and `RETURN`).
* **Role-Based Security**: Spring Security HTTP Basic authentication with granular permissions for `USER` and `ADMIN` roles.
* **Global Exception Handling**: Structured REST error responses (`ErrorResponse`) for missing entities or invalid business operations.
* **Secure Environment Configuration**: Externalized credentials managed via environment variable placeholders and `.env` configuration.

---

## 📁 Project Architecture & Package Structure

The source code follows a clear, domain-driven package organization:

```
com.example.spring_project
├── config/
│   └── SecurityConfig.java         # Spring Security & role-based authentication rules
├── controller/
│   └── RentalController.java       # REST Controller defining API endpoints
├── service/
│   └── RentalService.java          # Core business logic & pricing rules
├── repository/
│   ├── CarRepository.java          # Spring Data JPA repository for PostgreSQL
│   └── RentalAuditLogRepository.java # Spring Data MongoDB repository
├── model/
│   ├── RentalCar.java              # JPA Entity representing a rental car
│   ├── RentalAuditLog.java         # MongoDB Document for transaction logs
│   └── Action.java                 # Enum representing rental action types (RENT, RETURN)
├── exception/
│   ├── CarNotFoundError.java       # Custom exception for missing vehicles
│   ├── ErrorResponse.java          # Standardized error response payload
│   └── GlobalExceptionHandler.java  # RestControllerAdvice for exception handling
└── SpringProjectApplication.java   # Spring Boot entry point
```

---

## 🔑 Environment Variables & Credentials Setup

All database credentials and application security passwords are externalized for safety.

1. Copy the sample environment file `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Configure your environment variables in `.env`:

| Variable Name | Description | Default / Example Value |
| :--- | :--- | :--- |
| `DB_URL` | PostgreSQL connection URL | `jdbc:postgresql://localhost:5432/postgres` |
| `DB_USERNAME` | PostgreSQL database username | `postgres` |
| `DB_PASSWORD` | PostgreSQL database password | `postgres` |
| `SPRING_DATA_MONGODB_URI` | MongoDB Connection URI string | `mongodb+srv://<user>:<password>@cluster.mongodb.net/dbname` |
| `SECURITY_USER_NAME` | Regular user username | `driver` |
| `SECURITY_USER_PASSWORD` | Regular user password | `drive123` |
| `SECURITY_ADMIN_NAME` | Administrator username | `manager` |
| `SECURITY_ADMIN_PASSWORD` | Administrator password | `boss2026` |

> ⚠️ **Note**: `.env` is listed in `.gitignore` to prevent sensitive credentials from being committed to version control.

---

## 📡 API Endpoints & Security Permissions

| Method | Endpoint | Description | Required Role |
| :--- | :--- | :--- | :--- |
| `GET` | `/cars` | List all cars in the fleet | Public |
| `GET` | `/cars/{id}` | Get details of a specific car | Public |
| `POST` | `/cars/{id}/rent?customerName={name}` | Rent an available car | `USER` or `ADMIN` |
| `POST` | `/cars/{id}/return?customerName={name}` | Return a rented car | `ADMIN` |
| `POST` | `/cars` | Add a new car to the fleet | `ADMIN` |

---

## 🛠️ Build & Run Instructions

### Prerequisites
* Java 17 or higher
* PostgreSQL & MongoDB instances running (or configured remote connections)

### Compilation & Build
Use the Gradle wrapper to build the application:

```bash
# Windows
.\gradlew.bat build

# Linux / macOS
./gradlew build
```

### Running the Application
```bash
# Windows
.\gradlew.bat bootRun

# Linux / macOS
./gradlew bootRun
```

### Sample API Requests (cURL)

**1. Fetch all cars (Public):**
```bash
curl -X GET http://localhost:8080/cars
```

**2. Rent a car (as `driver`):**
```bash
curl -X POST "http://localhost:8080/cars/1/rent?customerName=John" \
  -u driver:drive123
```

**3. Add a new car (as `manager`):**
```bash
curl -X POST http://localhost:8080/cars \
  -u manager:boss2026 \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Tesla",
    "model": "Model 3",
    "dailyRate": 100.0,
    "rented": false
  }'
```

---

## 📄 License

This project is licensed under the MIT License.
