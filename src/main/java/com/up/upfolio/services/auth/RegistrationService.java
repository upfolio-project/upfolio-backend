package com.up.upfolio.services.auth;

import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.model.api.response.auth.OtpSentResponse;

public interface RegistrationService {
    String getRegisterToken();

    OtpSentResponse sendOtpCode(String registerToken, String phoneNumber);
    boolean verifyOtpCode(String registerToken, String code);
    JwtSuccessAuthResponse finish(String registerToken, UserRealName realName, String password);
}
