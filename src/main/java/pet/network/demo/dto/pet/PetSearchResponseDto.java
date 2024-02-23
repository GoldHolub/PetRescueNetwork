package pet.network.demo.dto.pet;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PetSearchResponseDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String sex;
    private LocalDate age;
    private boolean fond;
    private Long priceForDonate;
    private Long accumulatedPrice;
}
