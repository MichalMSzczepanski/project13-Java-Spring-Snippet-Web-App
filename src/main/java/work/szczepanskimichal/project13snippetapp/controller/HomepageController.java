package work.szczepanskimichal.project13snippetapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomepageController {

    @RequestMapping("/")
    @ResponseBody
    public String start() {
        return "hello";
    }

}
