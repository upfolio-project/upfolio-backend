package com.up.upfolio.services.organization;

import com.up.upfolio.model.profile.OrganizationModel;
import com.up.upfolio.model.profile.InputOrganizationModel;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface OrganizationService {
    void createBlankOrganization(UUID userUuid, OrganizationBasicDetails basicDetails);

    OrganizationModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target);

    OrganizationModel editProfile(@NonNull UUID userUuid, InputOrganizationModel inputOrganizationModel);
}
