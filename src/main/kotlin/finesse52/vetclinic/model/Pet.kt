package finesse52.vetclinic.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "pets")
class Pet{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    var id: UUID? = null

    @Column(name = "name")
    var name: String = ""

    @Column(name = "breed")
    var breed: String = ""

    @Column(name = "color")
    var color: String = ""

    @Column(name = "year_of_birth")
    var dateOfBirth: LocalDate? = null

    @Column(name = "start_at")
    var startAt: LocalDate? = null

    @Column(name = "end_at")
    var endAt: LocalDate? = null

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
}