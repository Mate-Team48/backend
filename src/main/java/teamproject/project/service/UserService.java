package teamproject.project.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.dto.user.UpdateVerificationDto;
import teamproject.project.dto.user.UserDto;

public interface UserService {
    List<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(long userId);

    List<FundraisingDto> getAllUserFundraisings(
            long userId,
            boolean isActive,
            String category,
            Pageable pageable);

    String updateValidation(UpdateVerificationDto request);
}
