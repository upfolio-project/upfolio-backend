package com.up.upfolio.services.auth.otp;

import com.up.upfolio.services.auth.RegistrationState;

public interface ConfirmationMethodSelector {
    RegistrationState.ConfirmationMethod select();
}
