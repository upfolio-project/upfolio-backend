package com.up.upfolio.controllers;

import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.register.ConfirmPhoneOtpRequest;
import com.up.upfolio.model.api.request.register.FinishRecruiterRegistrationRequest;
import com.up.upfolio.model.api.request.register.FinishSpecialistRegistrationRequest;
import com.up.upfolio.model.api.request.register.RegisterByPhoneNumberRequest;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.model.api.response.register.OtpSentResponse;
import com.up.upfolio.model.api.response.register.RegisterTokenResponse;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.model.user.UserType;
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
    public RegisterTokenResponse getRegisterToken(@RequestParam(value = "type", defaultValue = "SPECIALIST") UserType type) {
        String token = registrationService.getRegisterToken(type);
        return new RegisterTokenResponse(token, type);
    }

    @PostMapping("/phoneNumber")
    public OtpSentResponse commenceByPhoneNumber(@Valid @RequestBody RegisterByPhoneNumberRequest request) {
        return registrationService.sendOtpCode(request.getRegisterToken(), request.getPhoneNumber());
    }

    @PostMapping("/confirm")
    public SuccessResponse confirmPhoneOTP(@Valid @RequestBody ConfirmPhoneOtpRequest request) {
        boolean ok = registrationService.verifyOtpCode(request.getRegisterToken(), request.getCode());

        if (!ok) throw new GenericApiErrorException(ErrorDescriptor.INVALID_OTP_CODE);

        return new SuccessResponse();
    }

    @PostMapping("/finishSpecialistRegistration")
    public JwtSuccessAuthResponse finishSpecialistRegistration(@Valid @RequestBody FinishSpecialistRegistrationRequest request) {
        return registrationService.finish(request.getRegisterToken(),
                new UserRealName(request.getFirstName(), request.getLastName()), request.getPassword());
    }

    @PostMapping("/finishRecruiterRegistration")
    public JwtSuccessAuthResponse finishRecruiterRegistration(@Valid @RequestBody FinishRecruiterRegistrationRequest request) {
        return registrationService.finish(request.getRegisterToken(),
                new OrganizationBasicDetails(request.getOrganizationName(), request.getLegalEntityName()), request.getPassword());
    }

}
