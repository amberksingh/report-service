package com.example.report_service.awsLocalStack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class S3Service {

    private final S3Client s3Client;

    @Value("${app.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void createBucket(String bucketName) {
        CreateBucketRequest request = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();

        s3Client.createBucket(request);
        //CreateBucketResponse bucket = s3Client.createBucket(request);
        log.info("created bucket : {}", bucketName);
    }

    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        //PutObjectResponse putObjectResponse = s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
        log.info("Uploaded file {} to {} bucket :", fileName, bucketName);
        return "Uploaded: " + fileName;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        ResponseBytes<GetObjectResponse> bytes = s3Client.getObjectAsBytes(request);
        log.info("Downloaded file {} from {} bucket :", fileName, bucketName);
        return bytes.asByteArray();
    }

    public List<String> listFiles() {
        ListObjectsRequest req = ListObjectsRequest.builder()
                .bucket(bucketName)
                .build();
        List<String> listOfFiles = s3Client.listObjects(req)
                .contents()
                .stream()
                .map(S3Object::key)
                .toList();
        log.info("all files in {} bucket : {}", bucketName, listOfFiles);

        return listOfFiles;
    }
}
