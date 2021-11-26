package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final UserService userService;
    private final TagService tagService;
    private final UtilLists utilLists;

    public String returnUserDashboard(CurrentUser currentUser, String folder, Long id, Model model){
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

    public List<Snippet> findAllUserSnippets(String email) {
        return snippetRepository.findAllUserSnippets(email);
    }

    public List<Snippet> findAllUserSnippetsByFolder(String folder, User owner) {
        return snippetRepository.findSnippetsByFolderAndOwner(folder, owner);
    }

    public List<String> findAllUserFolders(String email) {
        return snippetRepository.findAllFoldersOfUser(email);
    }

    public void saveSnippet(Snippet snippet, Long id) {
        snippet.setOwner(userService.findByUserId(id));
        snippetRepository.save(snippet);
    }

    public Snippet getUserSnippetByID(Long id) {
        return snippetRepository.getById(id);
    }

    public String deleteUserSnippetById(Long id, CurrentUser currentUser) {
        if (snippetRepository.existsById(id)) {
            if (getUserSnippetByID(id).getOwner().getId() == currentUser.getUser().getId()) {
                // add confirmation in popup or another widok
                snippetRepository.delete(getUserSnippetByID(id));
                return "redirect:/user/dashboard";
            }
        }
        return "public/snippetErrorDeleted";
    }

    public void setSnippetFolder(Snippet snippet, String inputtedFolder) {
        if (inputtedFolder != null && inputtedFolder != "") {
            snippet.setFolder(inputtedFolder);
        }
    }

    public void setSnippetTagsAndUserTags(Snippet snippet, String inputtedTags, User currentUser) {
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

    public void getProgrammingLanguagesAndUserFoldersAndUserTags(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        List<String> programmingLanguages = Languages.getLanguages();
        List<String> folderList = (findAllUserFolders(currentUser.getUser().getEmail()).isEmpty() || findAllUserFolders(currentUser.getUser().getEmail()) == null)
                ? utilLists.getDefaultFolder() : findAllUserFolders(currentUser.getUser().getEmail());
        List<Tag> userTags = currentUser.getUser().getTags();
        model.addAttribute("programmingLanguages", programmingLanguages);
        model.addAttribute("folderList", folderList);
        model.addAttribute("userTags", userTags);
    }

    //TODO method for adding all values into snippet instead of controller method .... balagan

}
