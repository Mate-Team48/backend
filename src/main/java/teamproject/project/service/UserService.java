package teamproject.project.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import teamproject.project.dto.UserDto;
import teamproject.project.dto.verification.UpdateVerificationDto;

public interface UserService {
    String updateValidation(UpdateVerificationDto request);

    List<UserDto> getAllUsers(Pageable pageable);
}
