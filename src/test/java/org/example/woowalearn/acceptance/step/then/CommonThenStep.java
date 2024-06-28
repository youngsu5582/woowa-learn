package org.example.woowalearn.acceptance.step.then;

import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.service.EmailSender;
import org.mockito.Mockito;

public class CommonThenStep {
    public static void 생성_성공한지_검증(final ValidatableResponse response) {
        response.assertThat()
                .statusCode(201);
    }

    public static void 조회_성공한지_검증(final ValidatableResponse response) {
        response.assertThat()
                .statusCode(200);
    }

    public static void 잘못된_요청인지_검증(final ValidatableResponse response) {
        response.assertThat()
                .statusCode(400);
    }

    public static void 메일_발송한지_검증(final String email, final String contractName, final EmailSender emailSender,final String applyStatus) {
        Mockito.verify(emailSender,Mockito.times(1))
                .sendApplyJudgeMail(contractName, email,applyStatus);
    }
}
