package org.playground.login.playground.error;

public class FilterError extends RuntimeException {
    private int code;
    private String message;

    public FilterError(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public FilterError(int code, String message, Throwable t) {
        super(t);
        this.code = code;
        this.message = message;

    }

    public FilterError(Throwable t) {
        super(t);
        this.code = 500;
        this.message = t.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
