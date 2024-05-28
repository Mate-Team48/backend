package teamproject.project.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.dto.user.UserDto;
import teamproject.project.dto.user.UpdateVerificationDto;
import teamproject.project.exception.AdminUpdateException;
import teamproject.project.exception.EntityNotFoundException;
import teamproject.project.mapper.FundraisingMapper;
import teamproject.project.mapper.UserMapper;
import teamproject.project.model.Fundraising;
import teamproject.project.model.Role;
import teamproject.project.model.User;
import teamproject.project.repository.FundraisingRepository;
import teamproject.project.repository.RoleRepository;
import teamproject.project.repository.UserRepository;
import teamproject.project.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FundraisingRepository fundraisingRepository;
    private final UserMapper userMapper;
    private final FundraisingMapper fundraisingMapper;

    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(userMapper::userToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " was not found!"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<FundraisingDto> getAllUserFundraisings(long userId,
                                                       boolean isActive,
                                                       String category,
                                                       Pageable pageable) {
        Fundraising.Category fundraisingCategory = Fundraising.Category.valueOf(category.toUpperCase());
        List<Fundraising> fundraisings = fundraisingRepository.findAllByUserIdAndIsActiveAndCategory(userId, isActive, fundraisingCategory, pageable);
        return fundraisings.stream()
                .map(fundraisingMapper::fundraisingToDto)
                .toList();
    }

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
            throw new AdminUpdateException("You can not update ADMIN role");
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User with email " + email + " was not found!")
        );
    }
}
