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
}
