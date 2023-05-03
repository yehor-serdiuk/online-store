package ua.volcaniccupcake.onlinestore.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.security.Authority;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.security.AuthorityRepository;
import ua.volcaniccupcake.onlinestore.repository.security.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    Authority userRole = Authority.builder()
            .role("ROLE_USER")
            .build();
    Authority adminRole = Authority.builder()
            .role("ROLE_ADMIN")
            .build();
    User user = User.builder()
            .username("user")
            .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-user"))
            .authority(userRole)
            .build();
    User admin = User.builder()
            .username("admin")
            .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-admin"))
            .authority(adminRole)
            .build();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveAuthorities();
        saveUsers();
    }

    private void saveUsers() {
        userRepository.save(user);
        userRepository.save(admin);
    }
    private void saveAuthorities() {
        authorityRepository.save(userRole);
        authorityRepository.save(adminRole);
    }
}
