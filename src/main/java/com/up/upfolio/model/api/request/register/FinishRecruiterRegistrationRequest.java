package com.up.upfolio.model.api.request.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.validators.CommonValidationConfig;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinishRecruiterRegistrationRequest {
    @JsonProperty(value = "registerToken", required = true)
    @NotBlank
    private String registerToken;

    @JsonProperty(value = "legalEntityName", required = true)
    @Size(min = 1, max = CommonValidationConfig.MAX_FIELD_LENGTH)
    @NotBlank
    private String legalEntityName;

    @JsonProperty(value = "organizationName", required = true)
    @Size(min = 1, max = CommonValidationConfig.MAX_FIELD_LENGTH)
    @NotBlank
    private String organizationName;

    @JsonProperty(value = "password", required = true)
    @NotBlank
    private String password;
}
