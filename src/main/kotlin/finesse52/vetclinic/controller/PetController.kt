package finesse52.vetclinic.controller

import finesse52.vetclinic.dto.PetDTO
import finesse52.vetclinic.service.PetService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.UUID


@RestController
@RequestMapping(value = ["/api/pets"])
@Tag(name = "Pets", description = "Operations related to pets")
class PetController (
    private val petService: PetService
){

    @GetMapping("/{id}")
    @Operation(summary = "Get pet ID")
    @ApiResponse(responseCode = "200", description = "Customer pet")
    @ApiResponse(responseCode = "404", description = "Customer not pet")
    fun getPetById(@PathVariable id: UUID): ResponseEntity<PetDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(petService.findPetById(id))
    }

    @PostMapping
    @Operation(summary = "Create a new pet")
    @ApiResponse(responseCode = "201", description = "Pet created")
    fun createPet(@Valid @RequestBody pet: PetDTO): ResponseEntity<PetDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.createPet(pet))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a pet")
    @ApiResponse(responseCode = "200", description = "Pet updated")
    fun updatePet(@PathVariable id: UUID, @Valid @RequestBody pet: PetDTO): ResponseEntity<PetDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(petService.updatePet(id, pet))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete pet by ID")
    @ApiResponse(responseCode = "204", description = "Pet deleted")
    @ApiResponse(responseCode = "404", description = "Pet not found")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        petService.deletePet(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Find pets by customer ID")
    @ApiResponse(responseCode = "200", description = "Pets found for customer with ID {customerId}")
    @ApiResponse(responseCode = "404", description = "Customer not found with ID {customerId}")
    fun findPetsByCustomerId(@PathVariable customerId: UUID): ResponseEntity<List<PetDTO>> {
        val pets = petService.findPetsByCustomerId(customerId)
        return ResponseEntity.ok(pets)
    }

    @GetMapping("/color")
    @Operation(summary = "Find pets by color")
    @ApiResponse(responseCode = "200", description = "Pets found for color {color}")
    fun findPetsByColor(
        @RequestParam color: String,
        pageable: Pageable
    ): ResponseEntity<Page<PetDTO>> {
        val petsPage = petService.findPetsByColor(color, pageable)
        return ResponseEntity.ok(petsPage)
    }

    @GetMapping("/breed")
    @Operation(summary = "Find pets by breed")
    @ApiResponse(responseCode = "200", description = "Pets found for breed {breed}")
    fun findPetsByBreed(
        @RequestParam breed: String,
        pageable: Pageable
    ): ResponseEntity<Page<PetDTO>> {
        val petsPage = petService.findPetsByBreed(breed, pageable)
        return ResponseEntity.ok(petsPage)
    }

    @GetMapping("/start-date")
    @Operation(summary = "Find pets by start date")
    @ApiResponse(responseCode = "200", description = "Pets found for start date {startAt}")
    fun findPetsByStartDate(
        @RequestParam startAt: LocalDate,
        pageable: Pageable
    ): ResponseEntity<Page<PetDTO>> {
        val petsPage = petService.findPetsByStartDate(startAt, pageable)
        return ResponseEntity.ok(petsPage)
    }

    @GetMapping("/end-date")
    @Operation(summary = "Find pets by end date")
    @ApiResponse(responseCode = "200", description = "Pets found for end date {endAt}")
    fun findPetsByEndDate(
        @RequestParam endAt: LocalDate,
        pageable: Pageable
    ): ResponseEntity<Page<PetDTO>> {
        val petsPage = petService.findPetsByEndDate(endAt, pageable)
        return ResponseEntity.ok(petsPage)
    }

    @GetMapping("/treatment-period")
    @Operation(summary = "Find pets by treatment period")
    @ApiResponse(responseCode = "200", description = "Pets found for treatment period between {start} and {end}")
    fun findPetsByTreatmentPeriod(
        @RequestParam start: LocalDate,
        @RequestParam end: LocalDate,
        pageable: Pageable
    ): ResponseEntity<Page<PetDTO>> {
        val petsPage = petService.findPetsByTreatmentPeriod(start, end, pageable)
        return ResponseEntity.ok(petsPage)
    }
}