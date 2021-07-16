package work.szczepanskimichal.project13snippetapp.tag;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = Tag.TABLE_NAME)
@Data

public class Tag {

    static final String TABLE_NAME = "tags";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // can be null
    // user chooses from bootstrap-centric list
    @Column(columnDefinition = "varchar(50)")
    private String tagColor;

    @Column(columnDefinition = "varchar(50)")
    @Size(min = 3, max = 50)
    @NotEmpty
    private String tagName;



}
