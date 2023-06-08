package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.model.profile.ProfileModel;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.services.media.S3StorageService;
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

    private final S3StorageService s3StorageService;

    public ProfileModel map(UUID userUuid, ProfileEntity profile) {
        if (ProfileType.PRIVATE.equals(profile.getType()) && !profile.getUserUuid().equals(userUuid))
            return mapPrivate(profile);

        return mapPublic(profile);
    }

    private ProfileModel mapPublic(ProfileEntity profile) {
        ProfileModel mapped = modelMapper.map(profile, ProfileModel.class);
        mapped.setProfilePhotoUrl(mapProfilePhotoUrl(profile));

        return mapped;
    }

    private ProfileModel mapPrivate(ProfileEntity profile) {
        ProfileModel profileModel = new ProfileModel();

        profileModel.setType(profile.getType());
        profileModel.setRegistered(profile.getRegistered());
        profileModel.setUsername(profile.getUsername());
        profileModel.setRealName(profile.getRealName());
        profileModel.setProfilePhotoUrl(baseHost + "/assets/private.png");
        profileModel.setVerified(profile.getVerified());
        profileModel.setUserUuid(profile.getUserUuid());

        return profileModel;
    }

    private String mapProfilePhotoUrl(ProfileEntity profile) {
        String key = profile.getProfilePhotoKey();

        if (key == null || key.length() == 0) {
            return baseHost + "/assets/no-img.png";
        }

        return s3StorageService.getPhotoUrl(key);
    }
}
