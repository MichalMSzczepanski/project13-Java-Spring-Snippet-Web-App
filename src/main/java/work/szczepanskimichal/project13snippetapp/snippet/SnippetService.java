package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.szczepanskimichal.project13snippetapp.tag.Tag;
import work.szczepanskimichal.project13snippetapp.tag.TagService;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final UserService userService;
    private final TagService tagService;

    public List<Snippet> findAllUserSnippets(String email) {
        return snippetRepository.findAllUserSnippets(email);
    }

    public List<Snippet> findAllUserSnippetsByFolder(String folder, User owner) {
        return snippetRepository.findSnippetsByFolderAndOwner(folder, owner);
    }

    public List<String> findAllUserFolders(String email) { return snippetRepository.findAllFoldersOfUser(email); }

    public void saveSnippet(Snippet snippet, Long id) {
        snippet.setOwner(userService.findByUserId(id));
        snippetRepository.save(snippet);
    }

    public Snippet getUserSnippetByID(Long id) { return snippetRepository.findById(id).orElse(null); }

    public void deleteUserSnippetById(Long id) {
        snippetRepository.delete(getUserSnippetByID(id));
    }

    public void setSnippetFolder(Snippet snippet, String inputtedFolder) {
        if(inputtedFolder != null && inputtedFolder != "") {
            snippet.setFolder(inputtedFolder);
        }
    }

    public void setSnippetTagsAndUserTags(Snippet snippet, String inputtedTags, User currentUser) {
        if(inputtedTags != null && inputtedTags != "") {
            List<String> newTagStrings = Arrays.asList(inputtedTags.trim().split("\\s*,+\\s*,*\\s*"));


            List<Tag> currentSnippetTags = (snippet.getTags() != null ? snippet.getTags() : new ArrayList<>());
            List<Tag> newTags = newTagStrings.stream()
                    .map(s -> new Tag(null, s, null))
                    .collect(Collectors.toList());

            List<Tag> userTags = (currentUser.getTags() != null ? currentUser.getTags() : new ArrayList<>());
            for(Tag tag : newTags) {
                currentSnippetTags.add(tag);
                userTags.add(tag);
                tagService.save(tag);
            }
            userService.update(currentUser);
        }
    }

    //TODO method for adding all values into snippet instead of controller method .... balagan

}
