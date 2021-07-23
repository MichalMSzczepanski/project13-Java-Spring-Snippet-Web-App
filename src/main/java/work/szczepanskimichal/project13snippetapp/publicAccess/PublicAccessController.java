package work.szczepanskimichal.project13snippetapp.publicAccess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.SimpleEmailService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PublicAccessController {

    private final UserService userService;
    private final SimpleEmailService emailService;

    @RequestMapping("/")
    public String getPublicHomepage() {
        return "public/homepage";
    }

    @GetMapping("/login")
    public String loginGet(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if (param.containsKey("error")) {
            request.setAttribute("loginError", true);
        }
        return "public/login";
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
        User user = userService.convertCreateUserDTOToUser(createUserDTO);
        userService.saveUser(user);
        emailService.sendEmail(user.getEmail(), "Snippet App Email Confirmation", "You have 24h to confirm your email at: http://localhost:8080/create-account/confirmation/" + user.getAccountKeyValidation());
        return "public/confirm-user-email";
    }

    @GetMapping("/create-account/confirmation/{key}")
    public String confirmAccount(@PathVariable String key) {
        // TODO check if validation key is expired
        if(userService.validateAccountKey(key)) {
            User user = userService.findByKey(key);
            user.setEnabled(1);
            user.setAccountKeyValidation(null);
            user.setAccountKeCreated(null);
            user.setAccountKeyExpirationDate(null);
            userService.update(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String notAllowed403() {
        System.out.println("flag1");
        return "public/403";
    }

}
