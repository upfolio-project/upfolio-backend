package com.up.upfolio.repositories;

import com.up.upfolio.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
