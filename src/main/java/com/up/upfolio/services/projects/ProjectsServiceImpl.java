package com.up.upfolio.services.projects;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.projects.InputProjectModel;
import com.up.upfolio.model.projects.ProjectModel;
import com.up.upfolio.repositories.ProjectsRepository;
import com.up.upfolio.services.profile.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {
    private final ProfileService profileService;
    private final ProjectsRepository projectsRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectModel createProject(UUID requestedBy, InputProjectModel inputProjectModel) {
        ProfileEntity profile = profileService.getByUuid(requestedBy);

        ProjectEntity project = new ProjectEntity();

        project.setCreated(OffsetDateTime.now());
        project.setUpdated(OffsetDateTime.now());
        project.setAuthor(profile);

        project.setTags(inputProjectModel.getTags());
        project.setTitle(inputProjectModel.getTitle());
        project.setDescription(inputProjectModel.getDescription());

        project = projectsRepository.save(project);
        profileService.attachProject(profile, project);

        return modelMapper.map(project, ProjectModel.class);
    }

    @Override
    public ProjectModel getProject(UUID requestedBy, UUID projectUuid) {
        ProjectEntity project = getProject(projectUuid);
        checkCanViewProject(requestedBy, project);

        return modelMapper.map(project, ProjectModel.class);
    }

    @Override
    public List<ProjectModel> getUserProjects(@Nullable UUID requestedBy, UUID userUuid) {
        ProfileEntity target = profileService.getByUuid(userUuid);
        checkCanViewProjects(requestedBy, target);

        return target.getProjects().stream().map(p -> modelMapper.map(p, ProjectModel.class)).toList();
    }

    @Override
    public ProjectModel updateProject(UUID requestedBy, UUID projectUuid, InputProjectModel inputProjectModel) {
        ProjectEntity project = getProject(projectUuid);
        checkIsProjectOwner(requestedBy, project);

        project.setDescription(inputProjectModel.getDescription());
        project.setTags(inputProjectModel.getTags());
        project.setTitle(inputProjectModel.getTitle());

        return modelMapper.map(projectsRepository.save(project), ProjectModel.class);
    }

    private void checkCanViewProject(@Nullable UUID requestedBy, ProjectEntity project) {
        if (project.getAuthor().getUserUuid().equals(requestedBy)) return;

        if (project.getAuthor().isPrivate())
            throw new GenericApiErrorException(ErrorDescriptor.PROJECT_NOT_FOUND);
    }

    private void checkCanViewProjects(@Nullable UUID requestedBy, ProfileEntity target) {
        if (target.getUserUuid().equals(requestedBy) || !target.isPrivate()) return;

        throw new GenericApiErrorException(ErrorDescriptor.CANNOT_VIEW_THIS_PROFILE);
    }

    private void checkIsProjectOwner(@NonNull UUID requestedBy, ProjectEntity project) {
        checkCanViewProject(requestedBy, project);

        if (project.getAuthor().getUserUuid().equals(requestedBy)) return;

        throw new GenericApiErrorException(ErrorDescriptor.CANNOT_EDIT_THIS_PROJECT);
    }

    private ProjectEntity getProject(UUID projectUuid) {
        return projectsRepository.findById(projectUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.PROJECT_NOT_FOUND));
    }
}
