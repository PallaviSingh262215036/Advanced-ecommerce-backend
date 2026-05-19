# Advanced E-commerce Backend

A production-style backend application built using Spring Boot and MySQL.

This project implements real-world backend engineering concepts including:

- JWT Authentication
- Role-Based Authorization
- Product & Category Management
- Cart Management
- Pagination & Search
- Transactional Workflows
- DTO Architecture
- Entity Relationships
- Spring Security
- JPA/Hibernate Optimization

---

# Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

# Features Implemented

## Authentication
- User Registration
- Login using JWT
- Role-based authorization

## Product Module
- Create Product
- Category Mapping
- Pagination
- Search Products

## Cart Module
- Add to Cart
- Quantity Merge Logic
- Remove from Cart
- Get Current User Cart
- Cart Total Calculation

---

# Security Features

- BCrypt Password Encryption
- JWT Token Authentication
- Protected APIs using Spring Security

---

# API Examples

## Login

POST /auth/login

```json
{
  "email": "rahul@test.com",
  "password": "1234"
}
```

---

## Add Product

POST /products

```json
{
  "name": "iPhone 15",
  "description": "128GB",
  "price": 80000,
  "stock": 5,
  "categoryId": 1
}
```

---

# Project Architecture

Controller → Service → Repository → Database

- DTO-based API design
- EntityGraph optimization for reducing N+1 queries
- Transactional business workflows

---

# Running Locally

## Clone Repository

```bash
git clone <repo-url>
```

## Configure Database

Update:

```text
src/main/resources/application.properties
```

Add your MySQL username/password.

---

## Run Application

```bash
mvn spring-boot:run
```

---

# Upcoming Features

- Order Placement Workflow
- Inventory Management
- Redis Caching
- Docker Support
- Microservices Architecture
- Kafka Event Processing
