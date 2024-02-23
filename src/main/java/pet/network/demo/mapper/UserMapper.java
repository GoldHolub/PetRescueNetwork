package pet.network.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.network.demo.config.MapperConfig;
import pet.network.demo.dto.user.UserRegistrationRequestDto;
import pet.network.demo.dto.user.UserResponseDto;
import pet.network.demo.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "lastName", source = "secondName")
    User toModel(UserRegistrationRequestDto requestDto);
}
