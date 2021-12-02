package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import work.szczepanskimichal.project13snippetapp.tag.Tag;
import work.szczepanskimichal.project13snippetapp.tag.TagService;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;
import work.szczepanskimichal.project13snippetapp.utils.Languages;
import work.szczepanskimichal.project13snippetapp.utils.UtilLists;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final UserService userService;
    private final TagService tagService;
    private final UtilLists utilLists;

    public String getUserDashboard(CurrentUser currentUser,
                                      String folder,
                                      Long id,
                                      Model model){
        model.addAttribute("currentUser", currentUser.getUser());
        List<String> folderList = findAllUserFolders(currentUser.getUser().getEmail());
        model.addAttribute("folderList", folderList);
        if (folder != null) {
            List<Snippet> snippetList = findAllUserSnippetsByFolder(folder, currentUser.getUser());
            model.addAttribute("snippetList", snippetList);
            if (id != null){
                Snippet currentSnippet = getUserSnippetByID(id);
                model.addAttribute("currentSnippet", currentSnippet);
            }
        }
        return "user/dashboard";
    }

    public String userAddNewSnippetGet(Model model) {
        model.addAttribute("snippet", new Snippet());
        model.addAttribute("newSnippet", true);
        return "user/user-snippet-manage";
    }

    public String userAddNewSnippetPost(CurrentUser currentUser,
                                        Snippet snippet,
                                        HttpServletRequest request) {
        setSnippetFolder(snippet, request.getParameter("inputtedFolder"));
        setSnippetTagsAndUserTags(snippet,request.getParameter("inputtedTags") , currentUser.getUser());
        saveSnippet(snippet, currentUser.getUser().getId());
        log.info("User: " + currentUser.getUsername() + " added snippet: " + snippet.getId());
        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    public String editUserSnippetGet(CurrentUser currentUser,
                                     Long id,
                                     Model model) {
        Snippet snippet = getUserSnippetByID(id);
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

    public String editUserSnippetPost(CurrentUser currentUser,
                                      Snippet snippet,
                                      HttpServletRequest request,
                                      Model model) {
        setSnippetFolder(snippet, request.getParameter("inputtedFolder"));
        setSnippetTagsAndUserTags(snippet,request.getParameter("inputtedTags") , currentUser.getUser());
        saveSnippet(snippet, currentUser.getUser().getId());
        log.info("User: " + currentUser.getUsername() + " edited snippet: " + snippet.getId());
        return "redirect:/user/snippet-details/" + snippet.getId();
    }

    public String viewUserSnippetDetails(CurrentUser currentUser,
                                         Long id,
                                         Model model) {
        Snippet snippet = getUserSnippetByID(id);
        if(snippet.getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("snippet", snippet);
            return "user/user-snippet-details";
        } else {
            log.info("ERROR while User: " + currentUser.getUsername() + " tried to edit foreign snippet: " + snippet.getId());
            return "public/error";
        }
    }

    public String deleteUserSnippetById(Long id,
                                        CurrentUser currentUser) {
        if (snippetRepository.existsById(id)) {
            if (getUserSnippetByID(id).getOwner().getId() == currentUser.getUser().getId()) {
                // add confirmation in popup or another widok
                snippetRepository.delete(getUserSnippetByID(id));
                log.info("User: " + currentUser.getUsername() + " deleted snippet: " + snippetRepository.getById(id).getId());
                return "redirect:/user/dashboard";
            }
        }
        log.info("ERROR while User: " + currentUser.getUsername() + " tried to delete snippet: " + snippetRepository.getById(id).getId());
        return "public/snippetErrorDeleted";
    }

    public List<Snippet> findAllUserSnippets(String email) {
        return snippetRepository.findAllUserSnippets(email);
    }

    public List<Snippet> findAllUserSnippetsByFolder(String folder, User owner) {
        return snippetRepository.findSnippetsByFolderAndOwner(folder, owner);
    }

    public List<String> findAllUserFolders(String email) {
        return snippetRepository.findAllFoldersOfUser(email);
    }

    public void saveSnippet(Snippet snippet,
                            Long id) {
        snippet.setOwner(userService.findByUserId(id));
        snippetRepository.save(snippet);
    }

    public Snippet getUserSnippetByID(Long id) {
        return snippetRepository.getById(id);
    }

    public void setSnippetFolder(Snippet snippet,
                                 String inputtedFolder) {
        if (inputtedFolder != null && inputtedFolder != "") {
            snippet.setFolder(inputtedFolder);
        }
    }

    public void setSnippetTagsAndUserTags(Snippet snippet,
                                          String inputtedTags,
                                          User currentUser) {
        List<String> newTagStrings = new ArrayList<>();
        if (inputtedTags != null && inputtedTags != "") {
            newTagStrings = Arrays.asList(inputtedTags.trim().split("\\s*,+\\s*,*\\s*"));
        }
        List<Tag> currentTags = (snippet.getTags() != null ? snippet.getTags() : new ArrayList<>());

        List<Tag> userTags = (currentUser.getTags() != null ? currentUser.getTags() : new ArrayList<>());
        for (String string : newTagStrings) {
            Tag newTag = Tag.builder().tagName(string).tagColor(null).build();
            currentTags.add(newTag);
            userTags.add(newTag);
            tagService.save(newTag);
        }
        snippet.setTags(currentTags);
        snippetRepository.save(snippet);

    }

    public void getProgrammingLanguagesAndUserFoldersAndUserTags(@AuthenticationPrincipal CurrentUser currentUser,
                                                                 Model model) {
        List<String> programmingLanguages = Languages.getLanguages();
        List<String> folderList = (findAllUserFolders(currentUser.getUser().getEmail()).isEmpty() || findAllUserFolders(currentUser.getUser().getEmail()) == null)
                ? utilLists.getDefaultFolder() : findAllUserFolders(currentUser.getUser().getEmail());
        List<Tag> userTags = currentUser.getUser().getTags();
        model.addAttribute("programmingLanguages", programmingLanguages);
        model.addAttribute("folderList", folderList);
        model.addAttribute("userTags", userTags);
    }

    //TODO method for adding all values into snippet instead of controller method

}
