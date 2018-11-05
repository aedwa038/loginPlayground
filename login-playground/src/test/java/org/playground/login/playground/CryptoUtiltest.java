package org.playground.login.playground;

import org.junit.Test;
import org.playground.login.playground.utils.CryptoUtil;

public class CryptoUtiltest {

    @Test
    public void hashPassword() {
        String password = "akeem";
        System.out.println(CryptoUtil.hash(password));
    }

    @Test
    public void checkpassword() {
        String password = "akeem";
        String hash = CryptoUtil.hash(password);
        System.out.println(CryptoUtil.checkPassword(password, hash));
    }
}
