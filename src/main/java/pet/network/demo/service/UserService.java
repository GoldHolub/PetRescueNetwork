package pet.network.demo.service;

import pet.network.demo.dto.user.UserRegistrationRequestDto;
import pet.network.demo.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
