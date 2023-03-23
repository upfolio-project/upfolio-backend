package com.up.upfolio.services.profile;

import com.up.upfolio.entities.Profile;
import com.up.upfolio.entities.UserRealName;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.user.EditProfileModel;
import com.up.upfolio.model.user.ProfileModel;
import com.up.upfolio.model.user.ProfileStatus;
import com.up.upfolio.model.user.ProfileType;
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

    @Override
    public void createBlankProfile(UUID userUuid, UserRealName realName) {
        Profile profile = new Profile();

        profile.setProfilePhotoUrl(baseHost+"/assets/no-img.png");
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
        Profile profile = profileRepository.findByUsername(username).orElseThrow(() -> new GenericApiErrorException(404, "This profile is not found"));

        return modelMapper.map(profile, ProfileModel.class);
    }

    @Override
    public ProfileModel editProfile(UUID userUuid, EditProfileModel editProfile) {
        Profile profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(403, "Your account is deactivated. Contact support team"));

        profile.setDateOfBirth(editProfile.getDateOfBirth());
        profile.setTags(editProfile.getTags());
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
        Profile profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(403, "Your account is deactivated. Contact support team"));

        return new GetMeResponse(profile.getUsername(), baseHost+"/"+profile.getUsername());
    }

    private String generateRandomUsername() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}
