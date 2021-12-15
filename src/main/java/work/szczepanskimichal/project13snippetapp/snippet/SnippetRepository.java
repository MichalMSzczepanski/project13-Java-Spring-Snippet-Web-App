package work.szczepanskimichal.project13snippetapp.snippet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work.szczepanskimichal.project13snippetapp.tag.Tag;
import work.szczepanskimichal.project13snippetapp.user.User;

import javax.mail.Folder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    // crud
    Snippet save(Snippet snippet);
    void delete(Snippet snippet);
    boolean existsById(Long id);
    void deleteById(Long id);

    // admin master queries (public nad private)
    List<Snippet> findSnippetsById(Long id);
    List<Snippet> findSnippetsByVisibility(String visibility);
    List<Snippet> findSnippetsByProgrammingLanguage(String programmingLanugage);
    List<Snippet> findSnippetsByFolder(String folder);
    List<Snippet> findSnippetsByTitle(String title);
    @Query("select s from Snippet s where s.snippetContent like %?1%")
    List<Snippet> findSnippetsBySearchQuery(String searchQuery);
    List<Snippet> findSnippetByFavorite(Boolean favorite);
    List<Snippet> findSnippetsByTags(Tag tag);
    List<Snippet> findSnippetsByOwner(User owner);

    // public general queries for other user snippets
    @Query("select s from Snippet s where s.visibility = 'public'")
    List<Snippet> findAllPublicSnippets();
    @Query("select s from Snippet s where s.programmingLanguage like %?1% and s.visibility = 'public'")
    List<Snippet> findPublicSnippetsByLanguage(String handle);
    @Query("select s from Snippet s where s.snippetContent like %?1% and s.visibility = 'public'")
    List<Snippet> findPublicSnippetsBySearchQuery(String searchQuery);
    @Query("select s from Snippet s JOIN FETCH s.tags t where t.tagName like %?1%")
    List<Snippet> findPublicSnippetsByTag(String searchQuery);
    @Query("select s from Snippet s where s.owner.username like %?1% and s.visibility = 'public'")
    List<Snippet> findAllPublicSnippetsbyOwnerHandle(String handle);

    // user general queries
//    List<Snippet> findSnippetsByIdAndOwnerEquals(Long id, User owner);

    @Query("select s from Snippet s where s.owner.email like %?1%")
    List<Snippet> findAllUserSnippets(String email);

    @Query("select distinct s.folder from Snippet s where s.owner.email like %?1%")
    List<String> findAllFoldersOfUser(String email);

    List<Snippet> findSnippetsByFolderAndOwner(String folder, User owner);



;//    List<Snippet> findSnippetsByVisibility(String visibility);
//    List<Snippet> findSnippetsByProgrammingLanguage(String programmingLanugage);
//    List<Snippet> findSnippetsByFolder(String folder);
//    List<Snippet> findSnippetsByTitle(String title);
//    @Query("select s from Snippet s where s.snippetContent like %?1%")
//    List<Snippet> findSnippetsBySearchQuery(String searchQuery);
//    List<Snippet> findSnippetByFavorite(Boolean favorite);
//    List<Snippet> findSnippetsByTags(Tag tag);
//    List<Snippet> findSnippetsByOwner(User owner);

}
