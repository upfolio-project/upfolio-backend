package com.up.upfolio.repositories;

import com.up.upfolio.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProjectsRepository extends CrudRepository<ProjectEntity, UUID> {
}
