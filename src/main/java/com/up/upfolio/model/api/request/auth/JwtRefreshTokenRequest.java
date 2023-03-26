package com.up.upfolio.model.api.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRefreshTokenRequest {
    @JsonProperty(value = "refreshToken", required = true)
    @NotBlank
    private String refreshToken;
}
