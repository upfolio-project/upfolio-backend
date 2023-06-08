package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.profile.InputProfileModel;
import com.up.upfolio.model.profile.ProfileModel;

import java.util.UUID;

public interface ProfileService {
    void createBlankProfile(UUID userUuid, UserRealName realName);

    ProfileModel getProfile(UUID userUuid, String username);

    ProfileModel editProfile(UUID userUuid, InputProfileModel editProfile);

    GetMeResponse getMe(UUID userUuid);

    ProfileEntity getByUuid(UUID uuid);

    void attachProject(ProfileEntity profile, ProjectEntity project);

    void updateProfilePhotoKey(UUID userUuid, String key);
}
