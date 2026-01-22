# Banking System API
[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Yes-blue)](https://www.docker.com/)
[![Terraform](https://img.shields.io/badge/Terraform-Yes-blue)](https://www.terraform.io/)
[![Status](https://img.shields.io/badge/Status-In%20Development-orange)](https://github.com/)




REST API developed with **Java and Spring Boot** that simulates a banking/financial system.  
This project was created with a strong focus on **backend best practices**, **layered architecture**, and **modern DevOps concepts**.

The application is part of my professional portfolio and is being continuously improved to reflect real-world enterprise development practices.

---

## 📌 Overview

The application provides REST endpoints for managing:

- Clients
- Bank accounts
- Financial transactions

The system uses **MySQL** as a relational database and follows a clear separation of responsibilities between **Controller, Service, and Repository** layers.

In addition to the backend, the project includes **Docker containerization** and **Infrastructure as Code using Terraform**, preparing the application for cloud environments such as **AWS**.

---

## 🧩 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **REST API**
- **MySQL**
- **Maven**
- **Docker**
- **Docker Compose**
- **Terraform**
- **Swagger / OpenAPI**
- **JWT (authentication concepts)**

---

## 🏗️ Project Architecture

com.bank
├─ controller # REST endpoints
├─ service # Business logic
├─ repository # Data access layer (JPA)
├─ model # Domain entities
├─ dto # Data Transfer Objects
├─ security # Security configuration
├─ exception # Global exception handling
└─ BankingSystemApplication.java


This structure follows common enterprise patterns and makes the application easier to maintain, test, and scale.

---

## ▶️ Running the Project Locally

### Requirements
- Java 17+
- Maven
- Docker
- MySQL (or Docker)

### Run with Maven
```bash
mvn spring-boot:run
Run with Docker
docker build -t banking-app .
docker run -p 8080:8080 banking-app
📚 API Documentation
The API is automatically documented using Swagger.

After starting the application, access:

http://localhost:8080/swagger-ui.html

