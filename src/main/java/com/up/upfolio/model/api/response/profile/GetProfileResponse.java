package com.up.upfolio.model.api.response.profile;

import com.up.upfolio.model.api.response.BaseApiResponse;
import com.up.upfolio.model.user.ProfileModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProfileResponse extends BaseApiResponse {
    private ProfileModel profile;
}
