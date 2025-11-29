package com.example.report_service.awsLocalStack;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3;

    public S3Controller(S3Service s3) {
        this.s3 = s3;
    }

    @PostMapping("/create/{bucket}")
    public String createBucket(@PathVariable String bucket) {
        s3.createBucket(bucket);
        return "Bucket created: " + bucket;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return s3.uploadFile(file.getOriginalFilename(), file);
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> download(@PathVariable String name) throws IOException {
        byte[] data = s3.downloadFile(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name)
                .body(data);
    }

    @GetMapping("/list")
    public List<String> list() {
        return s3.listFiles();
    }
}
