package com.up.upfolio.services.auth;

import com.up.upfolio.model.user.OrganizationBasicDetails;
import com.up.upfolio.model.user.UserType;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.model.api.response.register.OtpSentResponse;

public interface RegistrationService {
    String getRegisterToken(UserType userType);

    default String getRegisterToken() {
        return getRegisterToken(UserType.SPECIALIST);
    }

    OtpSentResponse sendOtpCode(String registerToken, String phoneNumber);

    boolean verifyOtpCode(String registerToken, String code);

    JwtSuccessAuthResponse finish(String registerToken, UserRealName realName, String password);

    JwtSuccessAuthResponse finish(String registerToken, OrganizationBasicDetails organizationBasicDetails, String password);


    int getMaxOtpAttempts();
}
