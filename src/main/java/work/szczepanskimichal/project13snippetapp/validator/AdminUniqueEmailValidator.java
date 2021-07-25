package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdminUniqueEmailValidator implements ConstraintValidator<AdminUniqueEmail, AdminUpdateUserDTO> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(AdminUpdateUserDTO adminUpdateUserDTO, ConstraintValidatorContext constraintValidatorContext) {

        User existingUser = userService.findByUserEmail(adminUpdateUserDTO.getEmail());

        if (userService.findByUserEmail(adminUpdateUserDTO.getEmail()) == null || adminUpdateUserDTO.getEmail().equals(existingUser.getEmail())) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueEmail.error.message}").addPropertyNode("email").addConstraintViolation();
            return false;
        }

    }
}
