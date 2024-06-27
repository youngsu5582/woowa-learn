package org.example.woowalearn.acceptance.step.given;

import org.example.woowalearn.user.dto.ApplyTeacherRequest;

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
}
