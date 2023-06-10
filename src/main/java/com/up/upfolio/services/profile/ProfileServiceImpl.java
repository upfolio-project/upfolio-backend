package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.profile.InputProfileModel;
import com.up.upfolio.model.profile.ProfileModel;
import com.up.upfolio.model.profile.ProfileStatus;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.repositories.ProfileRepository;
import com.up.upfolio.services.username.UsernameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final ProfileMapper profileMapper;
    private final UsernameService usernameService;

    @Override
    public void createBlankProfile(@NonNull UUID userUuid, UserRealName realName) {
        ProfileEntity profile = new ProfileEntity();

        profile.setBio("");
        profile.setRegistered(OffsetDateTime.now());
        profile.setStatus(ProfileStatus.NOT_LOOKING_FOR_JOB);
        profile.setType(ProfileType.PRIVATE);
        profile.setVerified(false);
        profile.setRealName(realName);
        profile.setUserUuid(userUuid);

        profileRepository.save(profile);
        usernameService.create(userUuid);
    }

    @Override
    public ProfileModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target) {
        ProfileEntity profile = profileRepository.findById(target).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.PROFILE_NOT_FOUND));

        return profileMapper.map(requestedBy, profile);
    }


    @Override
    public ProfileModel editProfile(@NonNull UUID userUuid, InputProfileModel editProfile) {
        ProfileEntity profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_IS_DEACTIVATED));

        profile.setDateOfBirth(editProfile.getDateOfBirth());
        profile.setTags(editProfile.getTags());
        profile.setLocation(editProfile.getLocation());
        profile.setStatus(editProfile.getStatus());
        profile.setBio(editProfile.getBio());
        profile.setType(editProfile.getType());
        profile.setRealName(editProfile.getRealName());

        profileRepository.save(profile);

        return modelMapper.map(profile, ProfileModel.class);
    }

    @Override
    public ProfileEntity getByUuid(UUID uuid) {
        return profileRepository.findById(uuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));
    }

    @Override
    public void attachProject(ProfileEntity profile, ProjectEntity project) {
        profile.getProjects().add(project);
        profileRepository.save(profile);
    }

    @Override
    public void updateProfilePhotoKey(@NonNull UUID userUuid, String key) {
        ProfileEntity profile = getByUuid(userUuid);
        profile.setProfilePhotoKey(key);
        profileRepository.save(profile);
    }
}
