package org.example.woowalearn.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum WoowaLearnErrorCode {
    UNEXPECTED_SERVER_ERROR("SERVER-001","서버 관리자에게 문의하세요.", HttpStatus.INTERNAL_SERVER_ERROR);
    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

    WoowaLearnErrorCode(final String code, final String message, final HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}
