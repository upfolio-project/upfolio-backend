package com.up.upfolio.services.media;

import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.services.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PhotoUploaderServiceImpl implements PhotoUploaderService {
    private final ProfileService profileService;
    private final S3StorageService s3StorageService;
    private final PhotoConverterService photoConverterService;

    @Override
    public SuccessResponse updateProfilePhoto(UUID userUuid, InputStream input, long size, int cropX, int cropY, int side) {
        Pair<Long, InputStream> result = photoConverterService.getPng(photoConverterService.loadAndCrop(input, cropX, cropY, side));

        String key = s3StorageService.uploadPhoto(result.getLeft(), result.getRight());

        profileService.updateProfilePhotoKey(userUuid, key);

        return new SuccessResponse();
    }


}
