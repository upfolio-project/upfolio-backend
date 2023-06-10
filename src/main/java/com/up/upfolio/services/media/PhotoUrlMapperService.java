package com.up.upfolio.services.media;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoUrlMapperService {
    @Value("${upfolio.host:https://upfolio.ru}")
    private String baseHost;

    private final S3StorageService s3StorageService;

    public String mapProfilePhotoUrl(String key) {
        if (key == null || key.length() == 0) {
            return baseHost + "/assets/no-img.png";
        }

        return s3StorageService.getPhotoUrl(key);
    }

    public String getPrivatePhotoPlaceholderUrl() {
        return baseHost + "/assets/private.png";
    }
}
