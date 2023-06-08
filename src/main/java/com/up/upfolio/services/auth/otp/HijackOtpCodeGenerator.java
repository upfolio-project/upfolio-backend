package com.up.upfolio.services.auth.otp;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Primary
@ConditionalOnProperty(value = "otp.generator.hijack", havingValue = "true")
@Slf4j
public class HijackOtpCodeGenerator implements OtpCodeGenerator {
    private final TOTPGenerator totp;

    public HijackOtpCodeGenerator(@Value("${otp.generator.secret}") String secret) {
        log.warn("otp code generator runs in a hijacked mode");

        this.totp = new TOTPGenerator.Builder(secret.getBytes())
                .withHOTPGenerator(builder -> {
                    builder.withPasswordLength(6);
                    builder.withAlgorithm(HMACAlgorithm.SHA1);
                })
                .withPeriod(Duration.ofSeconds(30))
                .build();
    }


    @Override
    public synchronized String generateCode() {
        String code;

        if (totp.durationUntilNextTimeWindow().getSeconds() < 10) {
            code = totp.at(totp.getClock().instant().plusSeconds(11));
        } else {
            code = totp.now();
        }

        return code.substring(2);
    }
}
