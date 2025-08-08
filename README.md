# 📓 Journal App – Backend API
**_A secure and scalable backend for managing personal journal entries_**

[![Java](https://img.shields.io/badge/Language-Java-blue.svg)]()
[![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-green.svg)]()
[![MongoDB](https://img.shields.io/badge/Database-MongoDB-brightgreen.svg)]()
[![JWT](https://img.shields.io/badge/Auth-JWT-orange.svg)]()

---

## 📑 Table of Contents
- [📖 Overview]
- [🎯 Objectives]
- [📌 Scope]
- [🛠 Tech Stack]
- [⚙️ Requirements]
- [📂 Project Structure]
- [🔍 Features]
- [🧪 Testing]
- [✅ Advantages]
- [🚀 Future Improvements]
- [📜 References]

---

## 📖 Overview
The **Journal App Backend** is a secure digital platform that enables users to create, manage, and maintain personal journal entries.  
It provides **RESTful APIs** for:
- User Registration & Authentication (JWT)
- CRUD operations for journal entries
- Weekly email updates

This backend ensures **data security, scalability, and maintainability** for personal journaling applications.

---

## 🎯 Objectives
- Develop a secure, scalable backend for journal management
- Provide full CRUD operations for journal entries
- Implement **JWT authentication** for secure access
- Send **weekly email summaries** to users
- Provide **admin APIs** for managing users and roles

---

## 📌 Scope
- REST API for:
  - User registration, login, profile management
  - Journal entry management (Create, Read, Update, Delete)
- JWT-based authentication and role-based authorization
- Public APIs for weekly updates
- Modular architecture for scalability
- Optional email notification integration

---

## 🛠 Tech Stack
**Language:** Java  
**Framework:** Spring Boot (2.7.15)  
**Database:** MongoDB  
**Authentication:** JWT (Java JWT 0.12.5)  
**API Documentation:** Swagger (OpenAPI)  
**Logging:** SLF4J with Logback  
**Build Tool:** Maven / Gradle  
**Version Control:** Git  
**Testing Tools:** Postman, SonarCloud  
**Optional Services:** SMTP (email service)  
**IDE:** IntelliJ IDEA  

---

## ⚙️ Requirements

**Functional:**
- User registration & login with JWT authentication
- Profile management (update, delete)
- Journal CRUD operations with ownership validation
- Admin APIs for user management
- Weekly email updates for users
- Secure API access with role-based controls

**Non-Functional:**
- Response time < 500ms for most API calls
- HTTPS-secured transactions
- Clear API documentation via Swagger
- Logging of key events & monitoring system health

---

## 📂 Project Structure

JournalApp/

├── config/ # Security & application configurations

├── controller/ # REST API controllers

├── model/ # Entity models

├── repository/ # MongoDB repositories

├── service/ # Business logic services

├── dto/ # Data transfer objects

├── utils/ # Utility classes

└── resources/ # application.properties, static files


---

## 🔍 Features
- **Authentication & Authorization:** JWT-based security with role control
- **User Profile Management:** View, update, delete profile data
- **Journal Entry Management:** Secure CRUD for personal entries
- **Admin Panel APIs:** Manage users, roles
- **Email Service:** Weekly summaries via SMTP
- **Public APIs:** Retrieve registered users
- **API Documentation:** Swagger UI for interactive API testing

---

## 🧪 Testing

**Static Code Analysis – SonarCloud**  
- Tool: [SonarCloud](https://sonarcloud.io/project/overview?id=smitkachhadiya_JournalEntryAPI)  
- Integrated with CI pipeline for quality checks  
- Detects code smells, bugs, and vulnerabilities before merge  

**API Testing – Postman**  
- Positive and negative test scenarios  
- JWT authentication flows validated  
- Comprehensive Postman Collection covering all endpoints  

---

## ✅ Advantages
- **Security:** JWT authentication & HTTPS
- **Scalability:** Modular Spring Boot architecture
- **Transparency:** Swagger API documentation
- **Maintainability:** Code quality ensured with SonarCloud
- **User Experience:** Weekly update functionality

---

## 🚀 Future Improvements
- Advanced admin analytics dashboard
- Two-factor authentication
- Multi-language support
- Real-time notifications (WebSocket)
- Role-based granular permissions
- Integration with cloud email APIs (SendGrid, AWS SES)

---

## 📜 References
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [MongoDB Docs](https://www.mongodb.com/docs/)
- [Swagger OpenAPI](https://swagger.io/tools/open-source/open-source-integrations/)
- [SonarCloud](https://sonarcloud.io)
- [JWT.io](https://jwt.io)

---
