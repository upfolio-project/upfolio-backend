package com.up.upfolio.entities;

import com.up.upfolio.model.user.OrganizationBasicDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class OrganizationEntity {
    @Id
    private UUID userUuid;

    private String profilePhotoKey;

    private OrganizationBasicDetails details;

    private OffsetDateTime registered;

    private String location;

    @Column(length = 1000)
    private String bio;

    @ElementCollection
    @CollectionTable(name = "organization_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    private Boolean verified;


}
