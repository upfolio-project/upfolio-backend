package com.upfolio.upfolio.controllers;

import com.upfolio.upfolio.model.api.request.auth.ConfirmPhoneOTPRequest;
import com.upfolio.upfolio.model.api.request.auth.FinishRegistrationRequest;
import com.upfolio.upfolio.model.api.request.auth.RegisterByPhoneNumberRequest;
import com.upfolio.upfolio.model.api.response.auth.JWTSuccessAuthResponse;
import com.upfolio.upfolio.model.api.response.SuccessResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {
    @PostMapping("/phoneNumber")
    public SuccessResponse commenceByPhoneNumber(@RequestBody RegisterByPhoneNumberRequest request) {
        throw new NotImplementedException();
    }

    @PostMapping("/confirm")
    public SuccessResponse confirmPhoneOTP(@RequestBody ConfirmPhoneOTPRequest request) {
        throw new NotImplementedException();
    }

    @PostMapping("/finish")
    public JWTSuccessAuthResponse finish(@RequestBody FinishRegistrationRequest request) {
        throw new NotImplementedException();
    }
}
