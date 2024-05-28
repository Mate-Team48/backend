package teamproject.project.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateVerificationDto {
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Boolean statement;
}
