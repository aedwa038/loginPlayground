package org.playground.login.playground;

import org.junit.Test;
import org.playground.login.playground.utils.CryptoUtil;

public class CryptoUtiltest {

    @Test
    public void hashPassword() {
        String password = "password";
        System.out.println(CryptoUtil.hash(password));
    }

    @Test
    public void checkpassword() {
        String password = "password";
        String hash = CryptoUtil.hash(password);
        System.out.println(hash);
        System.out.println(CryptoUtil.checkPassword(password, "$2a$12$6SnB66J6C9L2O9dk3YeFde9z/ReAgdutkzk/Up.d0oUcS6ncPBbPy"));
    }
}
