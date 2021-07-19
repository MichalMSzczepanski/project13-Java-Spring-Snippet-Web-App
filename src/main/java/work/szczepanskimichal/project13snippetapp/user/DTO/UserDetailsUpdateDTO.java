package work.szczepanskimichal.project13snippetapp.user.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDetailsUpdateDTO {

    @Email
    @NotBlank(message="{email.not.blank.and.unique}")
    private String email;

    @Size(min = 3, max = 50)
    @NotBlank(message="{username.not.blank.and.unique}")
    private String username;

    public UserDetailsUpdateDTO userDTOFilledOut(CurrentUser currentUser) {
        UserDetailsUpdateDTO out = new UserDetailsUpdateDTO();
        out.setUsername(currentUser.getUser().getUsername());
        out.setEmail(currentUser.getUser().getEmail());
        return out;
    }

}
