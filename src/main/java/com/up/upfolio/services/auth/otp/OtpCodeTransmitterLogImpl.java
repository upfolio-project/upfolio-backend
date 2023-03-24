package com.up.upfolio.services.auth.otp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OtpCodeTransmitterLogImpl implements OtpCodeTransmitter {
    @Override
    public void sendCode(String phoneNumber, String code) {
        log.info("sending OTP code {} to {}", code, phoneNumber);
    }

    @Override
    public void makeCall(String phoneNumber, String code) {
        log.info("making phone call with {} to {}", code, phoneNumber);
    }
}
