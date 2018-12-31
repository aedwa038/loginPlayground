package org.playground.login.playground.pojo;

import org.playground.login.playground.repository.pojo.RegisteredUser;

public class UserRegistrationResponse {
    private String message;
    private RegisteredUser user;


    public UserRegistrationResponse(String message) {
        this.message = message;
    }

    public UserRegistrationResponse(RegisteredUser registeredUser) {
        this.message = "Welcome " +  registeredUser.getUsername();
        this.user = registeredUser;
    }


    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
