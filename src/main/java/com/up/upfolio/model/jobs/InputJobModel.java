package com.up.upfolio.model.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InputJobModel {
    @NotBlank
    @JsonProperty(required = true)
    @Size(min = 3, max = 64, message = "Job title must contain from 3 to 64 characters long")
    private String title;

    @NotBlank
    @JsonProperty(required = true)
    @Size(min = 1, max = 2000, message = "Job description must contain from 1 to 2000 characters long")
    private String description;

    @NotNull
    @JsonProperty(required = true)
    private List<String> tags;

    @NotNull
    @JsonProperty(required = true)
    private Boolean open;
}