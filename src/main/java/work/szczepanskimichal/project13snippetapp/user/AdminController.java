package work.szczepanskimichal.project13snippetapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import work.szczepanskimichal.project13snippetapp.role.Role;
import work.szczepanskimichal.project13snippetapp.role.RoleService;
import work.szczepanskimichal.project13snippetapp.utils.SimpleKeyGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final SimpleKeyGenerator simpleKeyGenerator;

    @GetMapping("/create-account")
    public String adminCreateUserOrAdminGet(Model model) {
        model.addAttribute("user", new User());
        return "admin/create-account";
    }

    @PostMapping("/create-account")
    public String adminCreateUserOrAdminPost(@Valid User user, BindingResult result, HttpServletRequest req, Model model) {
        if (result.hasErrors()) {
            return "admin/create-account";
        }
        userService.saveUser(user);
        return "redirect:/admin/dashboard";
        // TODO success page?
    }

//    @GetMapping("/update-account")
//    public String updateAccountGet(Principal principal, Model model) {
//        model.addAttribute("user", userService.findByUserEmail(principal.getName()));
//        return "public/create-update-account";
//    }
//
//    @PostMapping("/update-account")
//    public String updateUserPost(@Valid User user, BindingResult result, HttpServletRequest req, Model model) {
//        if (validateCreateAndUpdateErrors(user, result, req, model)) return "create-update-account";
//        user.setEnabled(user.getEnabled());
//        user.setApiKey(user.getApiKey());
//        userService.saveUser(user);
//        return "redirect:/dashboard";
//    }
//
//    //    TODO details in user dashboard
//    @GetMapping("/dashboard")
//    public String userDashboard(Model model) {
//
//        // send user snippet categories
//        // send user sippets
//
//        return "/user/dashboard";
//    }

    //    TODO details in user dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {

        // tudotudo tudododo

        return "/admin/dashboard";
    }

    @ModelAttribute("roleList")
    private List<String> getAllRoles() {
        return roleService.findAllRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
    }


}
