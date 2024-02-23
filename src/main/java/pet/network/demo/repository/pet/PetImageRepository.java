package pet.network.demo.repository.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.network.demo.model.PetImage;

public interface PetImageRepository extends JpaRepository<PetImage, Long> {
}
