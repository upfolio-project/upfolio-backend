package com.up.upfolio.controllers;

import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.auth.ConfirmPhoneOtpRequest;
import com.up.upfolio.model.api.request.auth.FinishRegistrationRequest;
import com.up.upfolio.model.api.request.auth.RegisterByPhoneNumberRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.api.response.auth.RegisterTokenResponse;
import com.up.upfolio.services.auth.RegistrationService;
import jakarta.validation.Valid;
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
    public SuccessResponse commenceByPhoneNumber(@RequestBody @Valid RegisterByPhoneNumberRequest request) {
        registrationService.sendOtpCode(request.getRegisterToken(), request.getPhoneNumber());

        return new SuccessResponse();
    }

    @PostMapping("/confirm")
    public SuccessResponse confirmPhoneOTP(@RequestBody @Valid ConfirmPhoneOtpRequest request) {
        boolean ok = registrationService.verifyOtpCode(request.getRegisterToken(), request.getCode());

        if (!ok) {
            throw new GenericApiErrorException(403, "Invalid OTP code");
        }

        return new SuccessResponse();
    }

    @PostMapping("/finish")
    public JwtSuccessAuthResponse finish(@RequestBody @Valid FinishRegistrationRequest request) {
        return registrationService.finish(request.getRegisterToken(),
                new UserRealName(request.getFirstName(), request.getLastName()), request.getPassword());
    }
}
