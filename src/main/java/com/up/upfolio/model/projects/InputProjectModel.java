package com.up.upfolio.model.projects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class InputProjectModel {
    @NotBlank
    @JsonProperty(required = true)
    @Size(min = 3, max = 64, message = "Project name must contain from 3 to 64 characters long")
    private String title;

    @NotBlank
    @JsonProperty(required = true)
    @Size(min = 1, max = 2000, message = "Project description must contain from 1 to 2000 characters long")
    private String description;

    @NotNull
    @JsonProperty(required = true)
    private List<String> tags;
}
