package teamproject.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamproject.project.dto.login.UserLoginRequestDto;
import teamproject.project.dto.login.UserLoginResponseDto;
import teamproject.project.dto.registration.UserRegistrationRequestDto;
import teamproject.project.dto.registration.UserRegistrationResponseDto;
import teamproject.project.exception.RegistrationException;
import teamproject.project.mapper.UserMapper;
import teamproject.project.model.Role;
import teamproject.project.model.User;
import teamproject.project.repository.RoleRepository;
import teamproject.project.repository.UserRepository;
import teamproject.project.security.AuthenticationService;
import teamproject.project.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email " + requestDto.getEmail()
                    + " already exists");
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFullName(requestDto.getFullName());
        user.setVerified(false);
        if (requestDto.getIsVolunteer()) {
            user.getRoles().add(roleRepository.findByRoleName(Role.RoleName.ROLE_VOLUNTEER));
        } else {
            user.getRoles().add(roleRepository.findByRoleName(Role.RoleName.ROLE_USER));
        }

        User savedUser = userRepository.save(user);
        UserLoginResponseDto authenticated = authenticationService.authenticate(
                new UserLoginRequestDto()
                        .setEmail(requestDto.getEmail())
                        .setPassword(requestDto.getPassword())
        );

        UserRegistrationResponseDto responseDto = userMapper
                .toUserRegistrationResponseDto(savedUser);
        responseDto.setToken(authenticated.token());
        return responseDto;
    }
}
