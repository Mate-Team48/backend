package teamproject.project.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import teamproject.project.validation.email.Email;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
