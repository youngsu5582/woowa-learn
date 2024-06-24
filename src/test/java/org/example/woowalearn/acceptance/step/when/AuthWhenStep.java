package org.example.woowalearn.acceptance.step.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.dto.UserCreateRequest;

public class AuthWhenStep {
    public static ValidatableResponse 회원가입(final UserCreateRequest userCreateRequest){
        return RestAssured.given().body(userCreateRequest).contentType(ContentType.JSON)
                .when().post("/auth/signup")
                .then();
    }
}
