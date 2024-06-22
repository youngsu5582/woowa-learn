package org.example.woowalearn.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {
    @Test
    void 문자열을_통해_생성한다() {
        assertThatCode(() -> new Password("password1234"))
                .doesNotThrowAnyException();
    }

    @Test
    void 여덟_글자_이하이면_예외를_발생한다() {
        assertThatThrownBy(() -> new Password("password"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
