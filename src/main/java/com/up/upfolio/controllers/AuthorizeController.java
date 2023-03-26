package com.up.upfolio.controllers;

import com.up.upfolio.entities.User;
import com.up.upfolio.exceptions.ErrorBulk;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.auth.AuthorizeByPasswordRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.auth.JwtAuthenticationService;
import jakarta.validation.Valid;
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
    public JwtSuccessAuthResponse byPassword(@RequestBody @Valid AuthorizeByPasswordRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new GenericApiErrorException(ErrorBulk.ACCOUNT_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
            throw new GenericApiErrorException(ErrorBulk.INCORRECT_PASSWORD);

        String jwtToken = jwtAuthenticationService.generate(user.getUuid());

        return new JwtSuccessAuthResponse(jwtToken, "");
    }
}
