package com.up.upfolio.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.up.upfolio.entities.UserRealName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;


/*
    ProfileModel contains the fields which are publicly returned via the API
*/
@Getter
@Setter
@Schema(name = "Profile")
public class ProfileModel {
    // public info
    private String username;
    private UserRealName realName;
    private OffsetDateTime registered;
    private ProfileType type;
    private String profilePhotoUrl;
    private Boolean verified;

    // private info (not returned if ProfileType == PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dateOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfileStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tags;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;
}
