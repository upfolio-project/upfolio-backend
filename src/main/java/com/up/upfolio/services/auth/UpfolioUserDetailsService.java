package com.up.upfolio.services.auth;

import com.up.upfolio.entities.UserEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import com.up.upfolio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpfolioUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private final JwtAuthenticationService jwtAuthenticationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtRefreshTokenService jwtRefreshTokenService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + phoneNumber));
    }

    public JwtSuccessAuthResponse authorizeByPassword(String phoneNumber, String password) {
        UserEntity user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPasswordHash()))
            throw new GenericApiErrorException(ErrorDescriptor.INCORRECT_PASSWORD);

        String jwtToken = jwtAuthenticationService.generate(user.getUuid());
        String jwtRefreshToken = jwtRefreshTokenService.createRefreshToken(user);

        return new JwtSuccessAuthResponse(jwtToken, jwtRefreshToken);
    }

    public JwtSuccessAuthResponse refreshJwt(String refreshToken) {
        String newJwt = jwtRefreshTokenService.refreshJwtToken(refreshToken);
        return new JwtSuccessAuthResponse(newJwt, refreshToken);
    }
}