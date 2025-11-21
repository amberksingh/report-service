package com.example.report_service.utility;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class ProfileManager {

    @Autowired
    Environment environment;

    @PostConstruct
    public void profileDetails() {
        log.info("PostConstruct called for report-service...");
        String[] activeProfiles = environment.getActiveProfiles();
        log.info("current report-service profile : {}", Arrays.toString(activeProfiles));
    }
}
