package com.up.upfolio.services.jobs;

import com.up.upfolio.model.jobs.InputJobModel;
import com.up.upfolio.model.jobs.JobModel;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface JobsService {
    JobModel createJob(UUID userUuid, InputJobModel inputJobModel);

    JobModel getJob(UUID requestedBy, UUID jobUuid);

    List<JobModel> getOrganizationJobs(@Nullable UUID requestedBy, UUID target);

    JobModel updateJob(UUID requestedBy, UUID jobUuid, InputJobModel inputJobModel);
}
