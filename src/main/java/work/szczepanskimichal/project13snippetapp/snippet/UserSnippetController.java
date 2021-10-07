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
    private final UserService userService;
    private final TagService tagService;

//    //    TODO details in user dashboard
//    @GetMapping("/dashboard")
//    public String userDashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
//        model.addAttribute("currentUser", currentUser.getUser());
//        List<String> folderList = snippetService.findAllUserFolders(currentUser.getUser().getEmail());
//        List<Snippet> snippetList = snippetService.findAllUserSnippets(currentUser.getUser().getEmail());
//        model.addAttribute("folderList", folderList);
//        model.addAttribute("snippetList", snippetList);
//        return "user/dashboard";
//    }

    @GetMapping({"/dashboard", "dashboard/{folder}","/dashboard/{folder}/{id}"})
    public String userDashboardByFolder(@AuthenticationPrincipal CurrentUser currentUser,
                                        @PathVariable(required = false) String folder,
                                        @PathVariable(required = false) Long id, Model model) {
        model.addAttribute("currentUser", currentUser.getUser());
        List<String> folderList = snippetService.findAllUserFolders(currentUser.getUser().getEmail());
        model.addAttribute("folderList", folderList);
        if (folder != null) {
            List<Snippet> snippetList = snippetService.findAllUserSnippetsByFolder(folder, currentUser.getUser());
            model.addAttribute("snippetList", snippetList);
            if (id != null){
                Snippet currentSnippet = snippetService.getUserSnippetByID(id);
                model.addAttribute("currentSnippet", currentSnippet);
            }
        }
        return "user/dashboard";
    }

    @GetMapping("/add-snippet")
    public String userAddSnippet(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
        model.addAttribute("snippet", new Snippet());
        model.addAttribute("newSnippet", true);
        return "user/user-snippet-manage";
    }

    @PostMapping("/add-snippet")
    public String userAddSnippetPost(@AuthenticationPrincipal CurrentUser currentUser,
                                     @ModelAttribute("snippet") @Valid Snippet snippet,
                                     BindingResult result, HttpServletRequest request, Model model) {
        if(result.hasErrors()) {
            getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-manage";
        }

        String inputtedFolder = request.getParameter("inputtedFolder");
        if(inputtedFolder != null && inputtedFolder != "") {
            snippet.setFolder(inputtedFolder);
        }

        String inputtedTags = request.getParameter("inputtedTags");
        if(inputtedTags != null && inputtedTags != "") {
            List<String> newTagStrings = Arrays.asList(inputtedTags.trim().split("\\s*,+\\s*,*\\s*"));


            List<Tag> currentSnippetTags = (snippet.getTags() != null ? snippet.getTags() : new ArrayList<>());
            List<Tag> newTags = newTagStrings.stream()
                    .map(s -> new Tag(null, s, null))
                    .collect(Collectors.toList());

            User user = currentUser.getUser();
            List<Tag> userTags = (user.getTags() != null ? user.getTags() : new ArrayList<>());
            for(Tag tag : newTags) {
                currentSnippetTags.add(tag);
                userTags.add(tag);
                tagService.save(tag);
            }
            userService.update(user);
        }
        snippetService.saveSnippet(snippet, currentUser.getUser().getId());
        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    @GetMapping("/edit-snippet/{id}")
    public String editUserSnippetGet(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id, Model model) {
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("snippet", snippet);
            getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
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
            getProgrammingLanguagesAndUserFoldersAndUserTags(currentUser, model);
            return "user/user-snippet-edit";
        }
        if(request.getParameter("inputedFolder") != null & request.getParameter("inputedFolder") != "") {
            snippet.setFolder(request.getParameter("inputedFolder"));
        }
        String inputtedTags = request.getParameter("inputtedTags");
        if(inputtedTags != null && inputtedTags != "") {
            List<String> newTagStrings = Arrays.asList(inputtedTags.trim().split("\\s*,+\\s*,*\\s*"));


            List<Tag> currentSnippetTags = (snippet.getTags() != null ? snippet.getTags() : new ArrayList<>());
            List<Tag> newTags = newTagStrings.stream()
                    .map(s -> new Tag(null, s, null))
                    .collect(Collectors.toList());

            User user = currentUser.getUser();
            List<Tag> userTags = (user.getTags() != null ? user.getTags() : new ArrayList<>());
            for(Tag tag : newTags) {
                currentSnippetTags.add(tag);
                userTags.add(tag);
                tagService.save(tag);
            }
            userService.update(user);
        }
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
        Snippet snippet = snippetService.getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            snippetService.deleteUserSnippetById(id);
            return "user/dashboard";
        } else {
            return "public/error";
        }
    }

    private void getProgrammingLanguagesAndUserFoldersAndUserTags(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<String> programmingLanguages = Languages.getLanguages();
        List<String> folderList = (snippetService.findAllUserFolders(currentUser.getUser().getEmail()).isEmpty() || snippetService.findAllUserFolders(currentUser.getUser().getEmail()) == null)
                ? utilLists.getDefaultFolder() : snippetService.findAllUserFolders(currentUser.getUser().getEmail());
        List<Tag> userTags = currentUser.getUser().getTags();
        model.addAttribute("programmingLanguages", programmingLanguages);
        model.addAttribute("folderList", folderList);
        model.addAttribute("userTags", userTags);
    }

}
