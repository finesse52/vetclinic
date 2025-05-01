package finesse52.vetclinic.transformer

import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.model.Customer
import finesse52.vetclinic.model.Pet
import org.springframework.stereotype.Component

@Component
class PetTransformer {
     fun toDTO(pet: Pet): PetDTO {
        return PetDTO(
            id = pet.id,
            name = pet.name,
            breed = pet.breed,
            color = pet.color,
            dateOfBirth = pet.dateOfBirth,
            startAt = pet.startAt,
            endAt = pet.endAt,
            customerId = pet.customer?.id
        )
    }

    fun toEntity(dto: PetDTO, customer: Customer): Pet {
        val pet = Pet()
        pet.id = dto.id
        pet.name = dto.name
        pet.breed = dto.breed
        pet.color = dto.color
        pet.dateOfBirth = dto.dateOfBirth
        pet.startAt = dto.startAt
        pet.endAt = dto.endAt
        pet.customer = customer
        return pet
    }
}