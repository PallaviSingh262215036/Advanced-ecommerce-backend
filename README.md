# Advanced E-Commerce Backend

A production-style E-Commerce Backend built with Spring Boot, Spring Security, JWT Authentication, MySQL, Docker, and Railway.

The application supports user authentication, role-based authorization, product management, category management, shopping cart operations, order placement, inventory management, and secure REST APIs.

---

# Live Demo

## Swagger Documentation

https://advanced-ecommerce-backend-production-67e7.up.railway.app/swagger-ui/index.html

## Source Code

https://github.com/PallaviSingh262215036/Advanced-ecommerce-backend

## Backend API

https://advanced-ecommerce-backend-production-67e7.up.railway.app

---

# Features

## Authentication & Authorization

* JWT Authentication
* Secure Login System
* Role-Based Access Control (RBAC)
* ADMIN and USER Roles
* Protected API Endpoints
* Spring Security Integration

## User Management

* User Registration
* User Login
* Get User Details
* Update User
* Delete User
* Search Users
* Pagination Support
* Role Assignment

## Category Management

* Create Categories
* Category Validation
* ADMIN-only Category Management

## Product Management

* Create Products
* View Products
* Product Pagination
* Product Categorization
* Product Stock Management

## Shopping Cart

* Add Products to Cart
* View Cart
* Remove Products from Cart
* Automatic Total Calculation
* Subtotal Calculation

## Order Management

* Place Orders
* View User Orders
* Convert Cart to Order
* Automatic Cart Cleanup

## Inventory Management

* Stock Tracking
* Automatic Stock Deduction
* Insufficient Stock Validation
* Overselling Prevention

## Additional Features

* Global Exception Handling
* Soft Delete Users
* DTO-Based Architecture
* Bean Validation
* Admin Analytics
* Swagger/OpenAPI Documentation
* Docker Support
* Railway Deployment

---

# Tech Stack

## Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* MySQL

## Authentication

* JWT (JSON Web Token)

## Documentation

* Swagger / OpenAPI

## Deployment

* Docker
* Railway

---

# Architecture

User / Client

↓

Spring Boot REST APIs

↓

Controller Layer

↓

Service Layer

↓

Repository Layer (JPA)

↓

MySQL Database

---

# Security

The application uses JWT-based authentication and role-based authorization.

### Roles

#### ADMIN

* Manage Categories
* Manage Products
* Assign Roles
* Access Protected Administrative Operations

#### USER

* Browse Products
* Manage Cart
* Place Orders
* View Personal Orders

---

# Business Flow

User Registration

↓

Login (JWT)

↓

Browse Products

↓

Add Product to Cart

↓

View Cart

↓

Place Order

↓

Stock Validation

↓

Stock Deduction

↓

Order Creation

↓

Cart Cleared

---

# Verified Functionality

### Authentication

* User Registration
* User Login
* JWT Validation
* Protected Endpoints

### Authorization

* ADMIN Access Control
* USER Access Control
* Role-Based Restrictions

### Product Flow

* Category Creation
* Product Creation
* Product Retrieval

### Cart Flow

* Add to Cart
* View Cart
* Remove from Cart

### Order Flow

* Place Order
* View Orders
* Cart Cleanup After Checkout

### Inventory Flow

* Stock Deduction
* Insufficient Stock Validation

### Deployment

* Dockerized Application
* Railway Deployment
* Environment Variable Configuration

---

# API Documentation

Swagger UI:

https://advanced-ecommerce-backend-production-67e7.up.railway.app/swagger-ui/index.html

---

# Environment Variables

Create the following environment variables:

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
JWT_SECRET=
```

# Running Locally

## Clone Repository

```bash
git clone YOUR_GITHUB_REPO_URL
```

## Move Into Project

```bash
cd user_management
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

# Docker

## Build Docker Image

```bash
docker build -t ecommerce-backend .
```

## Run Docker Container

```bash
docker run -p 8080:8080 ecommerce-backend
```

---

# Future Enhancements

* React Frontend
* Refresh Token Support
* Order Status Workflow
* Payment Gateway Integration
* Email Notifications
* Product Reviews & Ratings
---

# Project Status

Completed Backend MVP

Implemented:

* Authentication & Authorization
* Product Catalog
* Category Management
* Shopping Cart
* Order Management
* Inventory Management
* Docker Deployment
* Railway Deployment

Frontend development planned using React.

---

# Author

Built using Spring Boot, Spring Security, JWT, MySQL, Docker, and Railway as a backend-focused E-Commerce application.

