package com.up.upfolio.services.organization;

import com.up.upfolio.entities.JobEntity;
import com.up.upfolio.entities.OrganizationEntity;
import com.up.upfolio.model.profile.OrganizationModel;
import com.up.upfolio.model.profile.InputOrganizationModel;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface OrganizationService {
    void createBlankOrganization(UUID userUuid, OrganizationBasicDetails basicDetails);

    OrganizationModel getByUuid(@NonNull UUID target, boolean selfAccess);

    OrganizationEntity getEntityByUuid(@NonNull UUID target, boolean selfAccess);

    OrganizationModel editProfile(@NonNull UUID userUuid, InputOrganizationModel inputOrganizationModel);

    void attachJob(OrganizationEntity org, JobEntity job);

    void updateProfilePhotoKey(@NonNull UUID requestedBy, String key);
}
