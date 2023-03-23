package com.up.upfolio.repositories;

import com.up.upfolio.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends CrudRepository<Profile, UUID> {
    Optional<Profile> findByUsername(String username);
}
