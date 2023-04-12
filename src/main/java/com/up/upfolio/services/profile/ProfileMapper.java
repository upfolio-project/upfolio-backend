package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.model.user.ProfileModel;
import com.up.upfolio.model.user.ProfileType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    private final ModelMapper modelMapper;

    @Value("${upfolio.host:https://upfolio.ru}")
    private String baseHost;

    public ProfileModel map(UUID userUuid, ProfileEntity profile) {
        if (ProfileType.PRIVATE.equals(profile.getType()) && !profile.getUserUuid().equals(userUuid))
            return mapPrivate(profile);

        return mapPublic(profile);
    }

    private ProfileModel mapPublic(ProfileEntity profile) {
        return modelMapper.map(profile, ProfileModel.class);
    }

    private ProfileModel mapPrivate(ProfileEntity profile) {
        ProfileModel profileModel = new ProfileModel();

        profileModel.setType(profile.getType());
        profileModel.setRegistered(profile.getRegistered());
        profileModel.setUsername(profile.getUsername());
        profileModel.setRealName(profile.getRealName());
        profileModel.setProfilePhotoUrl(baseHost + "/assets/private.png");
        profileModel.setVerified(profile.getVerified());

        return profileModel;
    }
}
