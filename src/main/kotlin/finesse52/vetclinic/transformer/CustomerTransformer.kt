package finesse52.vetclinic.transformer

import finesse52.vetclinic.dto.CustomerDTO
import finesse52.vetclinic.model.Customer
import org.springframework.stereotype.Component

@Component
class CustomerTransformer {

    fun toDTO(customer: Customer): CustomerDTO {
        return CustomerDTO(
            id = customer.id,
            name = customer.name,
            email = customer.email,
            phone = customer.phone,
            dateOfBirth = customer.dateOfBirth
        )
    }

    fun toEntity(dto: CustomerDTO): Customer {
        val customer = Customer()
        customer.id = dto.id
        customer.name = dto.name
        customer.email = dto.email
        customer.phone = dto.phone
        customer.dateOfBirth = dto.dateOfBirth
        return customer
    }
}
