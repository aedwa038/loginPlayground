package org.playground.login.playground;

import org.junit.Test;
import org.playground.login.playground.repository.DataService;
import org.playground.login.playground.repository.pojo.RegisteredUser;

public class DataServiceTest {


    @Test
    public void name() throws Exception {
        DataService<RegisteredUser> dataService = new DataService<>();
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setEmail("email");
        registeredUser.setUsername("akeem");
        registeredUser.setId(1);
        registeredUser.setPassword("pass");


    }
}
