package com.up.upfolio.model.api.response.profile;

import com.up.upfolio.model.api.response.BaseApiResponse;
import com.up.upfolio.model.profile.OrganizationModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class GetOrganizationResponse extends BaseApiResponse {
    private OrganizationModel profile;
}
