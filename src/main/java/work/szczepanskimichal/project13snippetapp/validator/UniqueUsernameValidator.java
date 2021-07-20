package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null ) {
            return true;
        }
        if(userService == null) {
            return true;
        }
        return userService.findByUserName(username) == null;
    }
}
