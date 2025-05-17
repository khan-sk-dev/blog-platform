# 🚀 Blog Platform

<div align="center">
  
![Blog Platform](https://img.shields.io/badge/Blog-Platform-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring-Boot-green)
![Java](https://img.shields.io/badge/Java-17-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

</div>


## 📝 Description

A robust RESTful Blog Platform built with Spring Boot. This application provides comprehensive APIs to manage blog posts and comments with proper authentication and security mechanisms.

## ✨ Features

- 📃 **Blog Post Management**
  - Create, retrieve, update, and delete posts
  - Pagination and sorting capabilities
  - Search functionality
  
- 💬 **Comment System**
  - Add and retrieve comments on specific posts
  - Nested comments support
  
- 🔒 **Security**
  - JWT-based authentication
  - Role-based authorization
  - Secure API endpoints

- 🛠️ **Additional Features**
  - Exception handling with proper error responses
  - Input validation
  - Logging and monitoring
  - API documentation with Swagger

## 🔧 Technology Stack

<div align="center">
  
| Technology | Description |
|------------|-------------|
| ![Java](https://img.shields.io/badge/Java-17-orange) | Core programming language |
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) | Application framework |
| ![Spring MVC](https://img.shields.io/badge/Spring-MVC-lightgreen) | Web framework |
| ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-yellowgreen) | Data persistence |
| ![Spring Security](https://img.shields.io/badge/Spring-Security-darkgreen) | Authentication & authorization |
| ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue) | Database |
| ![Maven](https://img.shields.io/badge/Maven-3.6+-purple) | Build tool |
| ![Lombok](https://img.shields.io/badge/Lombok-Latest-red) | Boilerplate code reducer |
| ![Swagger](https://img.shields.io/badge/Swagger-3.0-darkblue) | API documentation |

</div>

## 📋 Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven 3.6+
- MySQL database
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## ⚙️ Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/khan-sk-dev/blog-platform.git
cd blog-platform
```

### 2. Configure the database

Create a MySQL database and update the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

### 5. Access the application

The application will be running at [http://localhost:8080](http://localhost:8080)

Swagger API documentation will be available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🔌 API Endpoints

### Posts

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/posts` | Retrieve all posts (with pagination) |
| GET | `/api/posts/{id}` | Retrieve post by ID |
| POST | `/api/posts` | Create a new post |
| PUT | `/api/posts/{id}` | Update an existing post |
| DELETE | `/api/posts/{id}` | Delete a post |
| GET | `/api/posts/search?keyword=value` | Search posts by keyword |

### Comments

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/posts/{postId}/comments` | Retrieve all comments for a post |
| POST | `/api/posts/{postId}/comments` | Add a comment to a post |
| PUT | `/api/comments/{id}` | Update an existing comment |
| DELETE | `/api/comments/{id}` | Delete a comment |

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

## 🔒 Security

- Authentication is handled using JWT (JSON Web Tokens)
- Password encryption using BCrypt
- Role-based authorization (ADMIN, USER)
- CSRF protection and XSS prevention

## 🧪 Testing

The project includes comprehensive tests:

```bash
# Run all tests
mvn test

# Generate test coverage report
mvn jacoco:report
```

Test coverage can be viewed at `target/site/jacoco/index.html`

## 🏗️ Project Structure

```
blog-platform/
├── src/
│   ├── main/
│   │   ├── java/com/khan/blogplatform/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── BlogPlatformApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/khan/blogplatform/
│           ├── controller/
│           ├── repository/
│           └── service/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## 📊 Database Schema

<div align="center">
  
```mermaid
erDiagram
    User {
        Long id PK
        String username
        String email
        String password
        String role
    }
    Post {
        Long id PK
        String title
        String content
        DateTime createdAt
        DateTime updatedAt
        Long userId FK
    }
    Comment {
        Long id PK
        String content
        DateTime createdAt
        Long postId FK
        Long userId FK
        Long parentId FK
    }
    Tag {
        Long id PK
        String name
    }
    PostTag {
        Long postId FK
        Long tagId FK
    }
    
    User ||--o{ Post : "creates"
    User ||--o{ Comment : "writes"
    Post ||--o{ Comment : "has"
    Comment ||--o{ Comment : "has replies"
    Post }|--|| PostTag : "has"
    Tag }|--|| PostTag : "belongs to"
```

</div>

## 🚀 Future Enhancements

- [ ] Add social login integration (Google, GitHub)
- [ ] Implement caching for improved performance
- [ ] Add media upload functionality
- [ ] Create user profiles with additional details
- [ ] Implement blog post categories and tags
- [ ] Add real-time notifications
- [ ] Add analytics dashboard
- [ ] Implement a rate limiter for APIs

## 👥 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## 📞 Contact

Khan - [@github_username](https://github.com/khan-sk-dev)

Project Link: [https://github.com/khan-sk-dev/blog-platform](https://github.com/khan-sk-dev/blog-platform)

---

<div align="center">
  
Made with ❤️ by [khan-sk-dev](https://github.com/khan-sk-dev)

</div>
