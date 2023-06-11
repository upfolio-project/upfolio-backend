package com.up.upfolio.services.projects;

import com.up.upfolio.model.projects.InputProjectModel;
import com.up.upfolio.model.projects.ProjectModel;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface ProjectsService {
    ProjectModel createProject(UUID requestedBy, InputProjectModel inputProjectModel);

    ProjectModel getProject(@Nullable UUID requestedBy, UUID projectUuid);

    List<ProjectModel> getSpecialistProjects(@Nullable UUID requestedBy, UUID userUuid);

    ProjectModel updateProject(UUID requestedBy, UUID projectUuid, InputProjectModel inputProjectModel);
}
