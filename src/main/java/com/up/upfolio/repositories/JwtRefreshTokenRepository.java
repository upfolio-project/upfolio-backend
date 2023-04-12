package com.up.upfolio.repositories;

import com.up.upfolio.entities.JwtRefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface JwtRefreshTokenRepository extends CrudRepository<JwtRefreshTokenEntity, String> {
}
