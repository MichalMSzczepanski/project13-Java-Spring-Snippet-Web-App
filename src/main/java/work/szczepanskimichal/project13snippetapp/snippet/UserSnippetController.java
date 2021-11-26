package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.tag.Tag;
import work.szczepanskimichal.project13snippetapp.tag.TagService;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.Languages;
import work.szczepanskimichal.project13snippetapp.utils.UtilLists;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
//@Secured("ROLE_USER")
public class UserSnippetController {

    private final UtilLists utilLists;
    private final SnippetService snippetService;

    @GetMapping({"/dashboard", "dashboard/{folder}","/dashboard/{folder}/{id}"})
    public String getUserDashboard(@AuthenticationPrincipal CurrentUser currentUser,
                                        @PathVariable(required = false) String folder,
                                        @PathVariable(required = false) Long id, Model model) {
        return snippetService.returnUserDashboard(currentUser, folder, id, model);
    }

    @GetMapping("/add-snippet")
    public String userAddSnippet(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
        model.addAttribute("snippet", new Snippet());
        model.addAttribute("newSnippet", true);
        return "user/user-snippet-manage";
    }

    @PostMapping("/add-snippet")
    public String userAddSnippetPost(@AuthenticationPrincipal CurrentUser currentUser,
                                     @ModelAttribute("snippet") @Valid Snippet snippet,
                                     BindingResult result, HttpServletRequest request, Model model) {
        if(result.hasErrors()) {
            snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-manage";
        }

        snippetService.setSnippetFolder(snippet, request.getParameter("inputtedFolder"));
        snippetService.setSnippetTagsAndUserTags(snippet,request.getParameter("inputtedTags") , currentUser.getUser());
        snippetService.saveSnippet(snippet, currentUser.getUser().getId());

        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    @GetMapping("/edit-snippet/{id}")
    public String editUserSnippetGet(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id, Model model) {
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("snippet", snippet);
            snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
//            return "user/user-snippet-edit";
            model.addAttribute("newSnippet", false);
            return "user/user-snippet-manage";
        } else {
            return "public/error";
        }
    }

    @PostMapping("/edit-snippet/{id}")
    public String editUserSnippetPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid Snippet snippet, BindingResult result, HttpServletRequest request, Model model) {
        if(result.hasErrors()) {
            snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-edit";
        }

        snippetService.setSnippetFolder(snippet, request.getParameter("inputtedFolder"));
        snippetService.setSnippetTagsAndUserTags(snippet,request.getParameter("inputtedTags") , currentUser.getUser());
        snippetService.saveSnippet(snippet, currentUser.getUser().getId());

        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    @GetMapping("/snippet-details/{id}")
    public String viewUserSnippetDetails(@AuthenticationPrincipal CurrentUser currentUser,@PathVariable Long id, Model model) {
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("snippet", snippet);
            return "user/user-snippet-details";
        } else {
            return "public/error";
        }
    }

    @GetMapping("/delete-snippet/{id}")
    public String deleteUserSnippet(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id) {
        return snippetService.deleteUserSnippetById(id, currentUser);
    }

}
