package com.up.upfolio.controllers;

import com.up.upfolio.entities.UserEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.request.auth.AuthorizeByPasswordRequest;
import com.up.upfolio.model.api.request.auth.JwtRefreshTokenRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.auth.JwtAuthenticationService;
import com.up.upfolio.services.auth.JwtRefreshTokenService;
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
    private final JwtRefreshTokenService jwtRefreshTokenService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/byPassword")
    public JwtSuccessAuthResponse byPassword(@RequestBody @Valid AuthorizeByPasswordRequest request) {
        UserEntity user = userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
            throw new GenericApiErrorException(ErrorDescriptor.INCORRECT_PASSWORD);

        String jwtToken = jwtAuthenticationService.generate(user.getUuid());
        String jwtRefreshToken = jwtRefreshTokenService.createRefreshToken(user);

        return new JwtSuccessAuthResponse(jwtToken, jwtRefreshToken);
    }

    @PostMapping("/refresh")
    public JwtSuccessAuthResponse byRefreshToken(@RequestBody @Valid JwtRefreshTokenRequest request) {
        String newJwt = jwtRefreshTokenService.refreshJwtToken(request.getRefreshToken());
        return new JwtSuccessAuthResponse(newJwt, request.getRefreshToken());
    }
}
