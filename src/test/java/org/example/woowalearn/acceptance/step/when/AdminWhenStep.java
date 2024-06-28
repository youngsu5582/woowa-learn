package org.example.woowalearn.acceptance.step.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.dto.ApplyChangeRequest;

public class AdminWhenStep {
    public static ValidatableResponse 지원서_승인(final String accessToken, final ApplyChangeRequest request) {
        //@formatter:off
        return RestAssured.given().body(request).contentType(ContentType.JSON)
                .header(new Header("Authorization","Bearer " + accessToken))
                .when().post("/admin/apply/approve")
                .then();
        //@formatter:on
    }

    public static ValidatableResponse 지원서_거절(final String accessToken, final ApplyChangeRequest request) {
        //@formatter:off
        return RestAssured.given().body(request).contentType(ContentType.JSON)
                .header(new Header("Authorization","Bearer " + accessToken))
                .when().post("/admin/apply/deny")
                .then();
        //@formatter:on
    }
}
