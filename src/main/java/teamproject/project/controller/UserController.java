package teamproject.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamproject.project.dto.verification.UpdateVerificationDto;
import teamproject.project.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/validate")
    public String updateVerification(@RequestBody @Valid UpdateVerificationDto request) {
        return userService.updateValidation(request);
    }
}
