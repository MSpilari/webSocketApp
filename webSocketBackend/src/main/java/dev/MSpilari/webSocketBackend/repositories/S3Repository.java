package dev.MSpilari.webSocketBackend.repositories;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Repository
public class S3Repository {

    private String bucketName;
    private S3Client s3Client;

    public S3Repository(S3Client s3CLient, @Value("${spring.cloud.aws.s3.bucket-name}") String bucketName) {
        this.bucketName = bucketName;
        this.s3Client = s3CLient;
    }

    public void uploadImage(String key, MultipartFile image) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(image.getContentType())
                .contentLength(image.getSize())
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(image.getInputStream(), image.getSize()));

    }

    public String getImageUrl(String key) {
        return s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(key).build())
                .toString();
    }
}
