package work.szczepanskimichal.project13snippetapp.user.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import work.szczepanskimichal.project13snippetapp.user.CurrentUser;
import work.szczepanskimichal.project13snippetapp.validator.PasswordMatch;
import work.szczepanskimichal.project13snippetapp.validator.UpdatePasswordMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@UpdatePasswordMatch
public class UserPasswordUpdateDTO {

    @NotBlank(message="{password.blank}")
    private String oldPassword;

    //  1 lowercase letter, 1 uppercase letter, 1 number, 1 special character and at least 8 characters long
//    @Pattern(regexp = "(?=(.*[0-9]))(?=.*[\\!@#$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,.\\/?])(?=.*\\[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}", message="{password.pattern}")
    @NotBlank(message="{password.blank}")
    private String password;

    //  1 lowercase letter, 1 uppercase letter, 1 number, 1 special character and at least 8 characters long
//    @Pattern(regexp = "(?=(.*[0-9]))(?=.*[\\!@#$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,.\\/?])(?=.*\\[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}", message="{password.pattern}")
    @NotBlank(message="{password.blank}")
    private String passwordConfirmation;

}
