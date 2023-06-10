package com.up.upfolio.services.profile;

import com.up.upfolio.entities.ProjectEntity;
import com.up.upfolio.entities.SpecialistEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.profile.InputSpecialistModel;
import com.up.upfolio.model.profile.ProfileStatus;
import com.up.upfolio.model.profile.ProfileType;
import com.up.upfolio.model.profile.SpecialistModel;
import com.up.upfolio.model.user.UserRealName;
import com.up.upfolio.repositories.ProfileRepository;
import com.up.upfolio.services.username.UsernameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SpecialistServiceImpl implements SpecialistService {
    private final ProfileRepository profileRepository;
    private final SpecialistProfileMapper specialistProfileMapper;
    private final UsernameService usernameService;

    @Override
    public void createBlankProfile(@NonNull UUID userUuid, UserRealName realName) {
        SpecialistEntity profile = new SpecialistEntity();

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
    public SpecialistModel getByUuid(@Nullable UUID requestedBy, @NonNull UUID target) {
        SpecialistEntity profile = profileRepository.findById(target).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND));

        return specialistProfileMapper.map(requestedBy, profile);
    }


    @Override
    public SpecialistModel editProfile(@NonNull UUID userUuid, InputSpecialistModel editProfile) {
        SpecialistEntity profile = profileRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.WRONG_HANDLER));

        profile.setDateOfBirth(editProfile.getDateOfBirth());
        profile.setTags(editProfile.getTags());
        profile.setLocation(editProfile.getLocation());
        profile.setStatus(editProfile.getStatus());
        profile.setBio(editProfile.getBio());
        profile.setType(editProfile.getType());
        profile.setRealName(editProfile.getRealName());

        profile = profileRepository.save(profile);

        return specialistProfileMapper.map(userUuid, profile);
    }

    @Override
    public SpecialistEntity getByUuid(UUID uuid, boolean isSelfAccess) {
        return profileRepository.findById(uuid).orElseThrow(() -> new GenericApiErrorException((isSelfAccess) ? ErrorDescriptor.WRONG_HANDLER : ErrorDescriptor.ACCOUNT_NOT_FOUND));
    }

    @Override
    public void attachProject(SpecialistEntity profile, ProjectEntity project) {
        profile.getProjects().add(project);
        profileRepository.save(profile);
    }

    @Override
    public void updateProfilePhotoKey(@NonNull UUID userUuid, String key) {
        SpecialistEntity profile = getByUuid(userUuid, true);
        profile.setProfilePhotoKey(key);
        profileRepository.save(profile);
    }
}
