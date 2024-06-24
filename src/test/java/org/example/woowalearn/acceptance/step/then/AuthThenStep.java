package org.example.woowalearn.acceptance.step.then;

import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.dto.TokenResponse;
import org.example.woowalearn.user.dto.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthThenStep {
    public static void 회원가입_성공한지_검증(final ValidatableResponse validatableResponse){
        final var userResponse = validatableResponse.extract().as(UserResponse.class);
        assertThat(userResponse.id()).isPositive();
        assertThat(userResponse.isMember()).isTrue();
    }
    public static void 로그인_성공한지_검증(final ValidatableResponse validatableResponse){
        validatableResponse.extract().as(TokenResponse.class);
    }
}
