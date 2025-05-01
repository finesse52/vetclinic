package finesse52.vetclinic.repository

import finesse52.vetclinic.model.Pet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.UUID

@Repository
interface PetRepository : JpaRepository<Pet, UUID> {
    fun findAllByColor(color: String, pageable: Pageable): Page<Pet>
    fun findAllByBreed(breed: String, pageable: Pageable): Page<Pet>
    fun findAllByStartAt(startAt: LocalDate, pageable: Pageable): Page<Pet>
    fun findAllByEndAt(endAt: LocalDate, pageable: Pageable): Page<Pet>
    fun findAllByStartAtBetween(start: LocalDate, end: LocalDate, pageable: Pageable): Page<Pet>
}