package org.example.woowalearn.acceptance.step.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;

public class UserWhenStep {
    public static ValidatableResponse 교육자_신청(final String token,final ApplyTeacherRequest request) {

        //@formatter:off
        return RestAssured.given().body(request).contentType(ContentType.JSON)
                .header(new Header("Authorization","Bearer " + token))
                .when().post("/user/apply")
                .then();
        //@formatter:on
    }
}
