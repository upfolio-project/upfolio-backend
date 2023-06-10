package com.up.upfolio.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import com.up.upfolio.model.validators.NullOrNotBlankConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class InputOrganizationModel {
    @NullOrNotBlankConstraint
    @Size(max = 32)
    private String location;

    @NotNull
    @JsonProperty(required = true)
    @Size(max = 1000)
    private String bio;

    @NotNull
    @JsonProperty(required = true)
    private OrganizationBasicDetails details;

    @NotNull
    @JsonProperty(required = true)
    private List<String> tags;
}
