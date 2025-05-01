package finesse52.vetclinic.dto

import java.time.LocalDate
import java.util.UUID

data class CustomerDTO(
    val id: UUID?,
    val name: String,
    val email: String,
    val phone: String,
    val dateOfBirth: LocalDate?
)
