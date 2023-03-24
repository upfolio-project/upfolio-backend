package com.up.upfolio.entities;

import com.up.upfolio.model.user.ProfileStatus;
import com.up.upfolio.model.user.ProfileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private UUID userUuid;

    @Column(unique = true)
    @NotBlank
    private String username;

    private UserRealName realName;

    private String dateOfBirth;

    private String profilePhotoUrl;

    private ProfileType type;

    private OffsetDateTime registered;

    private String location;

    private ProfileStatus status;

    private String bio;

    @ElementCollection
    @CollectionTable(name = "profile_tags", joinColumns = @JoinColumn(name = "userUuid"))
    @Column(name = "tag")
    private List<String> tags;

    private Boolean verified;
}
