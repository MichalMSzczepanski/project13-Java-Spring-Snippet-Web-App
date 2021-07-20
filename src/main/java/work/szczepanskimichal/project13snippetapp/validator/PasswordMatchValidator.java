package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, User> {

//    @Autowired
//    private UserService userService;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        String password = user.getPassword();
        String passwordConfirmation = user.getPasswordConfirmation();
        if (!password.equals(passwordConfirmation) || password == "" || passwordConfirmation != "") {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{passwords.dont.match}").addPropertyNode("password").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
