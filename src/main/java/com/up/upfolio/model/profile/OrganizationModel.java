package com.up.upfolio.model.profile;

import com.up.upfolio.model.user.OrganizationBasicDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Schema(name = "Organization")
public class OrganizationModel {
    private UUID userUuid;
    private String profilePhotoUrl;
    private Boolean verified;
    private OffsetDateTime registered;
    private OrganizationBasicDetails details;
    private String bio;
    private String location;
}
