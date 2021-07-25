package work.szczepanskimichal.project13snippetapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatePasswordMatchValidator implements ConstraintValidator<UpdatePasswordMatch, UserPasswordUpdateDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void initialize(UpdatePasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserPasswordUpdateDTO userPasswordUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {
        String oldPassword = userPasswordUpdateDTO.getOldPassword();
        String password = userPasswordUpdateDTO.getPassword();
        String passwordConfirmation = userPasswordUpdateDTO.getPasswordConfirmation();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUserId(((CurrentUser) auth.getPrincipal()).getUser().getId());

        if (!passwordEncoder.matches(oldPassword, currentUser.getPassword()) || !password.equals(passwordConfirmation) || password == "" || passwordConfirmation == "") {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{passwords.dont.match}").addPropertyNode("password").addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
