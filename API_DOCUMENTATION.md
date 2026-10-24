# Pet Care Clinic Management System
## API Documentation

### 1. Overview
The Pet Care Clinic Management System provides REST APIs to manage pet details. It supports adding, viewing, updating, and deleting pets. It also calculates consultation fees based on the pet's species and age.

**Base URL:** `http://localhost:8080`

**API Base Path:** `/api/pets`

**Technology:** Java 21, Spring Boot, Spring Data JPA, MySQL.

### 2. Add a New Pet

- **Method:** POST
- **Endpoint:** `/api/pets`
- **Purpose:** Registers a new pet in the clinic.

**Request Body:**
```json
{
  "name": "Bruno",
  "species": "Dog",
  "age": 3,
  "ownerName": "Rahul"
}
```

**Success Response:** `201 Created`

The response contains the saved pet details, generated ID, and consultation fee if calculated by the application.

### 3. View All Pets

- **Method:** GET
- **Endpoint:** `/api/pets`
- **Purpose:** Retrieves all registered pets.
- **Success Response:** `200 OK`

### 4. View a Pet by ID

- **Method:** GET
- **Endpoint:** `/api/pets/{id}`
- **Example:** `/api/pets/1`
- **Purpose:** Retrieves a specific pet using its ID.
- **Success Response:** `200 OK`
- **Error Response:** `404 Not Found` if the pet does not exist.

### 5. Update a Pet

- **Method:** PUT
- **Endpoint:** `/api/pets/{id}`
- **Example:** `/api/pets/1`
- **Purpose:** Updates the details of an existing pet.

**Request Body:**
```json
{
  "name": "Bruno",
  "species": "Dog",
  "age": 5,
  "ownerName": "Rahul"
}
```

**Success Response:** `200 OK`

**Error Response:** `404 Not Found` if the specified pet ID does not exist.

### 6. Delete a Pet

- **Method:** DELETE
- **Endpoint:** `/api/pets/{id}`
- **Example:** `/api/pets/1`
- **Purpose:** Deletes a pet using its ID.
- **Success Response:** `204 No Content`
- **Error Response:** `404 Not Found` if the pet does not exist.

### 7. Consultation Fee Calculation

| Species | Base Fee |
|---|---:|
| Dog | ₹500 |
| Cat | ₹400 |
| Bird | ₹300 |
| Other | ₹350 |

Additional charges:
- Age below 1 year: ₹100.
- Age above 7 years: ₹150.
- Otherwise, no additional charge.

Examples:
- Dog, age 3: ₹500.
- Cat, age 10: ₹550.
- Bird, age 0: ₹400.

### 8. Validation and Error Handling

- Pet name, species, and owner name must not be blank.
- Age must not be null or negative.
- Invalid input returns `400 Bad Request`.
- A missing pet ID returns `404 Not Found`.

### 9. Database

**Database name:** `petcareclinic`

**Table name:** `pets`

The pet records are stored in MySQL using Spring Data JPA. The consultation fee is calculated by the service layer and is not stored as a database column.

### 10. Testing

The APIs can be tested using IntelliJ HTTP Client, Postman, or Swagger UI if configured.

