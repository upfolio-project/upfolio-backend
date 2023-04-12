package com.up.upfolio.services.auth;

import com.up.upfolio.entities.UserEntity;

public interface JwtRefreshTokenService {
    String createRefreshToken(UserEntity user);
    String refreshJwtToken(String refreshToken);
}
