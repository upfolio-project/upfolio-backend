package com.up.upfolio.controllers;

import com.up.upfolio.model.api.request.auth.ConfirmPhoneOTPRequest;
import com.up.upfolio.model.api.request.auth.FinishRegistrationRequest;
import com.up.upfolio.model.api.request.auth.RegisterByPhoneNumberRequest;
import com.up.upfolio.model.api.response.auth.JWTSuccessAuthResponse;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.api.response.auth.RegisterTokenResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/register")
public class RegisterController extends BaseController {
    @GetMapping("/getRegisterToken")
    public RegisterTokenResponse getRegisterToken() {
        throw new NotImplementedException();
    }

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
