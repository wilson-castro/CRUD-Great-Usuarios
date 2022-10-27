package io.github.wilsoncastro.backend.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFoundException(EntityNotFoundException enf, HttpServletRequest request) {
        StandartError err = new StandartError(
            Instant.now(),
            HttpStatus.NOT_FOUND.value(),
            "Resource not found",
                enf.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandartError> AlreadyExistsException(AlreadyExistsException ae, HttpServletRequest request) {
        StandartError err = new StandartError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Resource conflict",
                ae.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandartError> globalExceptionHandler(Exception ex, HttpServletRequest request) {
        StandartError err = new StandartError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
