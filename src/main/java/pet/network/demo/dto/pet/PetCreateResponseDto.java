package pet.network.demo.dto.pet;

import lombok.Data;

@Data
public class PetCreateResponseDto {
    private Long id;
    private String name;
    private String type;
    private String age;
    private String healthCondition;
    private String sterilization;
    private String size;
    private String location;
    private String habitat;
    private String comments;
    private Long priceForDonate;
    private Long accumulatedPrice;
    private Long imageId;
}
