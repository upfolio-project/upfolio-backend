package com.up.upfolio.services.metrics;

import com.up.upfolio.model.metrics.SiteEntitiesCount;
import com.up.upfolio.repositories.JobsRepository;
import com.up.upfolio.repositories.OrganizationRepository;
import com.up.upfolio.repositories.SpecialistRepository;
import com.up.upfolio.repositories.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetricsServiceImpl implements MetricsService {
    private final JobsRepository jobsRepository;
    private final ProjectsRepository projectsRepository;
    private final SpecialistRepository specialistRepository;
    private final OrganizationRepository organizationRepository;

    @Override
    public SiteEntitiesCount getSiteEntitiesCount() {
        return new SiteEntitiesCount((int) jobsRepository.count(), (int) jobsRepository.countByOpen(true), (int) projectsRepository.count(),
                (int) specialistRepository.count(), (int) organizationRepository.count());
    }
}
