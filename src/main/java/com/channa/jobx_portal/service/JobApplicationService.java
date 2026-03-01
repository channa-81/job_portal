package com.channa.jobx_portal.service;

import java.util.List;

import com.channa.jobx_portal.dto.JobApplicationDTO;

public interface JobApplicationService {

    JobApplicationDTO applyForJob(Long jobId, Long candidateId);

    List<JobApplicationDTO> getAllApplications();

    List<JobApplicationDTO> getApplicationsByJob(Long jobId);

    List<JobApplicationDTO> getApplicationsByCandidate(Long candidateId);
}