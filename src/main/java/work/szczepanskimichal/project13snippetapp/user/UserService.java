package work.szczepanskimichal.project13snippetapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.szczepanskimichal.project13snippetapp.role.RoleRepository;
import work.szczepanskimichal.project13snippetapp.utils.EmailService;
import work.szczepanskimichal.project13snippetapp.utils.KeyGenerator;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final KeyGenerator keyGenerator;
    private final EmailService emailService;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    public void initialUserSetup(User user) {
        user.setRole(roleRepository.getById(1));
        user.setAccountKeyCreated(LocalDateTime.now());
        user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
        user.setAccountKey(keyGenerator.generateAccountKey());
    }

    public void adminSaveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByKey(String key) {
        return userRepository.findByAccountKey(key);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void update(User user) {
        userRepository.save(user);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Boolean isThisTheLastAdmin(User user) {
        if (user.getRole().getName().equals("ROLE_ADMIN")) {
            if (userRepository.countAllByRole(user.getRole()) == 1) {
                System.out.println("TEMPORARY: DELETING LAST ADMIN!!!");
                return true;
            }
        }
        return false;
    }

    public Boolean validateAccountKey(String key) {
        if (userRepository.findByAccountKey(key) != null) {
            LocalDateTime expirationDate = userRepository.findByAccountKey(key).getAccountKeyExpirationDate();
            if (LocalDateTime.now().compareTo(expirationDate) < 1) {
                return true;
            }
        }
        return false;
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public List<User> getUsersForAdmin(int pageNumber) {
        int numberOfPages = Math.round(countAllUsers()/10) + 1;
        if(pageNumber > numberOfPages || pageNumber <= 0) {
            return null;
        } else {
            Page<User> userPage = userRepository.findAll(PageRequest.of(pageNumber - 1, 10));
            return userPage.getContent();
        }
    }

    @Transactional
    public void emailPasswordResetLink(String email) {
        String key = keyGenerator.generateAccountKey();
        emailService.sendEmail(email,"SnippetApp - password reset confirmation", "You have 24h to reset your password at: http://localhost:8080/reset-password/" + key);
        User user = userRepository.findByEmail(email);
        user.setAccountKey(key);
    }

    public Boolean validatePasswordResetKey(String key) {
        if(userRepository.findByAccountKey(key) != null) {
            return true;
        }
        return false;
    }

    public Boolean emailIsRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void removeUserAccountKey(User user) {
        user.setAccountKey(null);
    }

}
