package com.up.upfolio.services.auth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.user.UserRealNameModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationServiceImpl implements RegistrationService {
    private final Cache<String, RegistrationState> stateHolder;
    private final OtpCodeGenerator otpCodeGenerator;

    private static final int MAX_REGISTRATIONS = 100;

    public RegistrationServiceImpl(OtpCodeGenerator generator) {
        stateHolder = CacheBuilder.newBuilder().maximumSize(MAX_REGISTRATIONS).build();
        otpCodeGenerator = generator;
    }

    @Override
    public String getRegisterToken() {
        String token = UUID.randomUUID().toString();
        stateHolder.put(token, new RegistrationState());

        return token;
    }

    @Override
    public void sendOtpCode(String registerToken, String phoneNumber) {
        RegistrationState state = getState(registerToken);

        if (state.getStep() != RegistrationState.Step.WAIT_FOR_PHONE_NUMBER)
            throw new GenericApiErrorException("Registration steps failure, please try reloading the page");

        // generate otp and send SMS
        String code = otpCodeGenerator.generateCode();

        state.setStep(RegistrationState.Step.WAIT_FOR_OTP_CODE);
        state.setOtpCode(code);
    }

    @Override
    public boolean verifyOtpCode(String registerToken, String code) {
        RegistrationState state = getState(registerToken);

        if (state.getStep() != RegistrationState.Step.WAIT_FOR_OTP_CODE)
            throw new GenericApiErrorException("Registration steps failure, please try reloading the page");

        if (!code.equals(state.getOtpCode()))
            return false;

        state.setStep(RegistrationState.Step.WAIT_FOR_FINISH);
        state.setOtpCode(null);

        return true;
    }

    @Override
    public JwtSuccessAuthResponse finish(String registerToken, UserRealNameModel realName) {
        RegistrationState state = getState(registerToken);

        if (state.getStep() != RegistrationState.Step.WAIT_FOR_FINISH)
            throw new GenericApiErrorException("Registration steps failure, please try reloading the page");

        if (!realName.isValid())
            throw new GenericApiErrorException("Bad real name input");

        // todo perform the actual registration steps and authorize the user

        return null;
    }

    private RegistrationState getState(String registerToken) {
        RegistrationState state = stateHolder.getIfPresent(registerToken);

        if (state == null)
            throw new GenericApiErrorException(403, "Registration token is not provided");

        return state;
    }
}
