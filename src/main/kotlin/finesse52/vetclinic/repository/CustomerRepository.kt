package finesse52.vetclinic.repository

import finesse52.vetclinic.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun findByNameContainingIgnoreCase(name: String): Optional<Customer>
}