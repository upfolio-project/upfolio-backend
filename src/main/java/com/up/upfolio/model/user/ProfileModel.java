package com.up.upfolio.model.user;

import com.up.upfolio.entities.UserRealName;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;


/*
    ProfileModel contains the fields which are publicly returned through the API
*/
@Getter
@Setter
public class ProfileModel {
    private String username;
    private UserRealName realName;
    private String dateOfBirth;
    private String profilePhotoUrl;
    private ProfileType type;
    private OffsetDateTime registered;
    private ProfileStatus status;
    private String bio;
    private List<String> tags;
    private Boolean verified;
}
