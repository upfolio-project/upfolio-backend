package com.up.upfolio.repositories;

import com.up.upfolio.entities.JwtRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface JwtRefreshTokenRepository extends CrudRepository<JwtRefreshToken, String> {
}
