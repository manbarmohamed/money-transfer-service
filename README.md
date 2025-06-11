# Money Transfer Service

A robust, transactional backend service for managing users, accounts, and executing secure money transfers between accounts. Built with Java and Spring Boot, this project demonstrates key banking operations with a focus on data integrity and modular design.

---

## Features

- **User Management**
  - Create, view, update, and delete users.
  - Each user has a name and email.
- **Account Management**
  - Create, retrieve, and update bank accounts linked to users.
  - Each user can have only one account.
  - Unique account numbers generated for each account.
  - Account balance management.
- **Money Transfers**
  - Securely transfer funds between accounts.
  - Validates sufficient sender balance and account existence.
  - Prevents concurrent modifications with pessimistic locking.
  - Transactional operations ensure atomicity and rollback on failure.
  - Detailed transaction records for each transfer.

---

## Technologies

<p>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="Java" width="42" height="42"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="Spring Boot" width="42" height="42"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" alt="Maven" width="42" height="42"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-original.svg" alt="Hibernate" width="42" height="42"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" alt="Docker" width="42" height="42"/>
</p>

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **Jakarta Validation**

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Build & Run

```sh
# Clone the repository
git clone https://github.com/manbarmohamed/money-transfer-service.git
cd money-transfer-service

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The service will start on `http://localhost:8080`.

---

## API Overview

### User APIs

- `POST /users` — Create a new user
- `GET /users/{id}` — Retrieve user by ID
- `GET /users` — List all users
- `DELETE /users/{id}` — Delete a user

### Account APIs

- `POST /accounts` — Create an account for a user
- `GET /accounts/{id}` — Retrieve account by ID
- `GET /accounts` — List all accounts
- `PUT /accounts/{id}` — Update account balance

### Money Transfer APIs

- `POST /transfers` — Transfer funds between accounts

**Sample Transfer Request:**
```json
{
  "fromAccount": "AC0000000001",
  "toAccount": "AC0000000002",
  "amount": 100.0
}
```

**Sample Transfer Response:**
```json
{
  "message": "Transfer completed successfully",
  "newSenderBalance": 900.0,
  "newReceiverBalance": 1100.0
}
```

---

## Transaction Safety

- Transfers and account updates are wrapped in database transactions.
- Pessimistic locking ensures no double-spending or race conditions.
- All validations (account existence, sufficient funds, etc.) are enforced server-side.

---

## Project Structure

```
src/main/java/com/bank/moneytransferservice/
├── controller/      # REST controllers
├── service/         # Business logic
├── repository/      # Data access
├── entity/          # JPA entities
├── dto/             # Data Transfer Objects
├── mapper/          # Entity-DTO mappers
```

---

## Contributing

Contributions are welcome! Please submit issues and pull requests for enhancements or bug fixes.

---

## License

This project is licensed under the MIT License.

---

## Author

- [manbarmohamed](https://github.com/manbarmohamed)
