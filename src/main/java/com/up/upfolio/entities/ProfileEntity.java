package com.up.upfolio.entities;

import com.up.upfolio.model.profile.ProfileStatus;
import com.up.upfolio.model.profile.ProfileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    private UUID userUuid;

    @Column(unique = true)
    @NotBlank
    private String username;

    private UserRealName realName;

    private LocalDate dateOfBirth;

    private String profilePhotoKey;

    private ProfileType type;

    private OffsetDateTime registered;

    private String location;

    private ProfileStatus status;

    private String bio;

    @ElementCollection
    @CollectionTable(name = "profile_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    @OneToMany
    private List<ProjectEntity> projects;

    private Boolean verified;

    public boolean isPrivate() {
        return ProfileType.PRIVATE.equals(this.type);
    }
}
