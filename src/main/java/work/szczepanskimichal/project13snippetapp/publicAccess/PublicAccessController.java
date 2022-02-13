package work.szczepanskimichal.project13snippetapp.publicAccess;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.UserPasswordResetDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserMapper;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.EmailService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PublicAccessController {

    private final UserService userService;
    private final EmailService emailService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @RequestMapping("/")
    public String getPublicHomepage() {
        return "public/homepage";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if (param.containsKey("error")) {

            request.setAttribute("loginError", true);
        }
//        log.info("user logged in from IP: " + request.getRemoteAddr());
        return "public/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
//        log.info("user logged out: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember-me")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        return "redirect:/";
    }

    @GetMapping("/create-account")
    public String createUserGet(Model model) {
        model.addAttribute("createUserDTO", new CreateUserDTO());
        return "public/create-account";
    }

    @PostMapping("/create-account")
    public String createUserPost(@Valid CreateUserDTO createUserDTO, BindingResult result) throws NoSuchAlgorithmException {
        if (result.hasErrors()) {
            return "public/create-account";
        }
        User user = userMapper.createUserDTOtoUser(createUserDTO);
        userService.initialUserSetup(user);
        emailService.sendEmail(user.getEmail(), "Snippet App Email Confirmation", "You have 24h to confirm your email at: http://localhost:8080/create-account/confirmation/" + user.getAccountKey());
        userService.saveUser(user);
        return "public/confirm-user-email";
    }

    @GetMapping("/create-account/confirmation/{key}")
    public String confirmAccount(@PathVariable String key) {
        if (userService.validateAccountKey(key)) {
            User user = userService.findByKey(key);
            user.setEnabled(1);
            user.setAccountKey(null);
            user.setAccountKeyExpirationDate(null);
            userService.update(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/reset-password")
    public String resetPasswordGet() {
        return "public/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPasswordEmailValidation(@RequestParam String email, Model model) {
        if (email.isEmpty() || !userService.emailIsRegistered(email)) {
            model.addAttribute("emailNotRegistered", "Email not registered");
            return "public/reset-password";
        }
        userService.emailPasswordResetLink(email);
        return "redirect:/login";
    }

    @GetMapping("/reset-password/{key}")
    public String resetPasswordExecutionGet(@PathVariable String key,
                                         Model model) {
        if (userService.validatePasswordResetKey(key)) {
            UserPasswordResetDTO userPasswordResetDTO = new UserPasswordResetDTO();
            model.addAttribute("userPasswordResetDTO", userPasswordResetDTO);
            return "public/reset-password-confirmation";
        }
        return "redirect:/login";
    }

    @Transactional
    @PostMapping("/reset-password/{key}")
    public String resetPasswordExecutionPost(@PathVariable String key,
                                             @Valid UserPasswordResetDTO userPasswordResetDTO,
                                             BindingResult result) {
        if (result.hasErrors()) {
            return "public/reset-password-confirmation";
        }
        userService.updatePassword(userService.findByKey(key), userPasswordResetDTO.getPassword());
        userService.removeUserAccountKey(userService.findByKey(key));
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String notAllowed403() {
        return "public/403";
    }

}
