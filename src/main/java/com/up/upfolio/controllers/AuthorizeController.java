package com.up.upfolio.controllers;

import com.up.upfolio.entities.User;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.auth.AuthorizeByPasswordRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.auth.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/authorize")
@RequiredArgsConstructor
public class AuthorizeController extends BaseController {
    private final JwtAuthenticationService jwtAuthenticationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/byPassword")
    public JwtSuccessAuthResponse byPassword(@RequestBody AuthorizeByPasswordRequest request) {
        User user = userRepository.findByPhoneNumber(request.phoneNumber()).orElseThrow(() -> new GenericApiErrorException("The account with this phone number is not found"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash()))
            throw new GenericApiErrorException(403, "Password is incorrect");

        String jwtToken = jwtAuthenticationService.generate(user.getUuid());

        return new JwtSuccessAuthResponse(jwtToken, "");
    }
}
