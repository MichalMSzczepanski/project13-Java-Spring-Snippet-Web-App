package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreateUniqueUsernameValidator implements ConstraintValidator<CreateUniqueUsername, CreateUserDTO> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(CreateUniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateUserDTO createUserDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (userService.findByUserName(createUserDTO.getUsername()) == null) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueUsername.error.message}").addPropertyNode("username").addConstraintViolation();
            return false;
        }


    }
}
