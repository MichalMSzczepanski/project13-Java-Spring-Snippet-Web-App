package work.szczepanskimichal.project13snippetapp.user;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.role.RoleService;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserDetailsUpdateDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordUpdateDTO;
import work.szczepanskimichal.project13snippetapp.utils.EmailService;
import work.szczepanskimichal.project13snippetapp.utils.SimpleKeyGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final SimpleKeyGenerator simpleKeyGenerator;
    private final EmailService emailService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    //    TODO details in admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser.getUser());

        // preview logs?
        //

        return "/admin/dashboard";
    }

    @GetMapping("/create-account")
    public String adminCreateUserOrAdminGet(Model model) {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setApiKey(simpleKeyGenerator.generateApiKey());
        model.addAttribute("createUserDTO", createUserDTO);
        return "admin/create-account";
    }

    @PostMapping("/create-account")
    public String adminCreateUserOrAdminPost(@Valid CreateUserDTO createUserDTO, BindingResult result, @RequestParam String emailConfirmationFollowUp) {
        if (result.hasErrors()) {
            return "admin/create-account";
        }
//        User user = userService.adminConvertCreateUserDTOToUser(createUserDTO);
        User user = userMapper.CreateUserDTOtoUser(createUserDTO);
        user.setEnabled(createUserDTO.getEnabled());
        user.setRole(createUserDTO.getRole());
        user.setAccountKeyCreated(LocalDateTime.now());
        if(emailConfirmationFollowUp.equals("yes")) {
            String key = simpleKeyGenerator.generateAccountKey();
            user.setAccountKey(key);
            user.setAccountKeyCreated(LocalDateTime.now());
            user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
            emailService.sendEmail(createUserDTO.getEmail(), "confirm your email", "You have 24h to confirm your email at: http://localhost:8080/create-account/confirmation/" + key);
        }
        userService.adminSaveUser(user);
        return "redirect:/admin/user-details/" + user.getId();
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

    @GetMapping("/manage-user-accounts/{pageNumber}")
    public String manageUserAccountsGet(Model model, @PathVariable int pageNumber) {
        if (!(userService.getUsersForAdmin(pageNumber) == null)) {
            model.addAttribute("userList", userService.getUsersForAdmin(pageNumber));
            model.addAttribute("numberOfUsers", userService.countAllUsers());
            return "admin/manage-user-accounts";
        } else {
            return "redirect:/admin/manage-user-accounts/1";
        }
    }

    @GetMapping("/edit-user/{id}")
    public String editUserAccountGet(@PathVariable Long id, Model model) {
//        AdminUpdateUserDTO adminUpdateUserDTO = userService.adminConvertUserToAdminUpdateUserDTO(userService.findByUserId(id));
        AdminUpdateUserDTO adminUpdateUserDTO = userMapper.UsertoAdminUpdateUserDTO(userService.findByUserId(id));
        adminUpdateUserDTO.setPasswordConfirmation((userService.findByUserId(id)).getPassword());
        model.addAttribute("adminUpdateUserDTO", adminUpdateUserDTO);
        System.out.println("test object contents: " + adminUpdateUserDTO);
        return "admin/edit-user";
    }

    @PostMapping("/edit-user/{id}")
    public String editUserAccountPost(@Valid AdminUpdateUserDTO adminUpdateUserDTO, BindingResult result) {
        if (result.hasErrors()) {
//            TBC validation and below code
//            userService.convertAdminUpdateUserDTOToUser(adminUpdateUserDTO);
//            adminUpdateUserDTO.getPasswordConfirmation();
            return "admin/edit-user";
        }
        userService.update(userMapper.AdminUpdateUserDTOtoUser(adminUpdateUserDTO));
//        userService.update(userService.convertAdminUpdateUserDTOToUser(adminUpdateUserDTO));
        return "redirect:/admin/user-details/" + adminUpdateUserDTO.getId();
    }

    @GetMapping("/user-details/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findByUserId(id));
        return "admin/user-details";
    }

    // TODO test this method and add validation if you're not deleting yourself!
    @GetMapping("/delete/{id}")
    public String adminDeleteUser(@PathVariable Long id, Model model) {
        if (userService.isThisTheLastAdmin(userService.findByUserId(id))) {
            model.addAttribute("lastAdminConfirmed", true);
            return "public/error";
        } else {
            userService.delete(userService.findByUserId(id));
            return "redirect:/admin/manage-user-accounts";
        }
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
