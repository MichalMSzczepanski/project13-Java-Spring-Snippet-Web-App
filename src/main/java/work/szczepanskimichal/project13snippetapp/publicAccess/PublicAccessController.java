package work.szczepanskimichal.project13snippetapp.publicAccess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class PublicAccessController {

    private final UserService userService;
    private final EmailService emailService;

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
        model.addAttribute("user", new User());
        return "public/create-account";
    }

    @PostMapping("/create-account")
    public String createUserPost(@Valid User user, BindingResult result, HttpServletRequest req, Model model) throws NoSuchAlgorithmException {
        if (result.hasErrors()) {
            return "public/create-account";
        }
        user.setEnabled(0);
        // TODO create api key generator class with methods
        Random rand = new Random();
        int upperbound = 100000;
        int apiKey = rand.nextInt(upperbound);
        user.setApiKey(String.valueOf(apiKey));
        // TODO create validation key generator class with methods
        int accountKeyValidation = rand.nextInt(upperbound);
        user.setAccountKeyValidation(String.valueOf(accountKeyValidation));
        user.setAccountKeCreated(LocalDateTime.now());
        user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
        emailService.sendEmail(user.getEmail(), "Snippet App Email Confirmation", "Confirm your email at: http://localhost:8080/create-account/confirmation/" + user.getAccountKeyValidation());
        userService.saveUser(user);
        return "public/confirm-user-email";
    }

    @GetMapping("/create-account/confirmation/{key}")
    public String confirmAccount(@PathVariable String key) {
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

}
