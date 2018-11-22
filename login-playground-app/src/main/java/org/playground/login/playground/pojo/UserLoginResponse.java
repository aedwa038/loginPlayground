package org.playground.login.playground.pojo;

import org.playground.login.playground.repository.pojo.RegisteredUser;

public class UserLoginResponse {
    private String message;
    private RegisteredUser user;


    public UserLoginResponse(RegisteredUser user) {
        this.message = "Welcome back " + user.getUsername();
        this.user = user;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public UserLoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
