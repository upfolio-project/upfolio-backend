package com.up.upfolio.services.profile;

import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.user.EditProfileModel;
import com.up.upfolio.model.user.ProfileModel;

import java.util.UUID;

public interface ProfileService {
    void createBlankProfile(UUID userUuid, UserRealName realName);
    ProfileModel getProfile(UUID userUuid, String username);
    ProfileModel editProfile(UUID userUuid, EditProfileModel editProfile);
    GetMeResponse getMe(UUID userUuid);
}
