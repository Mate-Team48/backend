package teamproject.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamproject.project.dto.login.UserLoginRequestDto;
import teamproject.project.dto.login.UserLoginResponseDto;
import teamproject.project.dto.registration.UserRegistrationRequestDto;
import teamproject.project.dto.registration.UserRegistrationResponseDto;
import teamproject.project.exception.RegistrationException;
import teamproject.project.security.AuthenticationService;
import teamproject.project.service.AuthService;

@Tag(name = "Auth management", description = "Endpoints for registration and login")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Endpoint for login",
            description = "Endpoint for login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/registration")
    @Operation(summary = "Endpoint for registration",
            description = "Endpoint for registration")
    public UserRegistrationResponseDto registerUser(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    ) throws RegistrationException {
        return authService.register(requestDto);
    }
}
