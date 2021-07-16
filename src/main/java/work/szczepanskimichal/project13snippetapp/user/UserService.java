package work.szczepanskimichal.project13snippetapp.user;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}