package work.szczepanskimichal.project13snippetapp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;
import work.szczepanskimichal.project13snippetapp.role.Role;
import work.szczepanskimichal.project13snippetapp.role.RoleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByKey(String key) { return userRepository.findByAccountKeyValidation(key); }

    public Boolean validateAccountKey(String key) {
        if (userRepository.findByAccountKeyValidation(key) != null) {
            LocalDateTime expirationDate = userRepository.findByAccountKeyValidation(key).getAccountKeyExpirationDate();
            if(LocalDateTime.now().compareTo(expirationDate) < 1) {
//                System.out.println("flag1: " +  (LocalDateTime.now().compareTo(expirationDate) < 1));
                return true;
            }
        }
//        System.out.println("flag2: " + (LocalDateTime.now().compareTo(userRepository.findByAccountKeyValidation(key).getAccountKeyExpirationDate()) < 1));
        return false;
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
