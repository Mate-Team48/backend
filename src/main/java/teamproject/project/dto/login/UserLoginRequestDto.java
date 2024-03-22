package teamproject.project.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import teamproject.project.validation.email.Email;

@Data
@Accessors(chain = true)
public class UserLoginRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
