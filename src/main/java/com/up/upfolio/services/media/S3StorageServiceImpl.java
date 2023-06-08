package com.up.upfolio.services.media;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class S3StorageServiceImpl implements S3StorageService {
    private static final long EXPIRATION_OFFSET_MS = 1000L * 3L * 60L * 60L;

    private final AmazonS3 amazonS3;

    public S3StorageServiceImpl(@Value("${upfolio.s3.accessKey}") String accessKey,
                                @Value("${upfolio.s3.secretKey}") String secretKey) {

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net", "ru-central1"
                        )
                )
                .build();
    }

    private Date getExpiration() {
        Date expiration = new Date();
        long expTimeMillis = Instant.now().toEpochMilli();

        expTimeMillis += EXPIRATION_OFFSET_MS;
        expiration.setTime(expTimeMillis);

        return expiration;
    }


    @Override
    public String uploadPhoto(long size, InputStream photo) {
        String key = getPngKey(".png");

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        metadata.setContentLength(size);

        amazonS3.putObject("upfolio", key, photo, metadata);

        return key;
    }

    @Override
    public String getPhotoUrl(String key) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest("upfolio", key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(getExpiration());

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    private String getPngKey(String suffix) {
        return UUID.randomUUID() + suffix;
    }
}
