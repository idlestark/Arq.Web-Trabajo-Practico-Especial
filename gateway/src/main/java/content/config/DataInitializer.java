package content.config;

import content.entities.Authority;
import content.entities.User;
import content.repository.AuthorityRepository;
import content.repository.UserRepository;
import content.security.AuthorityConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        Authority adminRole = authorityRepository.findById(AuthorityConstant._ADMIN)
                .orElseGet(() -> authorityRepository.save(new Authority(AuthorityConstant._ADMIN)));

        Authority userRole = authorityRepository.findById(AuthorityConstant._USER)
                .orElseGet(() -> authorityRepository.save(new Authority(AuthorityConstant._USER)));

        if (userRepository.findOneWithAuthoritiesByUsernameIgnoreCase("admin").isEmpty()) {
            User admin = new User("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setAuthorities(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }

        if (userRepository.findOneWithAuthoritiesByUsernameIgnoreCase("user").isEmpty()) {
            User user = new User("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setAuthorities(Set.of(userRole));
            userRepository.save(user);
        }
    }
}
