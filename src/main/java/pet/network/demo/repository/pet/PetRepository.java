package pet.network.demo.repository.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.network.demo.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
