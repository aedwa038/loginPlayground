package org.playground.login.playground.error;

public class ApplicatonError extends Exception {

    private String errorString;
    private ErrorCode errorCode = ErrorCode.UNKNOWN;

    @Deprecated
    public ApplicatonError (Exception ex) {
        super(ex);
        this.errorString = ex.getLocalizedMessage();
    }

    public ApplicatonError (ErrorCode errorCode, Exception ex) {
        super(ex);
        this.errorString = ex.getLocalizedMessage();
        this.errorCode = errorCode;
    }
    @Deprecated
    public ApplicatonError(String message, String errorString) {
        super(message);
        this.errorString = errorString;
    }

    public ApplicatonError(ErrorCode errorCode, String message, String errorString) {
        super(message);
        this.errorString = errorString;
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public enum ErrorCode {
        REGISRATION_ERROR,
        INTERNAL,
        ILLEGAL_ARGUMENT,
        BACKEND_ERROR,
        LOGIN_ERROR,
        AUTHENTICATION_ERROR,
        AUTHORIZATION_ERROR,
        UNKNOWN
    }

    @Override
    public String toString() {
        return "ApplicatonError{" +
                "errorString='" + errorString + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
