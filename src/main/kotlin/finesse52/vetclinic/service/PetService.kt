package finesse52.vetclinic.service

import finesse52.vetclinic.dto.CustomerDTO
import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.model.Customer
import finesse52.vetclinic.repository.CustomerRepository
import finesse52.vetclinic.repository.PetRepository
import finesse52.vetclinic.transformer.CustomerTransformer
import finesse52.vetclinic.transformer.PetTransformer
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PetService(
    private val petRepository: PetRepository,
    private val petTransformer: PetTransformer,
    private val customerTransformer: CustomerTransformer,
    private val customerRepository: CustomerRepository
){

    fun findPetById(id: UUID): PetDTO {
        val pet = petRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Pet with ID $id not found")
        return petTransformer.toDto(pet)
    }

    fun createPet(dto: PetDTO): PetDTO {
        val customer = dto.customerId?.let { customerRepository.findByIdOrNull(it) } ?: throw EntityNotFoundException("Customer with ID ${dto.customerId} not found")
        val entity = petTransformer.toEntity(dto, customer)
        val saved = petRepository.save(entity)
        return petTransformer.toDto(saved)
    }


    fun updatePet(id: UUID, dto: PetDTO): PetDTO {
        val existing = petRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Pet with ID $id not found")

        existing.name = dto.name
        existing.breed = dto.breed
        existing.color = dto.color
        existing.endAt = dto.endAt
        existing.startAt = dto.startAt
        val customer = dto.customerId?.let { customerRepository.findByIdOrNull(it) } ?: throw EntityNotFoundException("Customer with ID ${dto.customerId} not found")
        existing.customer = customer
        val updated = petRepository.save(existing)
        return petTransformer.toDto(updated)
    }

    fun deletePet(id: UUID) {
        if (!petRepository.existsById(id)) {
            throw EntityNotFoundException("Pet with ID $id not found")
        }
        petRepository.deleteById(id)
    }

    fun findPetsByCustomerId(customerId: UUID): List<PetDTO> {
        val customer = customerRepository.findByIdOrNull(customerId) ?: throw EntityNotFoundException("Customer with ID $customerId not found")
        return customer.pets.map { petTransformer.toDto(it) }
    }
    fun findPetsByColor(color: String, pageable: Pageable): Page<PetDTO> {
        val petsPage = petRepository.findAllByColor(color, pageable)
        return petsPage.map { petTransformer.toDto(it) }
    }

    fun findPetsByBreed(breed: String, pageable: Pageable): Page<PetDTO> {
        val petsPage = petRepository.findAllByBreed(breed, pageable)
        return petsPage.map { petTransformer.toDto(it) }
    }

    fun findPetsByStartDate(startAt: LocalDate, pageable: Pageable): Page<PetDTO> {
        val petsPage = petRepository.findAllByStartAt(startAt, pageable)
        return petsPage.map { petTransformer.toDto(it) }
    }

    fun findPetsByEndDate(endAt: LocalDate, pageable: Pageable): Page<PetDTO> {
        val petsPage = petRepository.findAllByEndAt(endAt, pageable)
        return petsPage.map { petTransformer.toDto(it) }
    }

    fun findPetsByTreatmentPeriod(start: LocalDate, end: LocalDate, pageable: Pageable): Page<PetDTO> {
        val petsPage = petRepository.findAllByStartAtBetween(start, end, pageable)
        return petsPage.map { petTransformer.toDto(it) }
    }
}