package com.channa.jobx_portal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.channa.jobx_portal.dto.JobApplicationDTO;
import com.channa.jobx_portal.service.JobApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService applicationService;


    // APPLY FOR JOB
    @PostMapping("/jobs/{jobId}/apply/{candidateId}")
    public ResponseEntity<JobApplicationDTO> apply(@PathVariable Long jobId,
                                                   @PathVariable Long candidateId) {
        return new ResponseEntity<>(
                applicationService.applyForJob(jobId, candidateId),
                HttpStatus.CREATED
        );
    }

    // GET ALL APPLICATIONS
    @GetMapping("/applications")
    public List<JobApplicationDTO> getAll() {
        return applicationService.getAllApplications();
    }

    // GET BY JOB
    @GetMapping("/applications/job/{jobId}")
    public List<JobApplicationDTO> getByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    // GET BY CANDIDATE
    @GetMapping("/applications/candidate/{candidateId}")
    public List<JobApplicationDTO> getByCandidate(@PathVariable Long candidateId) {
        return applicationService.getApplicationsByCandidate(candidateId);
    }
}