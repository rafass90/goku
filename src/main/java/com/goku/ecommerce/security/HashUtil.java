package com.goku.ecommerce.security;

import com.goku.ecommerce.domain.exception.EncryptPasswordException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class HashUtil {

    private static String algorithm = "MD5";

    @SneakyThrows
    public static String encryptPassword(String password) {
        BigInteger bi = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update((password + "1y124Dd").getBytes("UTF-8"));//Salt 1y124Dd

            bi = new BigInteger(1, md.digest());

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new EncryptPasswordException();
        }
        return String.format("%1$032X", bi);
    }
}
