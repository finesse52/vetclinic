package finesse52.vetclinic

import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.model.Pet
import finesse52.vetclinic.repository.CustomerRepository
import finesse52.vetclinic.repository.PetRepository
import finesse52.vetclinic.service.PetService
import finesse52.vetclinic.transformer.PetTransformer
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import java.util.*

class PetServiceTest {

    @InjectMocks
    lateinit var petService: PetService

    @Mock
    lateinit var petRepository: PetRepository

    @Mock
    lateinit var petTransformer: PetTransformer

    @Mock
    private lateinit var customerRepository: CustomerRepository


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should return PetDTO when pet exists`() {
        val id = UUID.randomUUID()
        val pet = Pet().apply { this.id = id; this.name = "Buddy" }
        val petDTO = PetDTO(
            id = id,
            name = pet.name,
            breed = pet.breed,
            color = pet.color,
            dateOfBirth = pet.dateOfBirth,
            startAt = pet.startAt,
            endAt = pet.endAt,
            customerId = pet.customer?.id
        )

        whenever(petRepository.findById(id)).thenReturn(Optional.of(pet))
        whenever(petTransformer.toDTO(pet)).thenReturn(petDTO)

        val result = petService.findPetById(id)

        assertNotNull(result)
        assertEquals("Buddy", result.name)
        verify(petRepository).findById(id)
        verify(petTransformer).toDTO(pet)
    }

    @Test
    fun `should throw EntityNotFoundException when pet not found`() {
        val id = UUID.randomUUID()

        whenever(petRepository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows(EntityNotFoundException::class.java) {
            petService.findPetById(id)
        }

        assertEquals("Pet with ID $id not found", exception.message)
        verify(petRepository).findById(id)
    }

    @Test
    fun `should delete pet by id`() {
        val id = UUID.randomUUID()

        whenever(petRepository.existsById(id)).thenReturn(true)

        petService.deletePet(id)

        verify(petRepository).existsById(id)
        verify(petRepository).deleteById(id)
    }
}
