package com.up.upfolio.services.auth.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class StdOtpCodeGenerator implements OtpCodeGenerator {
    private final SecureRandom secureRandom;

    public String generateCode() {
        byte[] bytes = new byte[4];
        secureRandom.nextBytes(bytes);

        return String.valueOf((bytes[0] & 0xff) % 10) +
                ((bytes[1] & 0xff) % 10) +
                ((bytes[2] & 0xff) % 10) +
                ((bytes[3] & 0xff) % 10);
    }
}
