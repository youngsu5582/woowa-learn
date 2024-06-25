package org.example.woowalearn.user.service;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PasswordMatcher {
    PasswordEncoder passwordEncoder;
    public boolean matches(final String rawPassword, final Password encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword.password());
    }
}
