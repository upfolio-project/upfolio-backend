package com.up.upfolio.model.projects;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetProjectsResponse {
    private UUID userUuid;
    private List<ProjectModel> projects;
}
