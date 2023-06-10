package com.up.upfolio.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.validators.CommonValidationConfig;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Embeddable
public class OrganizationBasicDetails implements Serializable {
    @JsonProperty(required = true)
    @NotBlank(message = "Organization name must not be empty")
    @Size(min = 1, max = CommonValidationConfig.MAX_FIELD_LENGTH)
    private String organizationName;

    @JsonProperty(required = true)
    @NotBlank(message = "Legal entity name must not be empty")
    @Size(min = 1, max = CommonValidationConfig.MAX_FIELD_LENGTH)
    private String legalEntityName;

    public OrganizationBasicDetails() {
    }
}
