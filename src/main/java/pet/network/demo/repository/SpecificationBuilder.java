package pet.network.demo.repository;

import org.springframework.data.jpa.domain.Specification;
import pet.network.demo.dto.pet.PetSearchParametersDto;

public interface SpecificationBuilder<T> {
    Specification<T> build(PetSearchParametersDto searchParameters);
}
