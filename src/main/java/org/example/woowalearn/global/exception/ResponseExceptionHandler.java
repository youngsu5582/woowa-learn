package org.example.woowalearn.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WoowaLearnException.class)
    public ResponseEntity<Object> reactionGameException(final WoowaLearnException e) {
        log.debug("Reaction Game exception [status={}]", e.getErrorCode());
        return createResponse(e.getErrorCode());
    }

    private ResponseEntity<Object> createResponse(final WoowaLearnErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
