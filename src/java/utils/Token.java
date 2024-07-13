/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.SecureRandom;
import java.util.Base64;


public class Token {
    
    public String generateRandomToken(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Token length must be greater than 0");
        }

        byte[] randomBytes = new byte[length];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);

        // Convert the byte array to a base64-encoded string
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
    
}
