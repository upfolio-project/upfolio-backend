package com.up.upfolio.model.api.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterByPhoneNumberRequest {
    @JsonProperty(value = "registerToken", required = true)
    @NotBlank
    private String registerToken;

    @JsonProperty(value = "phoneNumber", required = true)
    @NotBlank
    private String phoneNumber;
}
