package org.example.woowalearn.user.service;

import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private AuthService sut;

    @Test
    void 사용자_정보를_통해_회원가입을_진행한다(){
        final var request = new UserCreateRequest(
                "joyson5582@gmail.com",
                "password1234",
                true
        );
        final var response = sut.signup(request);

        assertThat(response.isMember()).isTrue();
    }
    @Test
    void 중복된_이메일로_회원가입시_예외를_발생한다(){
        final var request = new UserCreateRequest(
                "joyson5582@gmail.com",
                "password1234",
                true
        );
        sut.signup(request);

        assertThatThrownBy(()->sut.signup(request))
            .isInstanceOf(WoowaLearnException.class);
    }
}
