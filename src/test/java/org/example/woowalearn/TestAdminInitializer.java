package org.example.woowalearn;

import groovy.util.logging.Slf4j;
import org.example.woowalearn.user.domain.Role;
import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestAdminInitializer implements ApplicationRunner {

    public static final User ADMIN = new User("testAdmin@gmail.com", "testpassword1234", Role.ADMIN,false );
    private static final Logger log = LoggerFactory.getLogger(TestAdminInitializer.class);

    private final UserRepository userRepository;

    TestAdminInitializer(final UserRepository playerRepository) {
        this.userRepository = playerRepository;
    }

    @Override
    public void run(final ApplicationArguments args) {
        if (userRepository.count() > 0) {
            return;
        }

        userRepository.save(ADMIN);
        log.warn("Admin initialized [player={}]", ADMIN);
    }
}
