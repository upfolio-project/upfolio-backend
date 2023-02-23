package com.up.upfolio.model.api.response.auth;

import com.up.upfolio.model.api.response.BaseApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterTokenResponse extends BaseApiResponse {
    private String token;
}
