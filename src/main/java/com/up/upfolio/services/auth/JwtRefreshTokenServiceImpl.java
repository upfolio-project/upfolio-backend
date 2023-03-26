package com.up.upfolio.services.auth;

import com.up.upfolio.entities.JwtRefreshToken;
import com.up.upfolio.entities.User;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.repositories.JwtRefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {
    private static final long REFRESH_TTL = 50L * 24L * 60L * 60L;

    private final JwtRefreshTokenRepository jwtRefreshTokenRepository;
    private final SecureRandom secureRandom = new SecureRandom();
    private final JwtAuthenticationService jwtAuthenticationService;

    @Override
    public String createRefreshToken(User user) {
        JwtRefreshToken jwtRefreshTokenEntity = new JwtRefreshToken();

        jwtRefreshTokenEntity.setCreated(OffsetDateTime.now());
        jwtRefreshTokenEntity.setUser(user);

        String newToken = generateSeq();
        jwtRefreshTokenEntity.setTokenHash(DigestUtils.sha256Hex(newToken));

        jwtRefreshTokenRepository.save(jwtRefreshTokenEntity);

        return newToken;
    }

    public String refreshJwtToken(String refreshToken) {
        String tokenHash = DigestUtils.sha256Hex(refreshToken);

        JwtRefreshToken jwtRefreshTokenEntity = jwtRefreshTokenRepository.findById(tokenHash).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.SESSION_EXPIRED));

        if (isExpired(jwtRefreshTokenEntity.getCreated())) {
            jwtRefreshTokenRepository.delete(jwtRefreshTokenEntity);
            throw new GenericApiErrorException(ErrorDescriptor.SESSION_EXPIRED);
        }

        jwtRefreshTokenEntity.setUsageCount(jwtRefreshTokenEntity.getUsageCount() + 1);
        jwtRefreshTokenRepository.save(jwtRefreshTokenEntity);

        return jwtAuthenticationService.generate(jwtRefreshTokenEntity.getUser().getUuid());
    }


    private String generateSeq() {
        byte[] buf = new byte[64];
        secureRandom.nextBytes(buf);

        return Base64.getUrlEncoder().encodeToString(buf);
    }

    private boolean isExpired(OffsetDateTime created) {
        OffsetDateTime now = OffsetDateTime.now();

        return now.toEpochSecond() - created.toEpochSecond() >= REFRESH_TTL;
    }
}
