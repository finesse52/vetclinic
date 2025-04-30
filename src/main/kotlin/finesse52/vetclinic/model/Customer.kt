package finesse52.vetclinic.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "customers")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    var id: Long? = null

    @Column(name = "name")
    var name: String = ""

    @Column(name = "email")
    var email: String = ""

    @Column(name = "phone")
    var phone: String = ""

    @Column(name = "date_of_birth")
    var dateOfBirth: LocalDate? = null

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    var pets: MutableList<Pet> = mutableListOf()
}
