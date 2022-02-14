package work.szczepanskimichal.project13snippetapp.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.szczepanskimichal.project13snippetapp.utils.Colors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = Tag.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {

    static final String TABLE_NAME = "tags";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    @Size(min = 3, max = 50)
    @NotEmpty
    private String tagName;

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private Colors tagColor;

}
