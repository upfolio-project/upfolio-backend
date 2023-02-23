package com.up.upfolio.model.api.response.profile;

import com.up.upfolio.model.api.response.BaseApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMeResponse extends BaseApiResponse {
    private String username;
    private String url;
}
