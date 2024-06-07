package org.example.woowalearn.global.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
