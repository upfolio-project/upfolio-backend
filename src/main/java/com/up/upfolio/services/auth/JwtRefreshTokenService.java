package com.up.upfolio.services.auth;

import com.up.upfolio.entities.User;

public interface JwtRefreshTokenService {
    String createRefreshToken(User user);
    String refreshJwtToken(String refreshToken);
}
