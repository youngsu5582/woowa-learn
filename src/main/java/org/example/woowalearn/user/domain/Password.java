package org.example.woowalearn.user.domain;

import jakarta.persistence.Embeddable;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
public record Password(String password) {
    private static final int MIN_LENGTH = 8;
    private static final IllegalArgumentException MIN_LENGTH_EXCEPTION =
            new IllegalArgumentException(String.format("%d보다 더 길게 입력해야합니다.",MIN_LENGTH));

    public boolean matchPassword(final PasswordEncoder encoder, final String password) {
        return encoder.matches(password,this.password);
    }
    public Password {
        validate(password);
    }
    private void validate(final String password){
        if(password.length() <= MIN_LENGTH){
            throw MIN_LENGTH_EXCEPTION;
        }
    }
}
