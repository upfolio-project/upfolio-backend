package com.up.upfolio.repositories;

import com.up.upfolio.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProfileRepository extends CrudRepository<ProfileEntity, UUID> {
}
