package com.up.upfolio.services.auth;

import com.up.upfolio.model.api.response.BaseApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class JwtRefreshTokenResultResponse extends BaseApiResponse {
    private final String token;
}
