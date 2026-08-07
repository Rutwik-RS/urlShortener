# 🔗 URL Shortener API

A robust URL Shortener REST API built with **Spring Boot** that allows users to create short URLs, redirect to original links, and track click analytics. The project includes authentication, URL expiration, and analytics features.

## ✨ Features

* User Registration & Login (JWT Authentication)
* Shorten long URLs
* Custom short code generation
* Redirect to original URL
* Click analytics
* URL expiration support
* Secure REST APIs
* MySQL database integration
* Input validation and exception handling

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT Authentication
* MySQL
* Maven

## 📂 Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
└── exception
```

## 🚀 Getting Started

### Clone the repository

```bash
git clone https://github.com/your-username/url-shortener-api.git
```

### Configure Database

Update the database credentials in `application.properties`.

### Run the application

```bash
mvn spring-boot:run
```

The API will start at:

```text
http://localhost:8080
```

## 📌 API Endpoints

| Method | Endpoint                | Description              |
| ------ | ----------------------- | ------------------------ |
| POST   | /api/auth/register      | Register user            |
| POST   | /api/auth/login         | Login user               |
| POST   | /api/url/shorten        | Create short URL         |
| GET    | /{shortCode}            | Redirect to original URL |
| DELETE | /api/url/{id}           | Delete URL               |

## 📈 Future Improvements

* QR Code Generation
* Rate Limiting
* Redis Caching
* Docker Support
* Swagger/OpenAPI Documentation
* Custom Domains

## 👨‍💻 Author

**Rutwik RS**
