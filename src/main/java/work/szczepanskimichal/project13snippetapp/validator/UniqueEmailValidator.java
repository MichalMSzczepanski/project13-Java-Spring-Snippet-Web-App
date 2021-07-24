package work.szczepanskimichal.project13snippetapp.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserDetailsUpdateDTO> {

    @Autowired
    private UserService userService;

//    nadpisywanie
//    @Override
//    public void initialize(UniqueEmail constraintAnnotation) {
//    }

    @Override
    public boolean isValid(UserDetailsUpdateDTO userDetailsUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUserId(((CurrentUser) auth.getPrincipal()).getUser().getId());
        if (userService.findByUserEmail(userDetailsUpdateDTO.getEmail()) == null || userDetailsUpdateDTO.getEmail().equals(currentUser.getEmail())) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{uniqueEmail.error.message}").addPropertyNode("email").addConstraintViolation();
            return false;
        }

    }
}
