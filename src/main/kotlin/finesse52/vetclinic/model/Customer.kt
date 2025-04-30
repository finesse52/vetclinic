package finesse52.vetclinic.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "customers")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private var id: Long? = null

    @Column(name = "name")
    private var name: String = ""

    @Column(name = "email")
    private var email: String = ""

    @Column(name = "phone")
    private var phone: String = ""

    @Column(name = "date_of_birth")
    private var dateOfBirth: LocalDate? = null

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    var pets: MutableList<Pet> = mutableListOf()
}
