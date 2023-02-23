package com.upfolio.upfolio.services;

import com.upfolio.upfolio.exceptions.GenericInternalException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class OTPCodeGenerator {
    public String generateCode() {
        byte[] bytes = new byte[4];

        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new GenericInternalException(e.getMessage());
        }

        return String.valueOf((bytes[0] & 0xff) % 10) +
                ((bytes[1] & 0xff) % 10) +
                ((bytes[2] & 0xff) % 10) +
                ((bytes[3] & 0xff) % 10);
    }
}
