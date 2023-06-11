package com.up.upfolio.services.jobs;

import com.up.upfolio.entities.JobEntity;
import com.up.upfolio.entities.OrganizationEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.jobs.InputJobModel;
import com.up.upfolio.model.jobs.JobModel;
import com.up.upfolio.repositories.JobsRepository;
import com.up.upfolio.services.organization.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional
public class JobsServiceImpl implements JobsService {
    private final JobsRepository jobsRepository;
    private final OrganizationService organizationService;
    private final ModelMapper modelMapper;

    @Override
    public JobModel createJob(UUID userUuid, InputJobModel inputJobModel) {
        OrganizationEntity org = organizationService.getEntityByUuid(userUuid, true);

        JobEntity entity = new JobEntity();
        entity.setCreated(OffsetDateTime.now());
        entity.setAuthor(org);

        writeFields(entity, inputJobModel);

        entity = jobsRepository.save(entity);
        organizationService.attachJob(org, entity);

        return map(entity);
    }

    @Override
    public JobModel getJob(UUID requestedBy, UUID jobUuid) {
        // we don't check requestedBy because job openings are always publicly visible

        return map(jobsRepository.findById(jobUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.JOB_NOT_FOUND)));
    }

    @Override
    public List<JobModel> getOrganizationJobs(UUID requestedBy, UUID target) {
        OrganizationEntity org = organizationService.getEntityByUuid(target, false);

        return org.getJobs().stream().map(this::map).toList();
    }

    @Override
    public JobModel updateJob(UUID requestedBy, UUID jobUuid, InputJobModel inputJobModel) {
        OrganizationEntity org = organizationService.getEntityByUuid(requestedBy, true);
        JobEntity entity = jobsRepository.findById(jobUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.JOB_NOT_FOUND));

        // check job ownership
        if (!entity.getAuthor().getUserUuid().equals(org.getUserUuid()))
            throw new GenericApiErrorException(ErrorDescriptor.CANNOT_EDIT_THIS_JOB);

        writeFields(entity, inputJobModel);
        entity = jobsRepository.save(entity);

        return map(entity);
    }

    private JobModel map(JobEntity entity) {
        return modelMapper.map(entity, JobModel.class);
    }

    private void writeFields(JobEntity job, InputJobModel inputJobModel) {
        job.setUpdated(OffsetDateTime.now());
        job.setUpdated(OffsetDateTime.now());
        job.setTags(inputJobModel.getTags());
        job.setOpen(inputJobModel.getOpen());
        job.setDescription(inputJobModel.getDescription());
        job.setTitle(inputJobModel.getTitle());
    }
}
