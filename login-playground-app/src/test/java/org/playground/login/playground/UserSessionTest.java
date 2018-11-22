package org.playground.login.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.service.UserSession;

public class UserSessionTest {
    @Test
    public void name() throws Exception {
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUsername("akeem");
        registeredUser.setPassword("akeem");
        UserSession userSession = UserSession.create(registeredUser);
        String json = UserSession.encode(userSession);
        System.out.println(json);
        UserSession user = UserSession.decode(json);
        System.out.println(user.getRegisteredUser().getUsername());

    }
}
