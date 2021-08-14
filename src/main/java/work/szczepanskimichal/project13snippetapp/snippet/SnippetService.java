package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final UserService userService;

    public List<Snippet> findAllUserSnippets(String email) {
        return snippetRepository.findAllUserSnippets(email);
    }
    public List<String> findAllFoldersOfUser(String email) {
        return snippetRepository.findAllFoldersOfUser(email);
    }

    public void saveSnippet(Snippet snippet, Long id) {
        snippet.setOwner(userService.findByUserId(id));
        snippetRepository.save(snippet);
    }

    public Snippet getUserSnippetByID(Long id) {
        return snippetRepository.findById(id).orElse(null);
    }

    public void deleteUserSnippetById(Long id) {
        snippetRepository.delete(getUserSnippetByID(id));
    }

    //method for adding all values into snippet instead of controller method .... balagan

}
