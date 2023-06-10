package com.up.upfolio.model.api.response;

import com.up.upfolio.model.organization.OrganizationModel;
import com.up.upfolio.model.profile.ProfileModel;
import com.up.upfolio.model.user.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class UserInfoResponse extends BaseApiResponse {
    private final UserType userType;
    private final String username;
    private final UUID userUuid;

    private ProfileModel profile;
    private OrganizationModel organization;
}
