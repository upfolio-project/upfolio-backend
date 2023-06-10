package com.up.upfolio.model.projects;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

// ProjectModel is a model returned from the server via the API.
// See InputProjectModel for the model obtained from a client
@Getter
@Setter
public class ProjectModel {
    private UUID uuid;
    private UUID authorUuid;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private String title;
    private String description;
    private List<String> tags;
}
