package teamproject.project.dto.registration;

import lombok.Data;

@Data
public class UserRegistrationResponseDto {
    private Long id;
    private String email;
    private String fullName;
    private String token;
}
