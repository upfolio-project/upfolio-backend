package com.up.upfolio.model.projects;

import com.up.upfolio.model.jobs.JobModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetJobsResponse {
    private UUID userUuid;
    private List<JobModel> jobs;
}
