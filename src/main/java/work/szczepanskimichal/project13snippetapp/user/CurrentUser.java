package work.szczepanskimichal.project13snippetapp.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final work.szczepanskimichal.project13snippetapp.user.User user;

    public CurrentUser(String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       work.szczepanskimichal.project13snippetapp.user.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public work.szczepanskimichal.project13snippetapp.user.User getUser() {
        return user;
    }

}


