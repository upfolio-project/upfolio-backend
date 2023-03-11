package com.up.upfolio.controllers;

import com.up.upfolio.entities.UserRealNameModel;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.auth.ConfirmPhoneOtpRequest;
import com.up.upfolio.model.api.request.auth.FinishRegistrationRequest;
import com.up.upfolio.model.api.request.auth.RegisterByPhoneNumberRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.api.response.auth.RegisterTokenResponse;
import com.up.upfolio.services.auth.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/register")
@RequiredArgsConstructor
public class RegisterController extends BaseController {
    private final RegistrationService registrationService;

    @GetMapping("/getRegisterToken")
    public RegisterTokenResponse getRegisterToken() {
        String token = registrationService.getRegisterToken();
        return new RegisterTokenResponse(token);
    }

    @PostMapping("/phoneNumber")
    public SuccessResponse commenceByPhoneNumber(@RequestBody RegisterByPhoneNumberRequest request) {
        registrationService.sendOtpCode(request.registerToken(), request.phoneNumber());

        return new SuccessResponse();
    }

    @PostMapping("/confirm")
    public SuccessResponse confirmPhoneOTP(@RequestBody ConfirmPhoneOtpRequest request) {
        boolean ok = registrationService.verifyOtpCode(request.registerToken(), request.code());

        if (!ok) {
            throw new GenericApiErrorException(403, "Invalid OTP code");
        }

        return new SuccessResponse();
    }

    @PostMapping("/finish")
    public JwtSuccessAuthResponse finish(@RequestBody FinishRegistrationRequest request) {
        return registrationService.finish(request.registerToken(),
                new UserRealNameModel(request.firstName(), request.lastName()), request.password());
    }
}
