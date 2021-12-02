package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserSnippetController {

    private final SnippetService snippetService;

    @GetMapping({"/dashboard", "dashboard/{folder}","/dashboard/{folder}/{id}"})
    public String getUserDashboard(@AuthenticationPrincipal CurrentUser currentUser,
                                   @PathVariable(required = false) String folder,
                                   @PathVariable(required = false) Long id,
                                   Model model) {
        return snippetService.getUserDashboard(currentUser, folder, id, model);
    }

    @GetMapping("/add-snippet")
    public String userAddNewSnippetGet(@AuthenticationPrincipal CurrentUser currentUser,
                                       Model model) {
        snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
        return snippetService.userAddNewSnippetGet(model);
    }

    @PostMapping("/add-snippet")
    public String userAddNewSnippetPost(@AuthenticationPrincipal CurrentUser currentUser,
                                     @ModelAttribute("snippet") @Valid Snippet snippet,
                                     BindingResult result,
                                     HttpServletRequest request,
                                     Model model) {
        if(result.hasErrors()) {
            snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-manage";
        }
        return snippetService.userAddNewSnippetPost(currentUser, snippet, request);
    }

    @GetMapping("/edit-snippet/{id}")
    public String editUserSnippetGet(@AuthenticationPrincipal CurrentUser currentUser,
                                     @PathVariable Long id,
                                     Model model) {
        return snippetService.editUserSnippetGet(currentUser, id, model);
    }

    @PostMapping("/edit-snippet/{id}")
    public String editUserSnippetPost(@AuthenticationPrincipal CurrentUser currentUser,
                                      @Valid Snippet snippet,
                                      BindingResult result,
                                      HttpServletRequest request,
                                      Model model) {
        if(result.hasErrors()) {
            snippetService.getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-edit";
        }
        return snippetService.editUserSnippetPost(currentUser, snippet, request, model);
    }

    @GetMapping("/snippet-details/{id}")
    public String viewUserSnippetDetails(@AuthenticationPrincipal CurrentUser currentUser,
                                         @PathVariable Long id,
                                         Model model) {
        return snippetService.viewUserSnippetDetails(currentUser, id, model);
    }

    @GetMapping("/delete-snippet/{id}")
    public String deleteUserSnippet(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id) {
        return snippetService.deleteUserSnippetById(id, currentUser);
    }

}
