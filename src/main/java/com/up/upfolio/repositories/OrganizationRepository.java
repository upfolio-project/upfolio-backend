package com.up.upfolio.repositories;

import com.up.upfolio.entities.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, UUID> {
}
