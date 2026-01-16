package com.helloworld.util;

import java.util.Random;

/**
 * Utility class to generate verification codes
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class VerificationCodeGenerator {
    
    private static final Random random = new Random();
    
    /**
     * Generate a 6-digit verification code
     * @return 6-digit code as String (e.g., "123456")
     */
    public static String generateCode() {
        // Generate a random 6-digit number (100000 to 999999)
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
