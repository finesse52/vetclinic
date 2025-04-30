package finesse52.vetclinic.dto

import java.time.LocalDate

data class CustomerDTO(
    val id: Long?,
    val name: String,
    val email: String,
    val phone: String,
    val dateOfBirth: LocalDate?
)
