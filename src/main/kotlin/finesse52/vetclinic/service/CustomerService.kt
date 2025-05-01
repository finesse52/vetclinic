package finesse52.vetclinic.service

import finesse52.vetclinic.dto.CustomerDTO
import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.repository.CustomerRepository
import finesse52.vetclinic.transformer.CustomerTransformer
import finesse52.vetclinic.transformer.PetTransformer
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val customerTransformer: CustomerTransformer,
    private val petTransformer: PetTransformer
) {
    fun findCustomerById(id: UUID): CustomerDTO {
        val customer = customerRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Customer with ID $id not found")
        return customerTransformer.toDTO(customer)
    }
    fun createCustomer(dto: CustomerDTO): CustomerDTO {
        val entity = customerTransformer.toEntity(dto)
        val saved = customerRepository.save(entity)
        return customerTransformer.toDTO(saved)
    }

    fun updateCustomer(id: UUID, dto: CustomerDTO): CustomerDTO {
        val existing = customerRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Customer with ID $id not found")
        existing.name = dto.name
        existing.email = dto.email
        existing.phone = dto.phone
        existing.dateOfBirth = dto.dateOfBirth ?: existing.dateOfBirth

        val updated = customerRepository.save(existing)
        return customerTransformer.toDTO(updated)
    }

    fun deleteCustomer(id: UUID) {
        if (!customerRepository.existsById(id)) {
            throw EntityNotFoundException("Customer with ID $id not found")
        }
        customerRepository.deleteById(id)
    }

    fun getCustomersPets(customerId: UUID): List<PetDTO> {
        val customer = customerRepository.findByIdOrNull(customerId) ?: throw EntityNotFoundException("Customer with ID $customerId not found")
        return customer.pets.map { petTransformer.toDto(it) }
    }

    fun findCustomerByName(name: String): Optional<CustomerDTO> {
        return customerRepository.findByNameContainingIgnoreCase(name)
            .map { customerTransformer.toDTO(it) }
    }
}
