package com.up.upfolio.model.user;

import com.up.upfolio.entities.UserRealNameModel;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class ProfileModel {
    private String username;
    private UserRealNameModel realName;
    private String dateOfBirth;
    private String profilePhotoUrl;
    private ProfileType type;
    private OffsetDateTime registered;
    private ProfileStatus status;
    private String bio;
    private List<String> tags;
    private Boolean verified;
}
