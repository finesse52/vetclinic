package finesse52.vetclinic.controller

import finesse52.vetclinic.dto.CustomerDTO
import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping(value = ["/api/customers"])
@Tag(name = "Customers", description = "Operations related to pet Customers")
class CustomerController(
    private val customerService: CustomerService
){

    @GetMapping("/{id}")
    @Operation(summary = "Get customer ID")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    fun getCustomerById(@PathVariable id : UUID) : ResponseEntity<CustomerDTO>{
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id))
    }

    @PostMapping
    @Operation(summary = "Create a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created")
    fun createCustomer(@Valid @RequestBody customer: CustomerDTO): ResponseEntity<CustomerDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer")
    @ApiResponse(responseCode = "200", description = "Customer updated")
    fun updateCustomer(@PathVariable id: UUID, @Valid @RequestBody customer: CustomerDTO): ResponseEntity<CustomerDTO> {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by ID")
    @ApiResponse(responseCode = "204", description = "Customer deleted")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    fun deleteCustomer(@PathVariable id: UUID): ResponseEntity<Void> {
        customerService.deleteCustomer(id)
        return ResponseEntity.noContent().build()
    }
    @GetMapping("/search")
    @Operation(summary = "Find customer by name (partial match)")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    fun findCustomerByName(@RequestParam name: String): ResponseEntity<CustomerDTO> {
        val customer = customerService.findCustomerByName(name)
        return customer.map { ResponseEntity.ok(it) }
            .orElseThrow { EntityNotFoundException("Customer with name containing '$name' not found") }
    }

}