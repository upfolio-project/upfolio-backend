package com.up.upfolio.repositories;

import com.up.upfolio.entities.SpecialistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProfileRepository extends CrudRepository<SpecialistEntity, UUID> {
}
