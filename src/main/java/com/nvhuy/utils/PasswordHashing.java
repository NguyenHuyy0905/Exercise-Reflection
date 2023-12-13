package com.nvhuy.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public static String hashingPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }
    public static boolean checkPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }
}
