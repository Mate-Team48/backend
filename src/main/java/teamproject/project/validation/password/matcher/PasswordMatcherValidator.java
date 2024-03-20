package teamproject.project.validation.password.matcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import teamproject.project.dto.registration.UserRegistrationRequestDto;

public class PasswordMatcherValidator
        implements ConstraintValidator<PasswordMatcher, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return requestDto != null
                && Objects.equals(requestDto.getPassword(), requestDto.getRepeatPassword());
    }
}
