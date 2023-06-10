package com.up.upfolio.model.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.model.validators.DateOfBirthConstraint;
import com.up.upfolio.model.validators.NullOrNotBlankConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class InputSpecialistModel {
    @NotNull
    @JsonProperty(required = true)
    private UserRealName realName;

    @NotNull
    @JsonProperty(required = true)
    private ProfileType type;

    @NotNull
    @JsonProperty(required = true)
    private ProfileStatus status;

    @NotNull
    @JsonProperty(required = true)
    @Size(max = 1000)
    private String bio;

    @NotNull
    @JsonProperty(required = true)
    private List<String> tags;

    @NullOrNotBlankConstraint
    @Size(max = 32)
    private String location;

    @DateOfBirthConstraint
    private LocalDate dateOfBirth;
}
