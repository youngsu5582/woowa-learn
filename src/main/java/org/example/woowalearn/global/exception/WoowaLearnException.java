package org.example.woowalearn.global.exception;

public class WoowaLearnException extends RuntimeException{
    private final WoowaLearnErrorCode errorCode;
    public WoowaLearnException(final WoowaLearnErrorCode errorCode) {
        this(errorCode,null);
    }

    public WoowaLearnException(final WoowaLearnErrorCode errorCode,final Throwable cause) {
        super(errorCode.name(), cause);
        this.errorCode = errorCode;
    }

    public WoowaLearnErrorCode getErrorCode() {
        return errorCode;
    }
}
