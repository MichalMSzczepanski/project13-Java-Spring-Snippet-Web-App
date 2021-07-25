package work.szczepanskimichal.project13snippetapp.user.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.validator.*;

@Data
@NoArgsConstructor
@AdminUniqueEmail
@AdminUniqueUsername
public class AdminUpdateUserDTO extends User {

    private String passwordConfirmation;

}
