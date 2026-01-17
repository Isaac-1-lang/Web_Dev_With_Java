package com.helloworld.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EmailValidator {
    private static final String EMAIL_REGEX="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);


    /**
     * Validate the given email
     * @param email the email string to validate
     * @param true if email is valid , false if invalid
     */

    public static boolean isValid(String email) {
        if(email==null || email.isEmpty()) return false;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
