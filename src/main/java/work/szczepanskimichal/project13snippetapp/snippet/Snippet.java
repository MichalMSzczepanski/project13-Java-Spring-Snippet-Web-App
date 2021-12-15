package work.szczepanskimichal.project13snippetapp.snippet;

import lombok.Data;
import work.szczepanskimichal.project13snippetapp.tag.Tag;
import work.szczepanskimichal.project13snippetapp.user.User;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Snippet.TABLE_NAME)
@Data
public class Snippet {

    static final String TABLE_NAME = "snippets";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user chooses from list
    @Column(columnDefinition = "varchar(50) default 'public'")
    @NotBlank
    private String visibility;

    // user chooses from list
    @Column(columnDefinition = "varchar(50)")
    @NotBlank
    private String programmingLanguage;

    @Column(columnDefinition = "varchar(255) default 'uncategorized'")
//    @NotBlank
    private String folder;

    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    @Size(min = 5, max = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(min=5, max=65535)
    private String snippetContent;

    @Column(columnDefinition = "BIT default 0")
    private Boolean favorite;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @ManyToMany
    private List<Tag> tags;

    @ManyToOne
    private User owner;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

}
