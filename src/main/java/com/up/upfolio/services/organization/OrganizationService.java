package com.up.upfolio.services.organization;

import com.up.upfolio.model.organization.OrganizationModel;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface OrganizationService {
    void createBlankOrganization(UUID userUuid, OrganizationBasicDetails basicDetails);

    OrganizationModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target);
}
