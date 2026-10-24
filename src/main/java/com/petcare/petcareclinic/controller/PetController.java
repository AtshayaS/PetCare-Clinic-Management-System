

package com.petcare.petcareclinic.controller;

import com.petcare.petcareclinic.model.Pet;
import com.petcare.petcareclinic.service.PetNotFoundException;
import com.petcare.petcareclinic.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Get all pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // Get a pet by ID
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    // Add a new pet
    @PostMapping
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet) {
        Pet savedPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }

    // Update an existing pet
    @PutMapping("/{id}")
    public Pet updatePet(
            @PathVariable Long id,
            @Valid @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    // Delete a pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }


}