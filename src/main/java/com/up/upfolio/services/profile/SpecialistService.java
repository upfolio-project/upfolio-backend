package com.up.upfolio.services.profile;

import com.up.upfolio.entities.SpecialistEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.model.profile.InputSpecialistModel;
import com.up.upfolio.model.profile.SpecialistModel;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface SpecialistService {
    void createBlankProfile(@NonNull UUID requestedBy, UserRealName realName);

    SpecialistModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target);

    SpecialistModel editProfile(@NonNull UUID requestedBy, InputSpecialistModel editProfile);

    SpecialistEntity getByUuid(UUID uuid, boolean selfAccess);

    void attachProject(SpecialistEntity profile, ProjectEntity project);

    void updateProfilePhotoKey(@NonNull UUID requestedBy, String key);
}
