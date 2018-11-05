package org.playground.login.playground.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtil {

    private static int workload = 12;

    private CryptoUtil ()  {

    }
    public static  String hash (String password)  {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
