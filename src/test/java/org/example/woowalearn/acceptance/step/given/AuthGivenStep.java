package org.example.woowalearn.acceptance.step.given;

import org.example.woowalearn.user.dto.UserCreateRequest;

public class AuthGivenStep {
    public static UserCreateRequest 회원가입_정보_생성(final String email, final String password, final boolean isEmailAccepted) {
        return new UserCreateRequest(email,password,isEmailAccepted);
    }
}
