package work.szczepanskimichal.project13snippetapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.role.Role;
import work.szczepanskimichal.project13snippetapp.role.RoleService;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordUpdateDTO;
import work.szczepanskimichal.project13snippetapp.utils.SimpleKeyGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
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
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setApiKey(simpleKeyGenerator.generateApiKey());
        model.addAttribute("createUserDTO", createUserDTO);
        return "admin/create-account";
    }

    @PostMapping("/create-account")
    public String adminCreateUserOrAdminPost(@Valid CreateUserDTO createUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/create-account";
        }
        User user = userService.adminConvertCreateUserDTOToUser(createUserDTO);
        userService.adminSaveUser(user);
        return "redirect:/admin/dashboard";
        // TODO success page?
    }

    @GetMapping("/update-admin-details")
    public String updateAccountGet(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        UserDetailsUpdateDTO userDetailsUpdateDTO = new UserDetailsUpdateDTO();
        userDetailsUpdateDTO = userDetailsUpdateDTO.userDTOFilledOut(currentUser);
        model.addAttribute("userDetails", userDetailsUpdateDTO);
        return "admin/update-admin-details";
    }

    @PostMapping("/update-admin-details")
    public String updateUserPost(@ModelAttribute("userDetails") @Valid UserDetailsUpdateDTO userDetailsUpdateDTO, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "admin/update-admin-details";
        }
        User updatedUser = currentUser.getUser();
        updatedUser.setUsername(userDetailsUpdateDTO.getUsername());
        updatedUser.setEmail(userDetailsUpdateDTO.getEmail());
        userService.update(updatedUser);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/update-admin-password")
    public String updateUserPasswordGet(Model model) {
        model.addAttribute("userPasswords", new UserPasswordUpdateDTO());
        return "admin/update-admin-password";
        //    TODO send email with link to change password?
    }

    @PostMapping("/update-admin-password")
    public String updateUserPasswordPost(@ModelAttribute("userPasswords") @Valid UserPasswordUpdateDTO userPasswordUpdateDTO, BindingResult result, HttpServletRequest request, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "admin/update-admin-password";
        }
        userService.updatePassword(currentUser.getUser(), request.getParameter("password"));
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //        doesn't redirect to homepage
        return "redirect:/";
    }

    @GetMapping("/manage-user-accounts")
    public String manageUserAccountsGet(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "admin/manage-user-accounts";
    }

    @GetMapping("/edit-user/{id}")
    public String editUserAccountGet(@PathVariable Long id, Model model) {
        AdminUpdateUserDTO adminUpdateUserDTO = userService.adminConvertUserToAdminUpdateUserDTO(userService.findByUserId(id));
        adminUpdateUserDTO.setPasswordConfirmation((userService.findByUserId(id)).getPassword());
        model.addAttribute("adminUpdateUserDTO", adminUpdateUserDTO);
        return "admin/edit-user";
    }

// TODO walidator do DTO dla maila u username'a ponizej
    @PostMapping("/edit-user/{id}")
    public String editUserAccountPost(@Valid AdminUpdateUserDTO adminUpdateUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(userService.convertAdminUpdateUserDTOToUser(adminUpdateUserDTO));
            System.out.println(adminUpdateUserDTO.getPasswordConfirmation());
            return "admin/edit-user";
        }
        userService.update(userService.convertAdminUpdateUserDTOToUser(adminUpdateUserDTO));
        return "redirect:/admin/user-details/" + adminUpdateUserDTO.getId();
    }

    @GetMapping("/user-details/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findByUserId(id));
        return "admin/user-details";
    }

    @GetMapping("/delete/{id}")
    public String adminDeleteUser(@PathVariable Long id) {
        userService.delete(userService.findByUserId(id));
        return "redirect:/admin/manage-user-accounts";
    }

    //    TODO details in admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser.getUser());

        // preview logs?
        //

        return "/admin/dashboard";
    }

    @ModelAttribute("roleList")
    public List<String> getAllRoles() {
        return roleService.findAllRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
    }

    @ModelAttribute("enabledOptions")
    public String[] getEnabledOptions() {
        return new String[]{"0", "1"};
    }

}
