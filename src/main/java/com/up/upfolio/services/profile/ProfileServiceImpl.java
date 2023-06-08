package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProfileEntity;
import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.profile.InputProfileModel;
import com.up.upfolio.model.profile.ProfileModel;
import com.up.upfolio.model.profile.ProfileStatus;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProfileServiceImpl implements ProfileService {
    @Value("${upfolio.host:https://upfolio.ru}")
    private String baseHost;

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final ProfileMapper profileMapper;

    @Override
    public void createBlankProfile(UUID userUuid, UserRealName realName) {
        ProfileEntity profile = new ProfileEntity();

        profile.setBio("");
        profile.setRegistered(OffsetDateTime.now());
        profile.setStatus(ProfileStatus.NOT_LOOKING_FOR_JOB);
        profile.setType(ProfileType.PRIVATE);
        profile.setUsername(generateRandomUsername());
        profile.setVerified(false);
        profile.setRealName(realName);
        profile.setUserUuid(userUuid);

        profileRepository.save(profile);
    }

    @Override
    public ProfileModel getProfile(UUID userUuid, String username) {
        ProfileEntity profile = profileRepository.findByUsername(username).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.PROFILE_NOT_FOUND));

        return profileMapper.map(userUuid, profile);
    }

    @Override
    public ProfileModel editProfile(UUID userUuid, InputProfileModel editProfile) {
        ProfileEntity profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_IS_DEACTIVATED));

        profile.setDateOfBirth(editProfile.getDateOfBirth());
        profile.setTags(editProfile.getTags());
        profile.setLocation(editProfile.getLocation());
        profile.setStatus(editProfile.getStatus());
        profile.setBio(editProfile.getBio());
        profile.setType(editProfile.getType());
        profile.setRealName(editProfile.getRealName());
        profile.setUsername(editProfile.getUsername());

        profileRepository.save(profile);

        return modelMapper.map(profile, ProfileModel.class);
    }

    @Override
    public GetMeResponse getMe(UUID userUuid) {
        ProfileEntity profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_IS_DEACTIVATED));

        return new GetMeResponse(profile.getUsername(), baseHost + "/" + profile.getUsername());
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
    public void updateProfilePhotoKey(UUID userUuid, String key) {
        ProfileEntity profile = getByUuid(userUuid);
        profile.setProfilePhotoKey(key);
        profileRepository.save(profile);
    }

    private String generateRandomUsername() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}
