package com.up.upfolio.services.auth.otp;

import com.up.upfolio.services.auth.RegistrationState;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public final class ConfirmationMethodSelectorImpl implements ConfirmationMethodSelector {
    @Override
    public RegistrationState.ConfirmationMethod select() {
        return (RandomUtils.nextInt(0, 10) > 6) ? RegistrationState.ConfirmationMethod.SMS : RegistrationState.ConfirmationMethod.CALL;
    }
}
