
package com.petcare.petcareclinic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import com.petcare.petcareclinic.model.Pet;
import com.petcare.petcareclinic.repository.PetRepository;
import com.petcare.petcareclinic.service.PetService;



import java.util.Optional;
import com.petcare.petcareclinic.service.PetNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void getPetByIdShouldReturnPetWhenPetExists() {
        Pet pet = new Pet("Bruno", "Dog", 3, "Rahul");

        when(petRepository.findById(1L))
                .thenReturn(Optional.of(pet));

        Pet result = petService.getPetById(1L);

        assertNotNull(result);
        assertEquals("Bruno", result.getName());
        assertEquals("Dog", result.getSpecies());
    }
    @Test
    void getPetByIdShouldThrowExceptionWhenPetDoesNotExist() {
        when(petRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                PetNotFoundException.class,
                () -> petService.getPetById(999L)
        );
    }
}