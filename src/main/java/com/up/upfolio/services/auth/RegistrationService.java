package com.up.upfolio.services.auth;

import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.user.UserRealNameModel;

public interface RegistrationService {
    String getRegisterToken();

    void sendOtpCode(String registerToken, String phoneNumber);
    boolean verifyOtpCode(String registerToken, String code);
    JwtSuccessAuthResponse finish(String registerToken, UserRealNameModel realName);
}
