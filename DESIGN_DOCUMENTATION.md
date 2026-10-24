# Pet Care Clinic Management System
## Design Documentation

### 1. System Overview

The Pet Care Clinic Management System is a Spring Boot application developed to manage pet registration and clinic records. It provides REST APIs for CRUD operations and calculates consultation fees according to species and age.

### 2. System Architecture

The application follows a layered architecture.

```text
Client (HTTP Request)
        |
        v
PetController
        |
        v
PetService
        |
        v
PetRepository
        |
        v
MySQL Database
```

**Controller Layer:** Receives HTTP requests and returns responses.

**Service Layer:** Implements business logic, calculates consultation fees, and handles pet operations.

**Repository Layer:** Uses Spring Data JPA to communicate with the database.

**Model Layer:** Defines the Pet entity and its fields.

**Exception Handling:** Handles missing pet records and invalid request data.

### 3. Module Design

#### 3.1 Pet Management Module
- Add a new pet.
- View all registered pets.
- Retrieve a pet using its ID.
- Update existing pet details.
- Delete a pet using its ID.

#### 3.2 Consultation Fee Module
Calculates the consultation fee based on species and age.

#### 3.3 Validation Module
Validates pet details, including name, species, owner name, and age.

#### 3.4 Exception Handling Module
Returns suitable error responses for invalid data and pets that cannot be found.

### 4. Database Design

**Database:** `petcareclinic`

**Table:** `pets`

| Field | Data Type | Description |
|---|---|---|
| id | BIGINT | Primary key, automatically generated |
| name | VARCHAR | Pet name |
| species | VARCHAR | Type of pet |
| age | INTEGER | Age of pet |
| owner_name | VARCHAR | Pet owner's name |

The actual SQL column names may vary depending on the entity mapping and Hibernate naming strategy.

The consultation fee is a transient field calculated by the service and is not persisted in the database.

### 5. Data Flow

**Adding a Pet**
1. The client sends a POST request.
2. The controller validates the request.
3. The service saves the pet through the repository.
4. The service calculates the consultation fee.
5. The API returns the saved pet details.

**Viewing a Pet**
1. The client sends a GET request.
2. The service retrieves the pet from the repository.
3. If the pet exists, the service calculates its fee.
4. The controller returns the pet details.

**Updating a Pet**
1. The client sends a PUT request with the pet ID and new details.
2. The service checks whether the pet exists.
3. The service updates and saves the details.
4. The updated pet and fee are returned.

**Deleting a Pet**
1. The client sends a DELETE request with the pet ID.
2. The service checks whether the pet exists.
3. The repository deletes the pet.
4. The API returns a successful deletion status.

### 6. Technology Stack

- Programming Language: Java 21
- Framework: Spring Boot 3
- Database: MySQL
- Persistence: Spring Data JPA and Hibernate
- API Style: REST
- Validation: Jakarta Bean Validation
- Testing: JUnit and Mockito
- Development Environment: IntelliJ IDEA

### 7. Design Benefits

- Separation of concerns through layered architecture.
- Easier maintenance and testing.
- Centralized business logic.
- Database access through repository methods.
- Input validation and consistent exception handling.

