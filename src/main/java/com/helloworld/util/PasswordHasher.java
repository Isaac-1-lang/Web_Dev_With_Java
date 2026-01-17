package com.helloworld.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Utility class to hash passwords
 * @author Isaac-1-lang
 * @version 1.0
 */
public class PasswordHasher {
    /**
     * Hash a password using SHA-256
     * @param password the password to hash
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found");
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}