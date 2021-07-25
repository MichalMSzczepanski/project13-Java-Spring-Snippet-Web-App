package work.szczepanskimichal.project13snippetapp.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.szczepanskimichal.project13snippetapp.role.RoleRepository;
import work.szczepanskimichal.project13snippetapp.user.DTO.AdminUpdateUserDTO;
import work.szczepanskimichal.project13snippetapp.user.DTO.CreateUserDTO;
import work.szczepanskimichal.project13snippetapp.utils.KeyGenerator;

import java.time.LocalDateTime;
import java.util.List;

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

    public void adminSaveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByUserId(Long id) { return userRepository.findById(id).orElse(null); }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByKey(String key) { return userRepository.findByAccountKey(key); }

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

    public Boolean validateAccountKey(String key) {
        if (userRepository.findByAccountKey(key) != null) {
            LocalDateTime expirationDate = userRepository.findByAccountKey(key).getAccountKeyExpirationDate();
            if(LocalDateTime.now().compareTo(expirationDate) < 1) {
                return true;
            }
        }
        return false;
    }

    public User convertCreateUserDTOToUser (CreateUserDTO createUserDTO) {
        User user = new User();
        user.setId(createUserDTO.getId()); // added
        user.setEmail(createUserDTO.getEmail());
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEnabled(0);
        user.setRole(createUserDTO.getRole()); // added
        user.setApiKey(keyGenerator.generateApiKey());
        user.setAccountKey(keyGenerator.generateAccountKey());
        user.setAccountKeyCreated(LocalDateTime.now());
        user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
        return user;
    }

    public User convertAdminUpdateUserDTOToUser (AdminUpdateUserDTO adminUpdateUserDTO) {
        User user = new User();
        user.setId(adminUpdateUserDTO.getId()); // added
        user.setEmail(adminUpdateUserDTO.getEmail());
        user.setUsername(adminUpdateUserDTO.getUsername());
        user.setPassword(adminUpdateUserDTO.getPassword());
        user.setEnabled(0);
        user.setRole(adminUpdateUserDTO.getRole()); // added
        user.setApiKey(keyGenerator.generateApiKey());
        user.setAccountKey(keyGenerator.generateAccountKey());
        user.setAccountKeyCreated(LocalDateTime.now());
        user.setAccountKeyExpirationDate(LocalDateTime.now().plusDays(1));
        return user;
    }

    public User adminConvertCreateUserDTOToUser (CreateUserDTO createUserDTO) {
        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEnabled(1);
        user.setRole(createUserDTO.getRole());
        user.setApiKey(createUserDTO.getApiKey());
        user.setAccountKey(null);
//        user.setAccountKeCreated(null);
        user.setAccountKeyExpirationDate(null);
        return user;
    }

    public CreateUserDTO adminConvertUserToCreateUserDTO (User user) {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setId(user.getId());
        createUserDTO.setEmail(user.getEmail());
        createUserDTO.setUsername(user.getUsername());
        createUserDTO.setPassword(user.getPassword());
        createUserDTO.setEnabled(user.getEnabled());
        createUserDTO.setRole(user.getRole());
        createUserDTO.setApiKey(user.getApiKey());
        createUserDTO.setAccountKey(user.getAccountKey());
        createUserDTO.setAccountKeyCreated(user.getAccountKeyCreated());
        createUserDTO.setAccountKeyExpirationDate(user.getAccountKeyExpirationDate());
        return createUserDTO;
    }

    public AdminUpdateUserDTO adminConvertUserToAdminUpdateUserDTO (User user) {
        AdminUpdateUserDTO adminUpdateUserDTO = new AdminUpdateUserDTO();
        adminUpdateUserDTO.setId(user.getId());
        adminUpdateUserDTO.setEmail(user.getEmail());
        adminUpdateUserDTO.setUsername(user.getUsername());
        adminUpdateUserDTO.setPassword(user.getPassword());
        adminUpdateUserDTO.setEnabled(user.getEnabled());
        adminUpdateUserDTO.setRole(user.getRole());
        adminUpdateUserDTO.setApiKey(user.getApiKey());
        adminUpdateUserDTO.setAccountKey(user.getAccountKey());
        adminUpdateUserDTO.setAccountKeyCreated(user.getAccountKeyCreated());
        adminUpdateUserDTO.setAccountKeyExpirationDate(user.getAccountKeyExpirationDate());
        return adminUpdateUserDTO;
    }

}
