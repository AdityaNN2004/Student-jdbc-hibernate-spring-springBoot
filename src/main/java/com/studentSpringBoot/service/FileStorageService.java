package com.studentSpringBoot.service;
//package com.studentSpringBoot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor // Automatically injects s3Client and s3Presigner beans
public class FileStorageService {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner; // Injected globally for high performance

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    /**
     * 1. Uploads binary to S3 and returns ONLY the permanent Unique Key string.
     */
    public String storeFile(MultipartFile file) {
        String objectKey = "documents/" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            log.info("Uploading file to private S3 path key: [{}]", objectKey);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, 
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            log.info("File stored securely. Database Key Registered: [{}]", objectKey);
            return objectKey; 

        } catch (IOException e) {
            log.error("Failed to process file binary stream", e);
            throw new RuntimeException("Failed to read file bytes.", e);
        }
    }

    /**
     * 2. Generates a temporary, secure, 15-minute download link using the global presigner bean.
     */
    public String generatePresignedUrl(String objectKey) {
        if (objectKey == null || objectKey.trim().isEmpty()) {
            return null;
        }

        try {
            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(15)) // Expire link after 15 mins
                    .getObjectRequest(b -> b.bucket(bucketName).key(objectKey))
                    .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
            return presignedRequest.url().toString();
        } catch (Exception e) {
            log.error("Failed to generate presigned signature for key [{}]", objectKey, e);
            return null;
        }
    }
}
