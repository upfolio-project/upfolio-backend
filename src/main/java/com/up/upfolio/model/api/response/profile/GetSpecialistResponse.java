package com.up.upfolio.model.api.response.profile;

import com.up.upfolio.model.api.response.BaseApiResponse;
import com.up.upfolio.model.profile.SpecialistModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSpecialistResponse extends BaseApiResponse {
    private SpecialistModel profile;
}
