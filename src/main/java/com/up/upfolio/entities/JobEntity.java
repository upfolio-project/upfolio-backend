package com.up.upfolio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class JobEntity {
    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    private OrganizationEntity author;

    private OffsetDateTime created;
    private OffsetDateTime updated;

    @Size(max = 64)
    private String title;

    @Size(max = 2000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "job_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    @Column(nullable = false)
    private Boolean open;
}
