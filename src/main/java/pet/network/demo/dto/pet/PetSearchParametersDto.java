package pet.network.demo.dto.pet;

import lombok.Data;

@Data
public class PetSearchParametersDto {
    private String searchStringFromUser;
    private String purpose;
    private String healthCondition;
    private String animalType;
    private String location;
    private String habitat;
}
