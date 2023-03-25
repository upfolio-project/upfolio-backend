package com.up.upfolio.services.auth;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomUtils;

@Getter
@Setter
public class RegistrationState {
    public enum Step {
        WAIT_FOR_PHONE_NUMBER,
        WAIT_FOR_OTP_CODE,
        WAIT_FOR_FINISH
    }

    public enum PhoneNumberConfirmationMethod {
        SMS,
        CALL
    }

    private Step step;
    private String otpCode;
    private int otpAttemptCounter;
    private PhoneNumberConfirmationMethod confirmationMethod;
    private String phoneNumber;

    public RegistrationState() {
        step = Step.WAIT_FOR_PHONE_NUMBER;
        confirmationMethod = (RandomUtils.nextInt(0, 10) > 6) ? PhoneNumberConfirmationMethod.SMS : PhoneNumberConfirmationMethod.CALL;
    }
}
