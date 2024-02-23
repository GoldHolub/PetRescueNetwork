package pet.network.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.network.demo.dto.user.UserLoginRequestDto;
import pet.network.demo.dto.user.UserLoginResponseDto;
import pet.network.demo.dto.user.UserRegistrationRequestDto;
import pet.network.demo.dto.user.UserResponseDto;
import pet.network.demo.security.AuthenticationService;
import pet.network.demo.service.UserService;

@Tag(name = "User authentication", description = "Authenticate a new or existing user")
@RequiredArgsConstructor
@RequestMapping(value = "api/auth")
@RestController
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "User registration",
            description = "register a new user")
    @PostMapping(value = "/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PostMapping(value = "/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
