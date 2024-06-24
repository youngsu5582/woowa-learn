package org.example.woowalearn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WoowaLearnException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public WoowaLearnException(final HttpStatus httpStatusCode, final String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatusCode;
    }

    public WoowaLearnException(final HttpStatus httpStatusCode, final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatusCode;
    }
}
