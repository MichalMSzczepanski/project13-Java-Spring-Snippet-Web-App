package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdminUniqueUsernameValidator implements ConstraintValidator<AdminUniqueUsername, AdminUpdateUserDTO> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(AdminUpdateUserDTO adminUpdateUserDTO, ConstraintValidatorContext constraintValidatorContext) {

        User existingUser = userService.findByUserEmail(adminUpdateUserDTO.getEmail());

        if (userService.findByUserName(adminUpdateUserDTO.getUsername()) == null || adminUpdateUserDTO.getUsername().equals(existingUser.getUsername())) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueUsername.error.message}").addPropertyNode("username").addConstraintViolation();
            return false;
        }


    }
}
