package com.up.upfolio.entities;

import com.up.upfolio.model.profile.ProfileStatus;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.model.user.UserRealName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "specialists")
public class SpecialistEntity {
    @Id
    private UUID userUuid;

    private UserRealName realName;

    private LocalDate dateOfBirth;

    private String profilePhotoKey;

    private ProfileType type;

    private OffsetDateTime registered;

    private String location;

    private ProfileStatus status;

    @Column(length = 1000)
    private String bio;

    @ElementCollection
    @CollectionTable(name = "specialist_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    @OneToMany
    private List<ProjectEntity> projects;

    private Boolean verified;

    public boolean isPrivate() {
        return ProfileType.PRIVATE.equals(this.type);
    }
}
