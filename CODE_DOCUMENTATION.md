# Pet Care Clinic Management System
## Code Documentation

### 1. PetCareClinicApplication.java

**Purpose:** Main entry point of the Spring Boot application.

The `@SpringBootApplication` annotation enables Spring Boot configuration and component scanning. The `main()` method starts the application using `SpringApplication.run()`.

### 2. model/Pet.java

**Purpose:** Represents a pet as a Java entity mapped to the `pets` database table.

Important elements:
- `@Entity`: Marks the class as a JPA entity.
- `@Table(name = "pets")`: Specifies the database table.
- `@Id`: Identifies the primary key.
- `@GeneratedValue`: Generates the ID automatically.
- `@NotBlank` and `@Size`: Validate text fields.
- `@NotNull` and `@Min`: Validate age.
- `@Transient`: Excludes the consultation fee from database persistence.

Constructors, getters, and setters initialize and access the pet's fields.

### 3. repository/PetRepository.java

**Purpose:** Provides database operations for Pet entities.

The interface extends `JpaRepository<Pet, Long>`, providing built-in methods such as:
- `save()`
- `findAll()`
- `findById()`
- `delete()`
- `deleteById()`

### 4. service/PetService.java

**Purpose:** Contains the application's business logic.

Main methods:
- `addPet()`: Saves a new pet and calculates its fee.
- `getAllPets()`: Retrieves and displays all pets.
- `getPetById()`: Retrieves one pet by ID.
- `updatePet()`: Updates an existing pet.
- `deletePet()`: Deletes an existing pet.

The fee calculation method assigns a base fee based on species and applies age-related additional charges.

The service prints pet details and consultation fees to the application console.

### 5. service/PetNotFoundException.java

**Purpose:** Represents the error raised when a requested pet ID does not exist.

This runtime exception carries a descriptive error message. It is handled by the global exception handler.

### 6. controller/PetController.java

**Purpose:** Exposes the pet management functionality through REST endpoints.

- `@RestController`: Marks the class as a REST controller.
- `@RequestMapping("/api/pets")`: Defines the base URL.
- `@GetMapping`: Retrieves pet details.
- `@PostMapping`: Adds a pet.
- `@PutMapping("/{id}")`: Updates a pet.
- `@DeleteMapping("/{id}")`: Deletes a pet.
- `@Valid`: Triggers validation of request data.

The controller uses HTTP status codes to communicate the result of each operation.

### 7. controller/GlobalExceptionHandler.java

**Purpose:** Centralizes exception handling for REST requests.

- `@RestControllerAdvice`: Applies exception handling across controllers.
- `PetNotFoundException`: Returns `404 Not Found`.
- `MethodArgumentNotValidException`: Returns `400 Bad Request` for invalid request data.

### 8. OpenApiConfig.java (if added)

**Purpose:** Configures the title, version, and description of the API documentation displayed by Swagger UI.

The OpenAPI bean supplies descriptive metadata for the REST API.

### 9. PetServiceTest.java

**Purpose:** Tests service-layer behavior using JUnit and Mockito.

It can verify successful pet retrieval and confirm that `PetNotFoundException` is thrown when a pet ID does not exist.

### 10. PetCareClinicApplicationTests.java

**Purpose:** Checks whether the Spring application context loads successfully.

The `@SpringBootTest` annotation starts the application context for the test.

### 11. application.properties

**Purpose:** Stores application configuration.

It defines database connection settings, the database URL, JPA/Hibernate options, and other Spring Boot properties.

Database credentials should be supplied securely using environment variables rather than committed to source control.

### 12. pom.xml

**Purpose:** Defines Maven project dependencies and build settings.

Dependencies include Spring Web, Spring Data JPA, MySQL Connector, Jakarta Validation, and Spring Boot Test. The Springdoc OpenAPI dependency is included if Swagger documentation is enabled.

