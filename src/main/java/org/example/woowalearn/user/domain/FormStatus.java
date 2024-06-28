package org.example.woowalearn.user.domain;

public enum FormStatus {
    PENDING("WAIT"),
    DENY("DENY"),
    APPROVE("APPROVE"),
    COMPLETE("COMPLETE");

    private String value;

    FormStatus(final String value) {
        this.value = value;
    }

    public static FormStatus from(final String value) {
        for (final FormStatus formStatus : FormStatus.values()) {
            if (formStatus.value.equals(value)) {
                return formStatus;
            }
        }
        throw new IllegalArgumentException(String.format("%s는 없는 값입니다.",value));
    }
}
