package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, UserDetailsUpdateDTO> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDetailsUpdateDTO userDetailsUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUserId(((CurrentUser) auth.getPrincipal()).getUser().getId());
        if (userService.findByUserName(userDetailsUpdateDTO.getUsername()) == null) {
            return true;
        } else if(userDetailsUpdateDTO.getUsername().equals(currentUser.getUsername())){
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueUsername.error.message}").addPropertyNode("username").addConstraintViolation();
            return false;
        }


    }
}
