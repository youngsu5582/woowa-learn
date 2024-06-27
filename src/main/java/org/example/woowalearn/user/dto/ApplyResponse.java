package org.example.woowalearn.user.dto;

public record ApplyResponse(
        long applyFormId,
        String applyReason,
        String phoneNumber,
        String explainLink,
        String contractName,
        String contactEmail,
        String applyStatus

) {
}
