package com.up.upfolio.services.media;

import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.user.UserType;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.organization.OrganizationService;
import com.up.upfolio.services.profile.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PhotoUploaderServiceImpl implements PhotoUploaderService {
    private final SpecialistService specialistService;
    private final OrganizationService organizationService;
    private final S3StorageService s3StorageService;
    private final PhotoConverterService photoConverterService;
    private final UserRepository userRepository;


    @Override
    public SuccessResponse updateProfilePhoto(UUID userUuid, InputStream input, long size, int cropX, int cropY, int side) {
        UserType type = userRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND)).getUserType();

        Pair<Long, InputStream> result = photoConverterService.getPng(photoConverterService.loadAndCrop(input, cropX, cropY, side));

        String key = s3StorageService.uploadPhoto(result.getLeft(), result.getRight());

        switch (type) {
            case SPECIALIST -> specialistService.updateProfilePhotoKey(userUuid, key);

            case ORGANIZATION -> organizationService.updateProfilePhotoKey(userUuid, key);

            default -> throw new GenericApiErrorException(ErrorDescriptor.WRONG_HANDLER);
        }


        return new SuccessResponse();
    }
}
