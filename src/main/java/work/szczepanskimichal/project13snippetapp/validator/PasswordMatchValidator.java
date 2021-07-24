package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, CreateUserDTO> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateUserDTO createUserDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password = createUserDTO.getPassword();
        String passwordConfirmation = createUserDTO.getPasswordConfirmation();
        if (!password.equals(passwordConfirmation) || password == "" || passwordConfirmation == "") {
            System.out.println("flag1");
            constraintValidatorContext.buildConstraintViolationWithTemplate("{passwords.dont.match}").addPropertyNode("password").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
