package org.example.woowalearn.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler  {

    @ExceptionHandler(WoowaLearnException.class)
    public ResponseEntity<ErrorResponse> handleWoowaLearnException(final WoowaLearnException e) {
        log.debug("Reaction Game exception [statusCode = {}, errorMessage = {}, cause = {}]", e.getHttpStatus(), e.getErrorMessage(),e.getCause());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.debug("Server exception [errorMessage = {}, cause = {}]", e.getMessage(),e.getCause());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }
}
