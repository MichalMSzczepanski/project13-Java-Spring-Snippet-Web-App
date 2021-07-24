package work.szczepanskimichal.project13snippetapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/update-user-details")
    public String updateAccountGet(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserDetailsUpdateDTO userDetailsUpdateDTO = new UserDetailsUpdateDTO();
        userDetailsUpdateDTO = userDetailsUpdateDTO.userDTOFilledOut(currentUser);
        model.addAttribute("userDetails", userDetailsUpdateDTO);
        return "user/update-user-details";
    }

    @PostMapping("/update-user-details")
    public String updateUserPost(@ModelAttribute("userDetails") @Valid UserDetailsUpdateDTO userDetailsUpdateDTO, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "user/update-user-details";
        }
        User updatedUser = currentUser.getUser();
        updatedUser.setUsername(userDetailsUpdateDTO.getUsername());
        updatedUser.setEmail(userDetailsUpdateDTO.getEmail());
        System.out.println("FLAG 1111");
        System.out.println(updatedUser);
        userService.update(updatedUser);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/update-user-password")
    public String updateUserPasswordGet(Model model) {
        model.addAttribute("userPasswords", new UserPasswordUpdateDTO());
        return "user/update-user-password";
        //    TODO send email with link to change password?
    }

    @PostMapping("/update-user-password")
    public String updateUserPasswordPost(@ModelAttribute("userPasswords") @Valid UserPasswordUpdateDTO userPasswordUpdateDTO, BindingResult result, Model model, HttpServletRequest request, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "user/update-user-password";
        }
        if(!(request.getParameter("password").equals(request.getParameter("passwordConfirmation")))
                || request.getParameter("password") == ""
                || request.getParameter("passwordConfirmation") == "") {
            model.addAttribute("passwordMismatch", true);
            return "user/update-user-password";
        }
        userService.updatePassword(currentUser.getUser(), request.getParameter("password"));
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //        doesn't redirect to homepage
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@AuthenticationPrincipal CurrentUser currentUser) {
        userService.delete(currentUser.getUser());
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //        doesn't redirect to homepage
        return "redirect:/";
    }

//    TODO details in user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("currentUserName", currentUser.getUser().getUsername());

        // send user snippet categories
        // send user sippets

        return "/user/dashboard";
    }

}
