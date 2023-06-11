package com.up.upfolio.repositories;

import com.up.upfolio.entities.JobEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JobsRepository extends CrudRepository<JobEntity, UUID> {
    long countByOpen(Boolean open);
}
