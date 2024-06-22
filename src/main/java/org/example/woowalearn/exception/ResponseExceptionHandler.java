package org.example.woowalearn.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WoowaLearnException.class)
    public ResponseEntity<ErrorResponse> handleWoowaLearnException(final WoowaLearnException e) {
        log.debug("Reaction Game exception [statusCode = {}, errorMessage = {}]", e.getHttpStatus(), e.getErrorMessage());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage()));
    }
}
