package com.channa.jobx_portal.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class JobApplicationDTO {
    private Long id;
    private Long jobId;
    private Long candidateId;
    private LocalDate appliedDate;
}