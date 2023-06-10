package com.up.upfolio.model.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class ChangeUsernameRequest {
    @JsonProperty(value = "newUsername", required = true)
    @Size(min = 3, max = 17, message = "Username must be between 3 and 17 characters long")
    @NotBlank
    private String newUsername;
}
