package org.example.woowalearn.user.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void 사용자의_비밀번호와_일치하면_참을_반환한다() {
        final User user = new User("joyson5582@gmail.com", "password1234", true);
        final var result = user.isEqualPassword(null, "password1234");

        assertTrue(result);
    }
    @Test
    void 사용자의_암호화된_비밀번호와_일치하면_참을_반환한다(){
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        final User user = new User("joyson5582@gmail.com", encoder.encode("password1234"), true);

        final var result = user.isEqualPassword(encoder, "password1234");

        assertTrue(result);
    }
}
