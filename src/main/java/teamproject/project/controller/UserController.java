package teamproject.project.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.dto.user.UserDto;
import teamproject.project.dto.user.UpdateVerificationDto;
import teamproject.project.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/fundraisings")
    public List<FundraisingDto> getAllUserFundraisings(@PathVariable long userId,
                                                       @RequestParam(required = false) boolean isActive,
                                                       @RequestParam(required = false) String category,
                                                       Pageable pageable) {
        return userService.getAllUserFundraisings(userId, isActive, category, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/validate")
    public String updateVerification(@RequestBody @Valid UpdateVerificationDto request) {
        return userService.updateValidation(request);
    }
}
