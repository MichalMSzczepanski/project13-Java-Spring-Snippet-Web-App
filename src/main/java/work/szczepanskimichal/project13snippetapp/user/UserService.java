package work.szczepanskimichal.project13snippetapp.user;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;
import work.szczepanskimichal.project13snippetapp.role.Role;
import work.szczepanskimichal.project13snippetapp.role.RoleRepository;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.utils.KeyGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final KeyGenerator keyGenerator;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByUserId(Long id) { return userRepository.findById(id).orElse(null); }

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
                return true;
            }
        }
        return false;
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User convertCreateUserDTOToUser (CreateUserDTO createUserDTO) {
        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEnabled(0);
        user.setApiKey(keyGenerator.generateApiKey());
        user.setAccountKeyValidation(keyGenerator.generateAccountKey());
        user.setAccountKeCreated(LocalDateTime.now());
        user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
        System.out.println("flag convert user dto to user: " + user);
        return user;
    }

}
