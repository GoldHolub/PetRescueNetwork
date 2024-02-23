package pet.network.demo.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @NotNull
    @Email
    @Size(min = 8, max = 50)
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
    @NotBlank
    @Size(min = 10, max = 12)
    private String phone;
    @NotBlank
    private String secondName;
}
