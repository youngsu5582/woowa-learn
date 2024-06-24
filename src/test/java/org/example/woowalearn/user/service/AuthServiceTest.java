package org.example.woowalearn.user.service;

import org.example.woowalearn.ServiceTest;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserLoginRequest;
import org.example.woowalearn.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ServiceTest
class AuthServiceTest {
    @Autowired
    private AuthService sut;

    final String email = "joyson5582@gmail.com";
    final String password = "password1234";

    @Test
    void 사용자_정보를_통해_회원가입을_한다(){
        final var request = new UserCreateRequest(
                email,
                password,
                true
        );
        final var response = sut.signup(request);

        assertThat(response.isMember()).isTrue();
    }
    @Test
    void 중복된_이메일로_회원가입시_예외를_발생한다(){
        final var request = new UserCreateRequest(
                email,
                password,
                true
        );
        sut.signup(request);

        assertThatThrownBy(()->sut.signup(request))
            .isInstanceOf(WoowaLearnException.class);
    }
    @Test
    void 사용자_정보를_통해_로그인을_해서_토큰들을_생성한다(){
        createUser(email,password);

        final var request = new UserLoginRequest(
                email,
                password
        );
        final var response = sut.login(request);

        assertThat(response.accessToken()).contains("ey");
        assertThat(response.refreshToken()).contains("ey");
    }
    @Test
    void 없는_이메일로_로그인시_예외를_발생한다(){
        final var request = new UserLoginRequest(
                "notExist@email.com",
                password
        );
        assertThatThrownBy(()->sut.login(request))
                .isInstanceOf(WoowaLearnException.class);
    }
    @Test
    void 틀린_비밀번호로_로그인시_예외를_발생한다(){
        createUser(email,password);
        final var request = new UserLoginRequest(
                email,
                "notMatchedPassword"
        );
        assertThatThrownBy(()->sut.login(request))
                .isInstanceOf(WoowaLearnException.class);
    }
    private UserResponse createUser(final String email, final String password){
        final var request = new UserCreateRequest(
                email,
                password,
                true
        );
        return sut.signup(request);
    }
}
