package com.up.upfolio.services.organization;

import com.up.upfolio.entities.JobEntity;
import com.up.upfolio.entities.OrganizationEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.profile.OrganizationModel;
import com.up.upfolio.model.profile.InputOrganizationModel;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import com.up.upfolio.repositories.OrganizationRepository;
import com.up.upfolio.services.media.PhotoUrlMapperService;
import com.up.upfolio.services.username.UsernameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Component
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;
    private final PhotoUrlMapperService photoUrlMapperService;
    private final UsernameService usernameService;

    @Override
    public void createBlankOrganization(UUID userUuid, OrganizationBasicDetails basicDetails) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setRegistered(OffsetDateTime.now());
        entity.setUserUuid(userUuid);
        entity.setDetails(basicDetails);
        entity.setBio("");

        usernameService.create(userUuid);
        organizationRepository.save(entity);
    }

    @Override
    public OrganizationModel getByUuid(@NonNull UUID target, boolean selfAccess) {
        return map(getEntityByUuid(target, selfAccess));
    }

    @Override
    public OrganizationEntity getEntityByUuid(@NonNull UUID target, boolean selfAccess) {
        return organizationRepository.findById(target).orElseThrow(() -> new GenericApiErrorException((selfAccess) ? ErrorDescriptor.WRONG_HANDLER : ErrorDescriptor.ACCOUNT_NOT_FOUND));
    }

    @Override
    public OrganizationModel editProfile(@NonNull UUID userUuid, InputOrganizationModel inputOrganizationModel) {
        OrganizationEntity org = organizationRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.WRONG_HANDLER));

        org.setDetails(inputOrganizationModel.getDetails());
        org.setTags(inputOrganizationModel.getTags());
        org.setBio(inputOrganizationModel.getBio());
        org.setLocation(inputOrganizationModel.getLocation());

        org = organizationRepository.save(org);

        return map(org);
    }

    @Override
    public void attachJob(OrganizationEntity org, JobEntity job) {
        org.getJobs().add(job);
        organizationRepository.save(org);
    }

    @Override
    public void updateProfilePhotoKey(@NonNull UUID requestedBy, String key) {
        OrganizationEntity org = organizationRepository.findById(requestedBy).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.WRONG_HANDLER));
        org.setProfilePhotoKey(key);

        organizationRepository.save(org);
    }

    private OrganizationModel map(OrganizationEntity entity) {
        OrganizationModel mapped = modelMapper.map(entity, OrganizationModel.class);
        mapped.setProfilePhotoUrl(photoUrlMapperService.mapProfilePhotoUrl(entity.getProfilePhotoKey()));

        return mapped;
    }
}
