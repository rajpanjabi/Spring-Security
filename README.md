# 🔐 Spring Boot Authentication API with Spring Security + JWT

This project demonstrates how to build a secure authentication system in a Spring Boot application using **Spring Security** and **JWT (JSON Web Tokens)**.

It supports:
- ✅ User Registration
- ✅ User Login
- ✅ Password hashing with BCrypt
- ✅ JWT Token generation
- ✅ Stateless Authentication
- ✅ Role-based access (ready for extension)

---

## 📦 Tech Stack

- Java 17+
- Spring Boot 3.3+
- Spring Security 6
- JWT (`jjwt`)
- Spring Data JPA
- PostgreSQL / H2 (Switchable)
- Maven

---

## 📁 Project Structure
src/
├── controller/
│   └── AuthController.java
├── service/
│   ├── AuthService.java
│   ├── UserService.java
│   └── JWTService.java
├── dto/
│   ├── RegisterRequestDTO.java
│   ├── LoginRequestDTO.java
│   ├── RegisterResponseDTO.java
│   └── LoginResponseDTO.java
├── entity/
│   ├── User.java
│   └── Role.java
├── config/
│   └── SecurityConfig.java
├── repository/
│   └── UserRepository.java
└── …

## 📬 API Endpoints

### 📌 Register a New User

POST /api/auth/register
Body 
{
  "username": "testUser",
  "password": "password123"
}


### 📌 Login User

POST /api/auth/login
Body
{
  "username": "testUser",
  "password": "password123"
}

Response
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

- Use this token for protected endpoints:

## Security Setup
	•	Passwords are hashed using BCrypt.
	•	Authentication is stateless via JWTs — no server-side sessions.
	•	Only /api/auth/** routes are public; everything else is protected.
	•	Role-based access is ready (e.g. ROLE_USER, ROLE_ADMIN).

## Testing

You can test using:
	- Postman
    -http://localhost:8080/api/auth/register 
   
   Body {
  "username": "testuser",
  "password": "password123"
  }
  - http://localhost:8080/api/auth/login
   Body {
  "username": "testuser",
  "password": "password123"
}

##  Set environment variables in application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db

spring.datasource.username=your_user

spring.datasource.password=your_pass

jwt.secret=your_jwt_secret_key_here

jwt.expiration=3600000 # 1 hour in ms

spring.jpa.hibernate.ddl-auto=update


##  Build & Run
./mvnw spring-boot:run
