package com.up.upfolio.services.auth;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface JwtAuthenticationService {
    UUID getUserUuid(Authentication authentication);
    String generate(UUID userUuid);
}
