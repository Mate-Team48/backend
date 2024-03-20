package teamproject.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamproject.project.dto.registration.UserRegistrationRequestDto;
import teamproject.project.dto.registration.UserRegistrationResponseDto;
import teamproject.project.exception.RegistrationException;
import teamproject.project.mapper.UserMapper;
import teamproject.project.model.Role;
import teamproject.project.model.User;
import teamproject.project.repository.RoleRepository;
import teamproject.project.repository.UserRepository;
import teamproject.project.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + "already exists");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFullName(requestDto.getFullName());
        if (requestDto.getIsVolunteer()) {
            user.getRoles().add(roleRepository.findByRoleName(Role.RoleName.ROLE_VOLUNTEER));
        } else {
            user.getRoles().add(roleRepository.findByRoleName(Role.RoleName.ROLE_USER));
        }

        User savedUser = userRepository.save(user);
        return userMapper.toUserRegistrationResponseDto(savedUser);
    }
}
