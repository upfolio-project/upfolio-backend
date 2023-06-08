package com.up.upfolio.services.media;

import com.up.upfolio.model.api.response.SuccessResponse;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoUploaderService {
    SuccessResponse updateProfilePhoto(UUID userUuid, InputStream input, long size, int cropX, int cropY, int side);
}
