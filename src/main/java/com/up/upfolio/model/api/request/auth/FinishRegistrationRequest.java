package com.up.upfolio.model.api.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinishRegistrationRequest {
    @JsonProperty(value = "registerToken", required = true)
    @NotBlank
    private String registerToken;

    @JsonProperty(value = "firstName", required = true)
    @NotBlank
    private String firstName;

    @JsonProperty(value = "lastName", required = true)
    @NotBlank
    private String lastName;

    @JsonProperty(value = "password", required = true)
    @NotBlank
    private String password;
}
