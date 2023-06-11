package com.up.upfolio.controllers;

import com.up.upfolio.model.jobs.InputJobModel;
import com.up.upfolio.model.jobs.JobModel;
import com.up.upfolio.model.projects.GetJobsResponse;
import com.up.upfolio.services.jobs.JobsService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
public class JobsController extends BaseController {
    private final JobsService jobsService;

    @GetMapping("/organization/{userUuid}")
    public GetJobsResponse getSpecialistProjects(@Nullable @Parameter(hidden = true) UUID requestedBy, @PathVariable UUID userUuid) {
        List<JobModel> projects = jobsService.getOrganizationJobs(requestedBy, userUuid);
        return new GetJobsResponse(userUuid, projects);
    }


    @GetMapping("/job/{jobUuid}")
    public JobModel getJob(@Nullable @Parameter(hidden = true) UUID requestedBy, @PathVariable UUID jobUuid) {
        return jobsService.getJob(requestedBy, jobUuid);
    }

    @PostMapping("/create")
    public JobModel createJob(@Parameter(hidden = true) UUID requestedBy, @Valid @RequestBody InputJobModel inputJobModel) {
        return jobsService.createJob(requestedBy, inputJobModel);
    }


    @PostMapping("/editJob/{jobUuid}")
    public JobModel editJob(@Parameter(hidden = true) UUID requestedBy, @PathVariable UUID jobUuid, @Valid @RequestBody InputJobModel inputJobModel) {
        return jobsService.updateJob(requestedBy, jobUuid, inputJobModel);
    }
}
