package org.example.woowalearn.acceptance.step.then;

import io.restassured.response.ValidatableResponse;

public class CommonThenStep {
    public static void 생성_성공한지_검증(final ValidatableResponse response) {
        response.assertThat()
                .statusCode(201);
    }
    public static void 잘못된_요청인지_검증(final ValidatableResponse response){
        response.assertThat().statusCode(400);
    }
}
