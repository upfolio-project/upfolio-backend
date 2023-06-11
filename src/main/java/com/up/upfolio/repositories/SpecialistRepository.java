package com.up.upfolio.repositories;

import com.up.upfolio.entities.SpecialistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpecialistRepository extends CrudRepository<SpecialistEntity, UUID> {
}
