package com.up.upfolio.services.auth.otp;

public interface OtpCodeTransmitter {
    void sendCode(String phoneNumber, String code);
}
