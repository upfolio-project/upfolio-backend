package com.up.upfolio.repositories;

import com.up.upfolio.entities.UsernameMappingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsernameMappingRepository extends CrudRepository<UsernameMappingEntity, UUID> {
    Optional<UsernameMappingEntity> findByUsernameIgnoreCase(String username);
    boolean existsByUsernameIgnoreCase(String username);
}
