package com.up.upfolio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    private ProfileEntity author;

    private OffsetDateTime created;
    private OffsetDateTime updated;

    @Size(max = 64)
    private String title;

    @Size(max = 2000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "project_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    private String projectPhotoKey;
}
