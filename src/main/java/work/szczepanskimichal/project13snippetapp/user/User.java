package work.szczepanskimichal.project13snippetapp.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import work.szczepanskimichal.project13snippetapp.role.Role;
import work.szczepanskimichal.project13snippetapp.validator.PasswordMatch;
import work.szczepanskimichal.project13snippetapp.validator.UniqueEmail;
import work.szczepanskimichal.project13snippetapp.validator.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table (name = User.TABLE_NAME)
@Data
@NoArgsConstructor
public class User {

    static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotBlank(message="{email.not.blank.and.unique}")
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    @Size(min = 3, max = 50)
    @NotBlank(message="{username.not.blank.and.unique}")
    private String username;

    @NotBlank(message="{password.blank}")
    private String password;

    private int enabled;

    @ManyToOne
    private Role role;
    private String apiKey;
    private String accountKeyValidation;
    private LocalDateTime accountKeCreated;
    private LocalDateTime accountKeyExpirationDate;


//    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//    }
}
