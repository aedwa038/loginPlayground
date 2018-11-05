package org.playground.login.playground;

public class ApplicatonError extends Exception {
    private String errorString;

    public ApplicatonError (Exception ex) {
        super(ex);
        this.errorString = ex.getLocalizedMessage();
    }
    public ApplicatonError(String message, String errorString) {
        super(message);
        this.errorString = errorString;
    }

    public String getErrorString() {

        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
