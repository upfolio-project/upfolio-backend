package com.up.upfolio.services.profile;

import com.up.upfolio.entities.SpecialistEntity;
import com.up.upfolio.model.profile.SpecialistModel;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.services.media.PhotoUrlMapperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SpecialistProfileMapper {
    private final ModelMapper modelMapper;
    private final PhotoUrlMapperService photoUrlMapperService;

    public SpecialistModel map(UUID requestedBy, SpecialistEntity profile) {
        if (ProfileType.PRIVATE.equals(profile.getType()) && !profile.getUserUuid().equals(requestedBy))
            return mapPrivate(profile);

        return mapPublic(profile);
    }

    private SpecialistModel mapPublic(SpecialistEntity profile) {
        SpecialistModel mapped = modelMapper.map(profile, SpecialistModel.class);
        mapped.setProfilePhotoUrl(mapProfilePhotoUrl(profile));

        return mapped;
    }

    private SpecialistModel mapPrivate(SpecialistEntity profile) {
        SpecialistModel specialistModel = new SpecialistModel();

        specialistModel.setType(profile.getType());
        specialistModel.setRegistered(profile.getRegistered());
        specialistModel.setRealName(profile.getRealName());
        specialistModel.setProfilePhotoUrl(photoUrlMapperService.getPrivatePhotoPlaceholderUrl());
        specialistModel.setVerified(profile.getVerified());
        specialistModel.setUserUuid(profile.getUserUuid());

        return specialistModel;
    }

    private String mapProfilePhotoUrl(SpecialistEntity profile) {
        return photoUrlMapperService.mapProfilePhotoUrl(profile.getProfilePhotoKey());
    }
}
