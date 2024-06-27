package org.example.woowalearn.acceptance.step.given;

import org.example.woowalearn.user.dto.TokenResponse;
import org.example.woowalearn.user.dto.UserCreateRequest;

import static org.example.woowalearn.acceptance.step.when.AuthWhenStep.로그인;
import static org.example.woowalearn.acceptance.step.when.AuthWhenStep.회원가입;

public class AuthGivenStep {
    public static UserCreateRequest 회원가입_정보_생성(final String email, final String password, final boolean isEmailAccepted) {
        return new UserCreateRequest(email,password,isEmailAccepted);
    }
    public static TokenResponse 회원가입후_토큰_반환(final String email,final String password){
        회원가입(new UserCreateRequest(email,password,true));
        return 로그인(email,password).extract().as(TokenResponse.class);
    }
}
