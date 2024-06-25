package org.example.woowalearn.user.domain;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public record PhoneNumber(String phoneNumber) {
    private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("^\\d{2,4}-\\d{3,4}-\\d{4}$");

    public PhoneNumber {
        validate(phoneNumber);
    }

    private void validate(final String phoneNumber) {
        if (PHONE_NUMBER_REGEX.matcher(phoneNumber)
                .matches()) {
            return;
        }
        throw new IllegalArgumentException(String.format("%s는 이메일 형식이 아닙니다.", phoneNumber));
    }
}
