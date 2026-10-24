/*
package com.petcare.petcareclinic.service;

import com.petcare.petcareclinic.model.Pet;
import com.petcare.petcareclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    // Constructor injection
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Get all pets
    public List<Pet> getAllPets() {
        List<Pet> pets = petRepository.findAll();

        for (Pet pet : pets) {
            pet.setConsultationFee(calculateConsultationFee(pet));
        }

        return pets;
    }

    // Get one pet by ID

    public Pet getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() ->
                        new PetNotFoundException("Pet not found with ID: " + id));

        pet.setConsultationFee(calculateConsultationFee(pet));

        return pet;
    }

    // Add a new pet

    public Pet addPet(Pet pet) {
        pet.setId(null);

        Pet savedPet = petRepository.save(pet);

        savedPet.setConsultationFee(calculateConsultationFee(savedPet));

        return savedPet;
    }

    // Update an existing pet
    public Pet updatePet(Long id, Pet updatedPet) {
        Pet existingPet = getPetById(id);

        existingPet.setName(updatedPet.getName());
        existingPet.setSpecies(updatedPet.getSpecies());
        existingPet.setAge(updatedPet.getAge());
        existingPet.setOwnerName(updatedPet.getOwnerName());

        Pet savedPet = petRepository.save(existingPet);

        savedPet.setConsultationFee(calculateConsultationFee(savedPet));

        return savedPet;
    }

    // Delete a pet
    public void deletePet(Long id) {
        Pet pet = getPetById(id);
        petRepository.delete(pet);
    }
    private double calculateConsultationFee(Pet pet) {
        double fee;

        // Base fee based on species
        switch (pet.getSpecies().trim().toLowerCase()) {
            case "dog":
                fee = 500.0;
                break;
            case "cat":
                fee = 400.0;
                break;
            case "bird":
                fee = 300.0;
                break;
            default:
                fee = 350.0;
        }

        // Additional fee based on age
        if (pet.getAge() < 1) {
            fee += 100.0;
        } else if (pet.getAge() > 7) {
            fee += 150.0;
        }

        return fee;
    }
}
*/

package com.petcare.petcareclinic.service;

import com.petcare.petcareclinic.model.Pet;
import com.petcare.petcareclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Calculate consultation fee
    private double calculateConsultationFee(Pet pet) {
        double fee;
        String species = pet.getSpecies().trim().toLowerCase();

        switch (species) {
            case "dog":
                fee = 500.0;
                break;
            case "cat":
                fee = 400.0;
                break;
            case "bird":
                fee = 300.0;
                break;
            default:
                fee = 350.0;
        }

        if (pet.getAge() < 1) {
            fee += 100.0;
        } else if (pet.getAge() > 7) {
            fee += 150.0;
        }

        return fee;
    }

    // Print pet details and fee in IntelliJ console
    private void printPetDetails(String operation, Pet pet) {
        double fee = calculateConsultationFee(pet);
        pet.setConsultationFee(fee);

        System.out.println("\n========== " + operation + " ==========");
        System.out.println("Pet ID             : " + pet.getId());
        System.out.println("Pet Name           : " + pet.getName());
        System.out.println("Species             : " + pet.getSpecies());
        System.out.println("Age                 : " + pet.getAge());
        System.out.println("Owner Name          : " + pet.getOwnerName());
        System.out.printf("Consultation Fee    : ₹%.2f%n", fee);
        System.out.println("====================================\n");
    }

    // Add a new pet
    public Pet addPet(Pet pet) {
        pet.setId(null);

        Pet savedPet = petRepository.save(pet);
        printPetDetails("PET ADDED SUCCESSFULLY", savedPet);

        return savedPet;
    }

    // View all pets
    public List<Pet> getAllPets() {
        List<Pet> pets = petRepository.findAll();

        System.out.println("\n========== ALL PETS ==========");

        if (pets.isEmpty()) {
            System.out.println("No pets found.");
        }

        for (Pet pet : pets) {
            printPetDetails("PET DETAILS", pet);
        }

        return pets;
    }

    // View one pet by ID
    public Pet getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() ->
                        new PetNotFoundException(
                                "Pet not found with ID: " + id));

        printPetDetails("PET FOUND", pet);
        return pet;
    }

    // Update an existing pet
    public Pet updatePet(Long id, Pet updatedPet) {
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() ->
                        new PetNotFoundException(
                                "Pet not found with ID: " + id));

        existingPet.setName(updatedPet.getName());
        existingPet.setSpecies(updatedPet.getSpecies());
        existingPet.setAge(updatedPet.getAge());
        existingPet.setOwnerName(updatedPet.getOwnerName());

        Pet savedPet = petRepository.save(existingPet);
        printPetDetails("PET UPDATED SUCCESSFULLY", savedPet);

        return savedPet;
    }

    // Delete a pet by ID
    public void deletePet(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() ->
                        new PetNotFoundException(
                                "Pet not found with ID: " + id));

        printPetDetails("PET TO BE DELETED", pet);
        petRepository.delete(pet);

        System.out.println("Pet with ID " + id
                + " deleted successfully.");
    }
}
