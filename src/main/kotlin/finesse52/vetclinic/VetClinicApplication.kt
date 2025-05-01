package finesse52.vetclinic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("finesse52.vetclinic.model")
@EnableJpaRepositories("finesse52.vetclinic.repository")
class VetClinicApplication

fun main(args: Array<String>) {
    SpringApplication.run(VetClinicApplication::class.java, *args)
}
