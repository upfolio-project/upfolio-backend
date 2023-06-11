package com.up.upfolio.model.jobs;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JobModel {
    private UUID uuid;
    private UUID authorUuid;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private String title;
    private String description;
    private List<String> tags;
    private Boolean open;
}
