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
        LOGGER.error("Error occured: " + ex.getErrorString());
        LOGGER.error("Error: ",ex);
        ErrorResponse errorResponse = new ErrorResponse();
        int code = 500;
        switch (ex.getErrorCode()) {
            case ILLEGAL_ARGUMENT:
                errorResponse.setErrorCode(400.01f);
                code = 400;
                break;
            case UNKNOWN:
                errorResponse.setErrorCode(400.02f);
                code = 400;
                break;
            case BACKEND_ERROR:
                errorResponse.setErrorCode(503.03f);
                code = 503;
                break;
            case INTERNAL:
                errorResponse.setErrorCode(500.04f);
                code = 500;
                break;
            case LOGIN_ERROR:
                errorResponse.setErrorCode(404.05f);
                code = 404;
                break;
            default:
                errorResponse.setErrorCode(500.00f);
                code = 500;
        }
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(code).body(errorResponse);
    }


    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleException(
            Throwable ex) {
        LOGGER.warn("Error occured: " + ex.getMessage());
        LOGGER.error("Error: ",ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(500.00f);
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }


}
