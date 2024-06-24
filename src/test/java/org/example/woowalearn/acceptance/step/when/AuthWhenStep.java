package org.example.woowalearn.acceptance.step.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserLoginRequest;

public class AuthWhenStep {
    public static ValidatableResponse 회원가입(final UserCreateRequest userCreateRequest) {
        //@formatter:off
        return RestAssured.given().body(userCreateRequest).contentType(ContentType.JSON)
                .when().post("/auth/signup")
                .then();
        //@formatter:on
    }

    public static ValidatableResponse 로그인(final String email, final String password) {
        //@formatter:off
        return RestAssured.given().body(new UserLoginRequest(email,password)).contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then();
        //@formatter:on
    }
}
