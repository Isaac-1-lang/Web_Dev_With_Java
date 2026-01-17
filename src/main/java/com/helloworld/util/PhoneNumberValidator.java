package com.helloworld.util;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class PhoneNumberValidator {
    private String PHONE_NUMBER_VALIDATOR="[0-9]{10}";


    public boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_VALIDATOR);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
