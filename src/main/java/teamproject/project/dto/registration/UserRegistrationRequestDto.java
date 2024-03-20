package teamproject.project.dto.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import teamproject.project.validation.email.Email;
import teamproject.project.validation.password.Password;
import teamproject.project.validation.password.matcher.PasswordMatcher;

@Data
@PasswordMatcher
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String repeatPassword;
    @NotBlank
    private String fullName;
    @NotNull
    private Boolean isVolunteer;
}
