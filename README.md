# 📓 Journal App – Backend API
**_A secure and scalable backend for managing personal journal entries_**

[![Java](https://img.shields.io/badge/Language-Java-blue.svg)]()
[![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-green.svg)]()
[![MongoDB](https://img.shields.io/badge/Database-MongoDB-brightgreen.svg)]()
[![JWT](https://img.shields.io/badge/Auth-JWT-orange.svg)]()

---

## 📖 Overview
The **Journal App Backend** is a secure digital platform that enables users to create, manage, and maintain personal journal entries.  
It provides **RESTful APIs** for:
- User Registration & Authentication (JWT)
- CRUD operations for journal entries
- Weekly email updates

This backend ensures **data security, scalability, and maintainability** for personal journaling applications.

---

## 📂 Project Structure

JournalApp/

├── config/            # Security & application configurations

├── controller/        # REST API controllers

├── model/             # Entity models

├── repository/        # MongoDB repositories

├── service/           # Business logic services

├── dto/               # Data transfer objects

├── utils/             # Utility/helper classes

└── resources/         # application.properties and static files


---

## ✨ Features 🌟
- **JWT Authentication & Authorization** – Secure login and role-based access control  
- **User Management** – Register, update profile, delete account  
- **Journal Entry Management** – Create, read, update, and delete journal entries  
- **Admin Features** – Manage users and their roles  
- **Weekly Email Updates** – Automated summaries for users  
- **API Documentation** – Swagger UI integration  
- **Code Quality** – Static analysis with SonarCloud  

---

## 🛠 Tech Stack 💻
- **Language:** Java  
- **Framework:** Spring Boot (2.7.15)  
- **Database:** MongoDB  
- **Authentication:** JWT  
- **API Documentation:** Swagger (Springdoc OpenAPI)  
- **Logging:** SLF4J with Logback  
- **Testing Tools:** Postman, SonarCloud
- **Email Service:** SMTP
- **Build Tool:** Maven / Gradle  

---

## 🚀 How to Start Your Project ▶️

1. **Clone the repository**  
```bash
git clone https://github.com/your-username/JournalEntryAPI.git
cd JournalEntryAPI
```

2. **Configure Environment Variables**  
Create a `.env` file in the root directory with the following keys:

```env
MAIL_USERNAME=your_email@example.com
MAIL_PASSWORD=your_email_password
MONGO_USERNAME=your_mongo_username
MONGO_PASSWORD=your_mongo_password
MONGO_CLUSTER_HOST=your_mongo_cluster_host
MONGO_APP_NAME=Cluster1
```

> Note: These values will be injected into your Spring Boot application via environment variables.

3. **Update `application.yml` or `application-dev.yml`**  
Configure your MongoDB and mail settings using the environment variables:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_CLUSTER_HOST}/${MONGO_APP_NAME}?retryWrites=true&w=majority
  mail:
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
    host: smtp.gmail.com
    port: 587
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

4. **Build & Run the project**  
```bash
mvn clean install
mvn spring-boot:run
```

5. **Access API documentation**  
Swagger UI : http://localhost:8080/swagger-ui/index.html

---

## 👥 User Roles 🧑‍🤝‍🧑

### 1. User
- Register and log in  
- Create, update, and delete personal journal entries  
- View and manage profile  
- Receive weekly email updates  

### 2. Admin
- All user permissions  
- View all journal entries  
- Manage user accounts and roles  

---

## 🔑 Environment Variables 🔒

You must set the following in a `.env` file before running the project:

```env
MAIL_USERNAME=your_email@example.com
MAIL_PASSWORD=your_email_password
MONGO_USERNAME=your_mongo_username
MONGO_PASSWORD=your_mongo_password
MONGO_CLUSTER_HOST=your_mongo_cluster_host
MONGO_APP_NAME=Cluster1
```

- `MAIL_USERNAME` → Your email for sending updates  
- `MAIL_PASSWORD` → Email password or App Password (for Gmail)  
- `MONGO_USERNAME` → MongoDB Atlas username  
- `MONGO_PASSWORD` → MongoDB Atlas password  
- `MONGO_CLUSTER_HOST` → MongoDB cluster host URL (without protocol)  
- `MONGO_APP_NAME` → Name of your MongoDB database/cluster

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

## 📜 References
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [MongoDB Docs](https://www.mongodb.com/docs/)
- [Swagger OpenAPI](https://swagger.io/tools/open-source/open-source-integrations/)
- [SonarCloud](https://sonarcloud.io)
- [JWT.io](https://jwt.io)

---
