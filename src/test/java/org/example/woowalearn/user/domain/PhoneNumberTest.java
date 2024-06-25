package org.example.woowalearn.user.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PhoneNumberTest {
    @ParameterizedTest(name = "{0}은 생성 가능하다.")
    @ValueSource(strings = {"010-1234-5678","053-218-2025"})
    void 문자열을_통해_생성한다(final String value){
        assertThatCode(() -> new PhoneNumber(value))
                .doesNotThrowAnyException();
    }
    @ParameterizedTest(name = "{0}은 정해진 형식이 아니다.")
    @ValueSource(strings = {"010.1234.5678","01012345678","0-1012345678","010-123-456"})
    void 정해진_형식이_아니면_예외를_발생한다(final String value){
        assertThatThrownBy(() -> new PhoneNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
