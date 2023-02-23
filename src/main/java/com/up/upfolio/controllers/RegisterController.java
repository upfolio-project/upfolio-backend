package com.up.upfolio.controllers;

import com.up.upfolio.model.api.request.auth.ConfirmPhoneOtpRequest;
import com.up.upfolio.model.api.request.auth.FinishRegistrationRequest;
import com.up.upfolio.model.api.request.auth.RegisterByPhoneNumberRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
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
    public SuccessResponse confirmPhoneOTP(@RequestBody ConfirmPhoneOtpRequest request) {
        throw new NotImplementedException();
    }

    @PostMapping("/finish")
    public JwtSuccessAuthResponse finish(@RequestBody FinishRegistrationRequest request) {
        throw new NotImplementedException();
    }
}
