package com.up.upfolio.repositories;

import com.up.upfolio.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
