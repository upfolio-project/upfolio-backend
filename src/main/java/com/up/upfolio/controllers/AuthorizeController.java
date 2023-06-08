package com.up.upfolio.controllers;

import com.up.upfolio.model.api.request.auth.AuthorizeByPasswordRequest;
import com.up.upfolio.model.api.request.auth.JwtRefreshTokenRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.services.auth.UpfolioUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/authorize")
@RequiredArgsConstructor
public class AuthorizeController extends BaseController {
    private final UpfolioUserDetailsService userService;

    @PostMapping("/byPassword")
    public JwtSuccessAuthResponse byPassword(@RequestBody @Valid AuthorizeByPasswordRequest request) {
        return userService.authorizeByPassword(request.getPhoneNumber(), request.getPassword());
    }

    @PostMapping("/refresh")
    public JwtSuccessAuthResponse byRefreshToken(@RequestBody @Valid JwtRefreshTokenRequest request) {
        return userService.refreshJwt(request.getRefreshToken());
    }
}
