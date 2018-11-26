package org.playground.login.playground.service;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UserSessionService {

    private Map<String, UserSession> userSessionMap = new HashMap<>();
    public static UserSessionService instance = null;

    private UserSessionService() {
    }

    public static UserSessionService getInstance() {
        if( instance == null) {
            instance = new UserSessionService();
        }
        return instance;
    }

    public String putSession(UserSession userSession) {
        String sessionID = UUID.randomUUID().toString();
        userSessionMap.put(sessionID, userSession);
        return sessionID;
    }

    public UserSession getSession(String sessionID) {
        return userSessionMap.get(sessionID);
    }
}
