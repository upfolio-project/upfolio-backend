package com.up.upfolio.services.organization;

import com.up.upfolio.entities.OrganizationEntity;
import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.organization.OrganizationModel;
import com.up.upfolio.model.user.OrganizationBasicDetails;
import com.up.upfolio.repositories.OrganizationRepository;
import com.up.upfolio.services.media.PhotoUrlMapperService;
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

    @Override
    public void createBlankOrganization(UUID userUuid, OrganizationBasicDetails basicDetails) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setRegistered(OffsetDateTime.now());
        entity.setUserUuid(userUuid);
        entity.setDetails(basicDetails);

        organizationRepository.save(entity);
    }

    @Override
    public OrganizationModel getByUuid(UUID requestedBy, @NonNull UUID target) {
        return map(organizationRepository.findById(target).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND)));
    }

    private OrganizationModel map(OrganizationEntity entity) {
        OrganizationModel mapped = modelMapper.map(entity, OrganizationModel.class);
        mapped.setProfilePhotoUrl(photoUrlMapperService.mapProfilePhotoUrl(entity.getProfilePhotoKey()));

        return mapped;
    }
}
