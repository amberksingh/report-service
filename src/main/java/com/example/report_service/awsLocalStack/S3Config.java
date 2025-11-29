package com.example.report_service.awsLocalStack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AWS SDK v2
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

// Java
import java.net.URI;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.endpoint}")
    private String s3Endpoint;

    @Value("${cloud.aws.s3.path-style-access-enabled}")
    private boolean pathStyleAccessEnabled;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(s3Endpoint)) // java.net.URI
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .region(Region.of(region))  // software.amazon.awssdk.regions.Region
                .forcePathStyle(pathStyleAccessEnabled)       // required for LocalStack
                .build();
    }
}
