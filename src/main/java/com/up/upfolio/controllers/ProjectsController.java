package com.up.upfolio.controllers;

import com.up.upfolio.model.projects.GetProjectsResponse;
import com.up.upfolio.model.projects.InputProjectModel;
import com.up.upfolio.model.projects.ProjectModel;
import com.up.upfolio.services.projects.ProjectsService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectsController extends BaseController {
    private final ProjectsService projectsService;

    @GetMapping("/specialist/{userUuid}")
    public GetProjectsResponse getSpecialistProjects(@Nullable @Parameter(hidden = true) UUID requestedBy, @PathVariable UUID userUuid) {
        List<ProjectModel> projects = projectsService.getSpecialistProjects(requestedBy, userUuid);
        return new GetProjectsResponse(userUuid, projects);
    }

    @GetMapping("/project/{projectUuid}")
    public ProjectModel getProject(@Nullable @Parameter(hidden = true) UUID requestedBy, @PathVariable UUID projectUuid) {
        return projectsService.getProject(requestedBy, projectUuid);
    }

    @PostMapping("/create")
    public ProjectModel createProject(@Parameter(hidden = true) UUID requestedBy, @Valid @RequestBody InputProjectModel inputProjectModel) {
        return projectsService.createProject(requestedBy, inputProjectModel);
    }

    @PostMapping("/editProject/{projectUuid}")
    public ProjectModel editProject(@Parameter(hidden = true) UUID requestedBy, @PathVariable UUID projectUuid, @Valid @RequestBody InputProjectModel inputProjectModel) {
        return projectsService.updateProject(requestedBy, projectUuid, inputProjectModel);
    }
}
