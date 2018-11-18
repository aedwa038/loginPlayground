package org.playground.login.playground.pojo;

import org.playground.login.playground.repository.pojo.RegisteredUser;

public class HealthResponse {

    private String status = "OK";
    private RegisteredUser testUser;

    public HealthResponse(RegisteredUser testUser) {
        this.testUser = testUser;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegisteredUser getTestUser() {
        return testUser;
    }

    public void setTestUser(RegisteredUser testUser) {
        this.testUser = testUser;
    }

    public HealthResponse() {
    }

    public HealthResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
