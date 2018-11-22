package org.playground.login.playground.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.springframework.util.Base64Utils;

import java.util.Objects;

public class UserSession {

    private RegisteredUser registeredUser;


    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public static UserSession create(RegisteredUser registeredUser) {
        UserSession userSession = new UserSession();
        userSession.setRegisteredUser(registeredUser);
        return userSession;
    }

    public static String encode(UserSession userSession) throws ApplicatonError {
        ObjectMapper mapper = new ObjectMapper();
        String sessionString = "";
        try {
            sessionString = mapper.writeValueAsString(userSession);
        } catch (Exception ex) {
            throw new ApplicatonError("","");
        }
        return Base64Utils.encodeToString(sessionString.getBytes());
    }

    public static UserSession decode (String string) throws ApplicatonError {
        String decoded = new String(Base64Utils.decodeFromString(string));
        ObjectMapper objectMapper = new ObjectMapper();
        UserSession userSession = null;
        try {
            userSession = objectMapper.readValue(decoded, UserSession.class);
        } catch (Exception ex) {
            throw new ApplicatonError("","");
        }
        return  userSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(registeredUser, that.registeredUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(registeredUser);
    }
}
