package com.up.upfolio.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@RequiredArgsConstructor
public class SecurityControllerAdvice {
    @ModelAttribute
    public UUID loggedUserUUID(Authentication authentication) {
        if (authentication == null) return null;

        Jwt jwt = (Jwt) authentication.getPrincipal();

        return UUID.fromString(jwt.getSubject());
    }
}
