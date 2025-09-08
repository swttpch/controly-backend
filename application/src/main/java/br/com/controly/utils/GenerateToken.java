package br.com.controly.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GenerateToken {

    public static String generate(){
        SecureRandom secureRandom = new SecureRandom();

        // Generate 48 cryptographically secure random bytes
        byte[] randomBytes = new byte[48];
        secureRandom.nextBytes(randomBytes);

        // Convert the byte array to a hexadecimal string
        // BigInteger constructor takes a signum and a byte array
        // The 1 indicates a positive number
        String hexString = new BigInteger(1, randomBytes).toString(16);

        // Ensure leading zeros are present if the hex string is shorter than expected
        // A 48-byte array will result in a hex string of 96 characters (48 * 2)
        while (hexString.length() < 96) {
            hexString = "0" + hexString;
        }

        return hexString;
    }
}
