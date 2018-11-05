package org.playground.login.playground.pojo;

public class HealthResponse {

    private String status = "OK";

    public HealthResponse() {
    }

    public HealthResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
