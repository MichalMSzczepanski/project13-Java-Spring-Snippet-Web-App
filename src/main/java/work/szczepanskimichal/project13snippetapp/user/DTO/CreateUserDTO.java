package work.szczepanskimichal.project13snippetapp.user.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.user.User;
import work.szczepanskimichal.project13snippetapp.validator.PasswordMatch;
import work.szczepanskimichal.project13snippetapp.validator.UniqueEmail;
import work.szczepanskimichal.project13snippetapp.validator.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordMatch
public class CreateUserDTO extends User {

    private String passwordConfirmation;

}
