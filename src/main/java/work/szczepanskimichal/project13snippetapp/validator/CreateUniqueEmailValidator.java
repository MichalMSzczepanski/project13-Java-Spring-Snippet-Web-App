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

public class CreateUniqueEmailValidator implements ConstraintValidator<CreateUniqueEmail, CreateUserDTO> {

    @Autowired
    private UserService userService;

//    nadpisywanie?
//    @Override
//    public void initialize(CreateUniqueEmail constraintAnnotation) {
//    }

    @Override
    public boolean isValid(CreateUserDTO createUserDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (userService.findByUserEmail(createUserDTO.getEmail()) == null) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueEmail.error.message}").addPropertyNode("email").addConstraintViolation();
            return false;
        }

    }
}
