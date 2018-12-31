package org.playground.login.playground.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ApplicatonError.class)
    protected ResponseEntity<Object> handleApplicatonError(
            ApplicatonError ex) {

        switch (ex.getErrorCode()) {
            case ILLEGAL_ARGUMENT:
            case UNKNOWN:
            case BACKEND_ERROR:
            case INTERNAL:
            case LOGIN_ERROR:
                LOGGER.error("Error occured: " + ex.getErrorString());
                LOGGER.error("Error: ",ex);
        }

        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleException(
            Throwable ex) {
        LOGGER.warn("Error occured: " + ex.getMessage());

        return ResponseEntity.ok().build();
    }


}
