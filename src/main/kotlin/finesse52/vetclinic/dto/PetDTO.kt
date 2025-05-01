package finesse52.vetclinic.dto

import java.time.LocalDate
import java.util.UUID

data class PetDTO(
    val id: UUID?,
    val name: String,
    val breed: String,
    val color: String,
    val dateOfBirth: LocalDate?,
    val startAt: LocalDate?,
    val endAt: LocalDate?,
    val customerId: UUID?
)