## Project Overview

This project is a backend simulation for a **RESTful API** that manages information about **Collets** (Spannzangen) used in the **powRgrip®** technology. The system is designed to allow potential customers and other software to interact with Collet data via a server interface.

### Features Implemented:
- **GET** `/spannzangen`: Retrieve a list of all stored collets.
- **GET** `/spannzange/{id}`: Retrieve a specific collet by its ID.
- **POST** `/spannzange`: Add a new collet to the system. A validation is performed on the attributes, and a unique ID is assigned.
- **DELETE** `/spannzange/{id}`: Remove a specific collet by its ID from the system.
- **Validation**: The system validates the size, type, and article number of a collet before adding it to the system.

### Technologies:
- **Java** with **Quarkus** for fast, lightweight backend services.
- **JUnit** for unit testing.
- **RestAssured** for integration testing.
- **Maven** for dependency and build management.

## Project Structure (MVC Pattern)

This project is structured following the **Model-View-Controller (MVC)** design pattern to ensure good separation of concerns and maintainability.

- **Model (`model`)**: Defines the Collet entity with attributes like `id`, `size`, `type`, and `articleNumber`.
- **Controller (`controller`)**: Handles the REST API endpoints and client interactions.
- **Service (`service`)**: Contains the business logic and validation for Collet attributes.
- **Repository (`repository`)**: Manages the in-memory storage of Collet data.
- **View (`view`)**: Would be the front-end interface for user interaction but not implemented in this project.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd spannzangen-api
```

### 2. Run the Application

To run the application in development mode using Quarkus:
    
```bash
./mvnw quarkus:dev
```

The server will start at `http://localhost:8080`.

### 3. Test the Application

You can run the tests using Maven:

```bash
./mvnw test
```

### 4. Access the API

You can interact with the API using the following endpoints:

- **GET** all collets: `http://localhost:8080/spannzangen`
- **GET** a collet by ID: `http://localhost:8080/spannzange/{id}`
- **POST** to add a new collet: `http://localhost:8080/spannzange`
- **DELETE** a collet by ID: `http://localhost:8080/spannzange/{id}`

### 4.1 Example Requests

- **GET** all collets:
    ```bash
    curl http://localhost:8080/spannzangen
    ```
- **GET** a collet by ID:
    ```bash
    curl http://localhost:8080/spannzange/1
    ```
- **POST** to add a new collet:
    ```bash
    curl -X POST http://localhost:8080/spannzange -H "Content-Type: application/json" -d '{"size": 6, "type": "MB", "articleNumber": "1234.56789"}'
    ```
- **DELETE** a collet by ID:
    ```bash
    curl -X DELETE http://localhost:8080/spannzange/1
    ```