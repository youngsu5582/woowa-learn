package org.example.woowalearn.user.dto;

public record ApplyTeacherRequest(
    String applyReason,
    String phoneNumber,
    String explainLink,
    String contractName,
    String contactEmail
) {
}
