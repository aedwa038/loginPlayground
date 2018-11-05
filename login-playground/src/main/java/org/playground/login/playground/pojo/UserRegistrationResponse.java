package org.playground.login.playground.pojo;

public class UserRegistrationResponse {
    private String userName;
    private String userId;
    private String message;


    public UserRegistrationResponse(String message) {
        this.message = message;
    }

    public UserRegistrationResponse(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
