package org.example.woowalearn.user.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {
    @Test
    void 문자열을_통해_생성한다() {
        assertThatCode(() -> new Email("info@example.com"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"i893.naver.com", "joyson5582"})
    void 이메일_형식이_아니면_예외를_발생한다(final String value) {
        assertThatThrownBy(() -> new Email(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
