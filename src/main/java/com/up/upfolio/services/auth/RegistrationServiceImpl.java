package com.up.upfolio.services.auth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.up.upfolio.entities.User;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.auth.otp.OtpCodeGenerator;
import com.up.upfolio.services.auth.otp.OtpCodeTransmitter;
import com.up.upfolio.services.profile.ProfileService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
@Transactional
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final Cache<String, RegistrationState> stateHolder;
    private final OtpCodeGenerator otpCodeGenerator;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberNormalizer phoneNumberNormalizer;
    private final UserRepository userRepository;
    private final JwtAuthenticationService jwtAuthenticationService;
    private final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private final SecureRandom secureRandom;
    private final OtpCodeTransmitter otpCodeTransmitter;
    private final ProfileService profileService;

    private static final int MAX_REGISTRATIONS = 100;
    private static final int MAX_OTP_ATTEMPTS = 5;

    public RegistrationServiceImpl(OtpCodeGenerator otpCodeGenerator, PasswordEncoder passwordEncoder, PhoneNumberNormalizer phoneNumberNormalizer,
                                   UserRepository userRepository, JwtAuthenticationService jwtAuthenticationService, SecureRandom secureRandom,
                                   OtpCodeTransmitter otpCodeTransmitter, ProfileService profileService) {

        stateHolder = CacheBuilder.newBuilder().maximumSize(MAX_REGISTRATIONS).build();

        this.passwordEncoder = passwordEncoder;
        this.phoneNumberNormalizer = phoneNumberNormalizer;
        this.userRepository = userRepository;
        this.jwtAuthenticationService = jwtAuthenticationService;
        this.otpCodeGenerator = otpCodeGenerator;
        this.secureRandom = secureRandom;
        this.otpCodeTransmitter = otpCodeTransmitter;
        this.profileService = profileService;
    }

    @Override
    public String getRegisterToken() {
        byte[] bytes = new byte[24];
        secureRandom.nextBytes(bytes);

        String token = base64Encoder.encodeToString(bytes);
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

        state.setPhoneNumber(phoneNumberNormalizer.normalize(phoneNumber));
        state.setStep(RegistrationState.Step.WAIT_FOR_OTP_CODE);
        state.setOtpCode(code);

        otpCodeTransmitter.sendCode(phoneNumber, code);
    }

    @Override
    public boolean verifyOtpCode(String registerToken, String code) {
        RegistrationState state = getState(registerToken);

        if (state.getStep() != RegistrationState.Step.WAIT_FOR_OTP_CODE)
            throw new GenericApiErrorException("Registration steps failure, please try reloading the page");

        if (state.getOtpAttemptCounter() >= MAX_OTP_ATTEMPTS)
            throw new GenericApiErrorException(403, "Too many one-time code attempts, registration rejected. Please reload the page");

        state.setOtpAttemptCounter(state.getOtpAttemptCounter() + 1);

        if (!code.equals(state.getOtpCode()))
            return false;

        state.setStep(RegistrationState.Step.WAIT_FOR_FINISH);
        state.setOtpCode(null);

        return true;
    }

    @Override
    public JwtSuccessAuthResponse finish(String registerToken, UserRealName realName, String password) {
        RegistrationState state = getState(registerToken);

        if (state.getStep() != RegistrationState.Step.WAIT_FOR_FINISH)
            throw new GenericApiErrorException("Registration steps failure, please try reloading the page");

        if (!realName.checkValid())
            throw new GenericApiErrorException("Bad user name");

        User user = new User();
        user.setName(realName);
        user.setPhoneNumber(state.getPhoneNumber());
        user.setPasswordHash(passwordEncoder.encode(password));
        user = userRepository.save(user);

        profileService.createBlankProfile(user.getUuid(), realName);

        String token = jwtAuthenticationService.generate(user.getUuid());

        return new JwtSuccessAuthResponse(token, "");
    }

    private RegistrationState getState(String registerToken) {
        RegistrationState state = stateHolder.getIfPresent(registerToken);

        if (state == null)
            throw new GenericApiErrorException(403, "Registration token is not provided");

        return state;
    }
}
