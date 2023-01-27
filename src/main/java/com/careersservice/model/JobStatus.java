package com.careersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobStatus {
    private String jobId;
    private String jobName;
    private double javaExperience;
    private double springExperience;
    private String status;

}
