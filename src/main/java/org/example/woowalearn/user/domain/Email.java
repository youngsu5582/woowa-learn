package org.example.woowalearn.user.domain;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record Email(String address) {
    private static final Pattern EMAIL_REGEX = Pattern.compile( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public Email {
        validate(address);
    }

    private void validate(final String address) {
        if (EMAIL_REGEX.matcher(address).matches()) {
            return;
        }
        throw new IllegalArgumentException(String.format("%s는 이메일 형식이 아닙니다.",address));
    }
}
