package com.up.upfolio;

import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.services.auth.RegistrationService;
import com.up.upfolio.services.auth.otp.OtpCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHelper {
    private final RegistrationService registrationService;

    @SpyBean
    private OtpCodeGenerator otpCodeGenerator;

    public JwtSuccessAuthResponse makeAccount() {
        Mockito.when(otpCodeGenerator.generateCode()).thenReturn("0101");

        String regToken = registrationService.getRegisterToken();
        registrationService.sendOtpCode(regToken, "70000000000");
        registrationService.verifyOtpCode(regToken, "0101");

        return registrationService.finish(regToken, new UserRealName("FirstName", "LastName"), "PaSsFoRTeSt");
    }
}
