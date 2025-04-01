# Blog Platform

This project is a Blog Platform built with Spring Boot. It provides RESTful APIs to manage blog posts and comments.

## Features

- Manage blog posts: create, retrieve, update, and delete.
- Manage comments on posts: add and retrieve.
- Secure endpoints with Spring Security.
- Use Lombok for reducing boilerplate code.

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Lombok

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6+
- MySQL database
- An IDE like IntelliJ IDEA or Eclipse

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/SohaibKhan007/blog-platform.git
   cd blog-platform
   ```

2. **Configure the database:**

   Update the `application.properties` file with your MySQL database credentials.

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Access the application:**

   The application will be running at `http://localhost:8080`.

## API Endpoints

### Posts

- **GET /posts**: Retrieve all posts.
- **GET /posts/{id}**: Retrieve a post by ID.
- **POST /posts**: Create a new post.
- **PUT /posts/{id}**: Update a post.
- **DELETE /posts/{id}**: Delete a post.

### Comments

- **GET /posts/{postId}/comments**: Retrieve comments for a specific post.
- **POST /posts/{postId}/comments**: Add a comment to a specific post.

## Security

- Endpoints are secured using Spring Security.
- Passwords are hashed using Spring Security Crypto.

## Testing

- Unit tests are provided using Spring Boot Starter Test and Mockito.
- Test coverage is measured using Jacoco.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries, please contact the repository owner through GitHub.

---
