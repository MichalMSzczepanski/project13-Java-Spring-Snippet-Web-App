package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.Languages;
import work.szczepanskimichal.project13snippetapp.utils.UtilLists;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
//@Secured("ROLE_USER")
public class UserSnippetController {

    private final UtilLists utilLists;
    private final SnippetService snippetService;

    //    TODO details in user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("currentUser", currentUser.getUser());
        List<String> folderList = snippetService.findAllFoldersOfUser(currentUser.getUser().getEmail());
        List<Snippet> snippetList = snippetService.findAllUserSnippets(currentUser.getUser().getEmail());
        model.addAttribute("folderList", folderList);
        model.addAttribute("snippetList", snippetList);
        return "user/dashboard";
    }

    @GetMapping("/add-snippet")
    public String userAddSnippet(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        getProgrammingLanguagesAndUserFolders(currentUser, model);
        model.addAttribute("snippet", new Snippet());
        return "user/user-snippet-add";
    }

    @PostMapping("/add-snippet")
    public String userAddSnippetPost(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute("snippet") @Valid Snippet snippet, BindingResult result, HttpServletRequest request, Model model) {
        System.out.println("this is the snippet: " + snippet);
        if(result.hasErrors()) {
            getProgrammingLanguagesAndUserFolders(currentUser, model);
            return "user/user-snippet-add";
        }
        if(request.getParameter("inputedFolder") != null & request.getParameter("inputedFolder") != "") {
            snippet.setFolder(request.getParameter("inputedFolder"));
        }
        snippetService.saveSnippet(snippet, currentUser.getUser().getId());
        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    @GetMapping("/edit-snippet/{id}")
    public String editUserSnippetGet(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id, Model model) {
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("snippet", snippet);
            getProgrammingLanguagesAndUserFolders(currentUser, model);
            return "user/user-snippet-edit";
        } else {
            return "public/error";
        }
    }

    @PostMapping("/edit-snippet/{id}")
    public String editUserSnippetPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid Snippet snippet, BindingResult result, HttpServletRequest request, Model model) {
        if(result.hasErrors()) {
            getProgrammingLanguagesAndUserFolders(currentUser, model);
            return "user/user-snippet-edit";
        }
        if(request.getParameter("inputedFolder") != null & request.getParameter("inputedFolder") != "") {
            snippet.setFolder(request.getParameter("inputedFolder"));
        }
        snippetService.saveSnippet(snippet, currentUser.getUser().getId());
        return "redirect:/user/user-snippet-details/" + snippet.getId();
    }

    // view single snippet
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
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            snippetService.deleteUserSnippetById(id);
            return "user/dashboard";
        } else {
            return "public/error";
        }
    }

    private void getProgrammingLanguagesAndUserFolders(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<String> programmingLanguages = utilLists.getLanguages();
//        List<String> programmingLanguages = Languages.getLanguages();
//        List<String> temp = utilLists.getDefaultFolder();
        List<String> folderList = (snippetService.findAllFoldersOfUser(currentUser.getUser().getEmail()).isEmpty() || snippetService.findAllFoldersOfUser(currentUser.getUser().getEmail()) == null)
                ? utilLists.getDefaultFolder() : snippetService.findAllFoldersOfUser(currentUser.getUser().getEmail());
        model.addAttribute("programmingLanguages", programmingLanguages);
        model.addAttribute("folderList", folderList);
    }

}
