package org.example.woowalearn.acceptance.step.then;

import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.dto.ApplyResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class UserThenStep {
    public static void 지원_결과_특정_상태인지_검증(final ValidatableResponse response, final FormStatus status){
        final var result = response.extract().as(ApplyResponse.class);
        assertThat(result.applyStatus()).isEqualTo(status.name());
    }
}
