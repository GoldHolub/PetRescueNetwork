package pet.network.demo.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetCreateRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private String age;
    private String sex;
    @NotBlank
    private String healthCondition;
    private String sterilization;
    @NotBlank
    private String size;
    @NotBlank
    private String location;
    @NotBlank
    private String habitat;
    @NotNull
    private Long priceForDonate;
    @NotNull
    private Long accumulatedPrice;
    private String comments;
    private byte[] image;
}
