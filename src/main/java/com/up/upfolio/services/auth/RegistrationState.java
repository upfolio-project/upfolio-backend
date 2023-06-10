package com.up.upfolio.services.auth;

import com.up.upfolio.model.user.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RegistrationState {
    public enum Step {
        WAIT_FOR_PHONE_NUMBER,
        WAIT_FOR_OTP_CODE,
        WAIT_FOR_FINISH
    }

    public enum ConfirmationMethod {
        SMS,
        CALL
    }

    private final ConfirmationMethod confirmationMethod;
    private final UserType userType;

    private Step step;
    private String otpCode;
    private int otpAttemptCounter;
    private String phoneNumber;

    public RegistrationState(UserType userType, ConfirmationMethod confirmationMethod) {
        this.userType = userType;
        this.step = Step.WAIT_FOR_PHONE_NUMBER;
        this.confirmationMethod = confirmationMethod;
    }

    public RegistrationState(ConfirmationMethod confirmationMethod) {
        this(UserType.SPECIALIST, confirmationMethod);
    }
}
