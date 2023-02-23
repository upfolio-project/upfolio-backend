package com.up.upfolio.services.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationState {
    public enum Step {
        WAIT_FOR_PHONE_NUMBER,
        WAIT_FOR_OTP_CODE,
        WAIT_FOR_FINISH
    }

    private Step step;
    private String otpCode;

    public RegistrationState() {
        step = Step.WAIT_FOR_PHONE_NUMBER;
    }
}
