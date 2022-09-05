package top.wsure.auth.utils;

import top.wsure.auth.entity.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
    FileName:   SecurityUtil
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class SecurityUtil {

    public static String encryptUserPassword(User user){
        return md5(user.getUserName() + md5(user.getPassword()));
    }

    public static String createToken(User user){
        return md5(user.getUserName() + md5(String.valueOf(System.currentTimeMillis())));
    }

    public static String md5(String string){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(string.getBytes());
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }

}
