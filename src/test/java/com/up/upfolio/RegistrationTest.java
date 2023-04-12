package com.up.upfolio;

import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.services.auth.RegistrationService;
import com.up.upfolio.services.auth.otp.OtpCodeGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationTest {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private OtpCodeGenerator otpCodeGenerator;

    @Test
    void registrationFlow_correctInputs() {
        Mockito.when(otpCodeGenerator.generateCode()).thenReturn("0101");

        String regToken = registrationService.getRegisterToken();
        registrationService.sendOtpCode(regToken, "70000000000");
        assertTrue(registrationService.verifyOtpCode(regToken, "0101"));

        JwtSuccessAuthResponse successAuthResponse = registrationService.finish(regToken, new UserRealName("FirstName", "LastName"), "PaSsFoRTeSt");

        assertNotNull(successAuthResponse);
        assertNotEquals(0, successAuthResponse.getRefreshToken().length());
        assertNotEquals(0, successAuthResponse.getToken().length());
    }

    @Test
    void testOtpGenerator() {
        Mockito.when(otpCodeGenerator.generateCode()).thenCallRealMethod();

        String code1 = otpCodeGenerator.generateCode(); // must not be mocked at this point

        assertEquals(4, code1.length());

        String code2 = otpCodeGenerator.generateCode();
        String code3 = otpCodeGenerator.generateCode();

        System.out.println(code1);
        System.out.println(code2);
        System.out.println(code3);
    }

    @Test
    void wrongPhoneNumberAndOtpCode() {
        Mockito.when(otpCodeGenerator.generateCode()).thenReturn("0101");

        String regToken = registrationService.getRegisterToken();

        assertThrows(GenericApiErrorException.class, () -> registrationService.sendOtpCode(regToken, "...."));

        registrationService.sendOtpCode(regToken, "70000000000");

        for (int i = 0; i < registrationService.getMaxOtpAttempts(); i++)
            assertFalse(registrationService.verifyOtpCode(regToken, "0000"));

        assertThrows(GenericApiErrorException.class, () -> registrationService.verifyOtpCode(regToken, "0101"));
    }
}
