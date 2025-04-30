package finesse52.vetclinic.dto

import java.time.LocalDate

data class PetDTO(
    val id: Long?,
    val name: String,
    val breed: String,
    val color: String,
    val dateOfBirth: LocalDate?,
    val startAt: LocalDate?,
    val endAt: LocalDate?,
    val customerId: Long?
)