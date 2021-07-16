package work.szczepanskimichal.project13snippetapp.homepage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomepageController {

    @RequestMapping("/")
    public String getPublicHomepage() {
        return "public/homepage";
    }

    @GetMapping("/login")
    public String login() {
        return "public/login";
    }

    @PostMapping("/login")
    public String loginReturn() {
        return "public/homepage";
    }


}
