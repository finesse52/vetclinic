package finesse52.vetclinic

import finesse52.vetclinic.dto.CustomerDTO
import finesse52.vetclinic.model.Customer
import finesse52.vetclinic.repository.CustomerRepository
import finesse52.vetclinic.service.CustomerService
import finesse52.vetclinic.transformer.CustomerTransformer
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.*
import org.mockito.MockitoAnnotations
import java.util.*

class CustomerServiceTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @Mock
    private lateinit var customerTransformer: CustomerTransformer

    @InjectMocks
    private lateinit var customerService: CustomerService


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should return CustomerDTO when customer exists`() {
        val id = UUID.randomUUID()
        val customer = Customer().apply { this.id = id; this.name = "John" }
        val customerDTO = CustomerDTO(
            id = id,
            name = customer.name,
            email = customer.email,
            phone = customer.phone,
            dateOfBirth = customer.dateOfBirth)

        whenever(customerRepository.findById(id)).thenReturn(Optional.of(customer))
        whenever(customerTransformer.toDTO(customer)).thenReturn(customerDTO)

        val result = customerService.findCustomerById(id)

        assertNotNull(result)
        assertEquals("John", result.name)
        verify(customerRepository).findById(id)
        verify(customerTransformer).toDTO(customer)
    }

    @Test
    fun `should throw EntityNotFoundException when customer not found`() {
        val id = UUID.randomUUID()

        whenever(customerRepository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows(EntityNotFoundException::class.java) {
            customerService.findCustomerById(id)
        }

        assertEquals("Customer with ID $id not found", exception.message)
        verify(customerRepository).findById(id)
    }
}