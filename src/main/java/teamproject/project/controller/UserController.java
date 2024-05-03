package teamproject.project.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamproject.project.dto.UserDto;
import teamproject.project.dto.verification.UpdateVerificationDto;
import teamproject.project.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDto> updateVerification(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/validate")
    public String updateVerification(@RequestBody @Valid UpdateVerificationDto request) {
        return userService.updateValidation(request);
    }
}
