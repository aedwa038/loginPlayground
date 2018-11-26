package org.playground.login.playground;

import org.junit.Test;
import org.playground.login.playground.repository.DataService;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.pojo.Stuff;

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

    @Test
    public void primaryKey() {
        DataService<Stuff> dataService = new DataService<>();
        Stuff stuff = new Stuff();
        stuff.setData("data");
        stuff.setId(0);
        stuff.setUser_id(0);

        System.out.println(dataService.findPrimaryKey(stuff, Stuff.class));
    }
}
