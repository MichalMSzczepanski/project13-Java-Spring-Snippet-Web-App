package work.szczepanskimichal.project13snippetapp.validator;

import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordUpdateDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatePasswordMatchValidator implements ConstraintValidator<UpdatePasswordMatch, UserPasswordUpdateDTO> {

    @Override
    public void initialize(UpdatePasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserPasswordUpdateDTO userPasswordUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password = userPasswordUpdateDTO.getPassword();
        String passwordConfirmation = userPasswordUpdateDTO.getPasswordConfirmation();
        if (!password.equals(passwordConfirmation) || password == "" || passwordConfirmation == "") {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{passwords.dont.match}").addPropertyNode("password").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
