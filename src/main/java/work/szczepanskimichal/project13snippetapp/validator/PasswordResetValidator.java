package work.szczepanskimichal.project13snippetapp.validator;

import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordResetDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PasswordResetValidator implements ConstraintValidator<PasswordReset, UserPasswordResetDTO> {

    @Override
    public void initialize(PasswordReset constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserPasswordResetDTO userPasswordResetDTO, ConstraintValidatorContext constraintValidatorContext) {

        String password = userPasswordResetDTO.getPassword();
        String passwordConfirmation = userPasswordResetDTO.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation) || password == "" || passwordConfirmation == "") {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{passwords.dont.match}").addPropertyNode("password").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
