package com.up.upfolio.services.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {
    private final JwtEncoder encoder;

    private static final long JWT_TTL_SECS = 36000L;

    @Override
    public String generate(UUID userUuid) {
        Instant requested = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(requested)
                .expiresAt(requested.plusSeconds(JWT_TTL_SECS))
                .subject(userUuid.toString())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public UUID getUserUuid(Authentication authentication) {
        return getUserUuid(authentication.getPrincipal());
    }

    public UUID getUserUuid(Object principal) {
        Jwt jwt = (Jwt) principal;

        return UUID.fromString(jwt.getSubject());
    }
}
