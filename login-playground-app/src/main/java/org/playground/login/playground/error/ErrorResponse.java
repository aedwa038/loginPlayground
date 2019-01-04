package org.playground.login.playground.error;

public class ErrorResponse {
    private float errorCode;
    private String message;

    public float getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(float errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
