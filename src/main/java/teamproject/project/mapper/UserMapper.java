package teamproject.project.mapper;

import org.mapstruct.Mapper;
import teamproject.project.config.MapperConfig;
import teamproject.project.dto.registration.UserRegistrationResponseDto;
import teamproject.project.dto.user.UserDto;
import teamproject.project.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto userToUserDto(User user);

    UserRegistrationResponseDto toUserRegistrationResponseDto(User savedUser);
}
