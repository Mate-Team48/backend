package teamproject.project.service;

import teamproject.project.dto.registration.UserRegistrationRequestDto;
import teamproject.project.dto.registration.UserRegistrationResponseDto;
import teamproject.project.exception.RegistrationException;

public interface AuthService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
