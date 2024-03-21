package teamproject.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.project.dto.verification.UpdateVerificationDto;
import teamproject.project.exception.EntityNotFoundException;
import teamproject.project.model.Role;
import teamproject.project.model.User;
import teamproject.project.repository.RoleRepository;
import teamproject.project.repository.UserRepository;
import teamproject.project.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public String updateValidation(UpdateVerificationDto request) {
        checkForAdmin(request.getEmail());
        User user = findUserByEmail(request.getEmail());
        user.setVerified(request.getStatement());
        User savedUser = userRepository.save(user);
        return savedUser.isVerified()
                ? "User with email " + savedUser.getEmail() + " was verified"
                : "Verification for user with email " + savedUser.getEmail() + " was disabled";
    }

    private void checkForAdmin(String email) {
        User user = findUserByEmail(email);
        if (user.getRoles().contains(roleRepository.findByRoleName(Role.RoleName.ROLE_ADMIN))) {
            throw new RuntimeException("You can not update ADMIN roles");
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User with email " + email + " was not found!")
        );
    }
}
