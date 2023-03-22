package com.up.upfolio.model.api.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeByPasswordRequest {
    @JsonProperty(value = "phoneNumber", required = true)
    @NotBlank
    private String phoneNumber;

    @JsonProperty(value = "password", required = true)
    @NotBlank
    private String password;
}
