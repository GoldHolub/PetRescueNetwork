package pet.network.demo.service.impl;

import java.time.LocalDate;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pet.network.demo.dto.user.UserRegistrationRequestDto;
import pet.network.demo.dto.user.UserResponseDto;
import pet.network.demo.exception.RegistrationException;
import pet.network.demo.mapper.UserMapper;
import pet.network.demo.model.Role;
import pet.network.demo.model.User;
import pet.network.demo.repository.role.RoleRepository;
import pet.network.demo.repository.user.UserRepository;
import pet.network.demo.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email: "
                    + requestDto.getEmail() + " already exist");
        }
        Role roleUser = roleRepository.findByRole(Role.RoleName.USER);
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(Set.of(roleUser));
        user.setDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
