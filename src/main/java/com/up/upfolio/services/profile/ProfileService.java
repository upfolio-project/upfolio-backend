package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.model.profile.InputProfileModel;
import com.up.upfolio.model.profile.ProfileModel;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface ProfileService {
    void createBlankProfile(@NonNull UUID requestedBy, UserRealName realName);

    ProfileModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target);

    ProfileModel editProfile(@NonNull UUID requestedBy, InputProfileModel editProfile);

    ProfileEntity getByUuid(UUID uuid);

    void attachProject(ProfileEntity profile, ProjectEntity project);

    void updateProfilePhotoKey(@NonNull UUID requestedBy, String key);
}
