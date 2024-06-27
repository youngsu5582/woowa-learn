package org.example.woowalearn.acceptance.step.given;

import org.example.woowalearn.user.dto.ApplyResponse;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;

import static org.example.woowalearn.acceptance.step.when.UserWhenStep.본인_지원서_조회;

public class UserGivenStep {
    public static ApplyTeacherRequest 교육자_지원서_정보_성성(final String applyReason, final String phoneNumber, final String explainLink, final String contractName, final String contactEmail) {
        return new ApplyTeacherRequest(
                applyReason,
                phoneNumber,
                explainLink,
                contractName,
                contactEmail
        );
    }

    public static ApplyResponse 본인_지원서_결과_조회(final String accessToken) {
        return 본인_지원서_조회(accessToken).extract()
                .as(ApplyResponse.class);
    }
}
