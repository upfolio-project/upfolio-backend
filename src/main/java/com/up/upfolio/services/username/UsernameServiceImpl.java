package com.up.upfolio.services.username;

import com.up.upfolio.entities.UsernameMappingEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.repositories.UsernameMappingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Component
public class UsernameServiceImpl implements UsernameService {
    private final UsernameMappingRepository usernameMappingRepository;

    @Override
    public UUID resolve(String username) {
        return get(username).getUserUuid();
    }

    @Override
    public String resolve(UUID userUuid) {
        return usernameMappingRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND)).getUsername();
    }

    @Override
    public String create(UUID userUuid, String newUsername) {
        newUsername = UsernameService.sanitize(newUsername);

        UsernameMappingEntity entity = new UsernameMappingEntity();
        entity.setUsername(newUsername);
        entity.setUserUuid(userUuid);

        return usernameMappingRepository.save(entity).getUsername();
    }

    @Override
    public String create(UUID userUuid) {
        return create(userUuid, generateRandomUsername());
    }

    @Override
    public String update(UUID userUuid, String newUsername) {
        newUsername = UsernameService.sanitize(newUsername);

        UsernameMappingEntity entity = usernameMappingRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));

        entity.setUsername(newUsername);

        usernameMappingRepository.save(entity);

        return newUsername;
    }

    private UsernameMappingEntity get(String username) {
        username = UsernameService.sanitize(username);

        return usernameMappingRepository.findByUsername(username).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));
    }

    private String generateRandomUsername() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}
