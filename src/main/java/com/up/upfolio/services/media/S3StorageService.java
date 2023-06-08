package com.up.upfolio.services.media;

import java.io.InputStream;

public interface S3StorageService {
    String uploadPhoto(long size, InputStream photo);
    String getPhotoUrl(String key);
}
