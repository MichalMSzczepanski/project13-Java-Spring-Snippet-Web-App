package work.szczepanskimichal.project13snippetapp.user;

import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/create-account")
    public String createUserGet(Model model) {
        model.addAttribute("user", new User());
        return "public/create-update-account";
    }

    @PostMapping("/create-account")
    public String createUserPost(@Valid User user, BindingResult result, HttpServletRequest req, Model model) {
        if (validateCreateAndUpdateErrors(user, result, req, model)) return "create-update-account";
        user.setEnabled(1);
        user.setApiKey("123456");
        userService.saveUser(user);
        return "redirect:/login";
        // TODO success page?
    }

    @GetMapping("/update-account")
    public String updateAccountGet(Principal principal, Model model) {
        model.addAttribute("user", userService.findByUserEmail(principal.getName()));
        return "public/create-update-account";
    }

    @PostMapping("/update-account")
    public String updateUserPost(@Valid User user, BindingResult result, HttpServletRequest req, Model model) {
        if (validateCreateAndUpdateErrors(user, result, req, model)) return "create-update-account";
        user.setEnabled(user.getEnabled());
        user.setApiKey(user.getApiKey());
        userService.saveUser(user);
        return "redirect:/dashboard";
    }

//    TODO details in user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(Model model) {

        // send user snippet categories
        // send user sippets

        return "/user/dashboard";
    }

//    error validation in create and update actions
    private boolean validateCreateAndUpdateErrors(@Valid User user, BindingResult result, HttpServletRequest req, Model model) {
        if (result.hasErrors()) {
            return true;
        }
        if(!(req.getParameter("password").equals(req.getParameter("passwordConfirmation"))) || req.getParameter("password") == "" || req.getParameter("passwordConfirmation") == "" ) {
            model.addAttribute("passwordMismatch", true);
            return true;
        }
        user.setPassword(req.getParameter("password"));
        return false;
    }


}
