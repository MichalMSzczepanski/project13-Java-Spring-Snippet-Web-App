package work.szczepanskimichal.project13snippetapp.homepage;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedInUsername", "loggedinUserID", "loggedinUserEmail"})
public class HomepageController {

    UserService userService;

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

    @PostMapping("/login")
    public String loginPost(Model model, Principal principal) {
        model.addAttribute("loggedInUsername", principal.getName());
        return "public/homepage";
    }


}
