package com.up.upfolio.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.entities.UserRealName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditProfileModel {
    @NotBlank
    @JsonProperty(required = true)
    @Size(min = 3, max = 17, message = "Username must be between 3 and 17 characters long")
    private String username;

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
    private String bio;

    @NotNull
    @JsonProperty(required = true)
    private List<String> tags;

    @NotEmpty
    private String dateOfBirth;
}
