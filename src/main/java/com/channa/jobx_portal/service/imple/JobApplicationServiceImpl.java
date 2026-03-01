package com.channa.jobx_portal.service.imple;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.channa.jobx_portal.dto.JobApplicationDTO;
import com.channa.jobx_portal.entity.Candidate;
import com.channa.jobx_portal.entity.Job;
import com.channa.jobx_portal.entity.JobApplication;
import com.channa.jobx_portal.repositary.CandidateRepository;
import com.channa.jobx_portal.repositary.JobApplicationRepository;
import com.channa.jobx_portal.repositary.JobRepository;
import com.channa.jobx_portal.service.JobApplicationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;


    // APPLY FOR JOB
    @Override
    public JobApplicationDTO applyForJob(Long jobId, Long candidateId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setCandidate(candidate);
        application.setAppliedDate(LocalDate.now());

        JobApplication saved = applicationRepository.save(application);

        return mapToDTO(saved);
    }

    // GET ALL
    @Override
    public List<JobApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET BY JOB
    @Override
    public List<JobApplicationDTO> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET BY CANDIDATE
    @Override
    public List<JobApplicationDTO> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 🔁 Mapper
    private JobApplicationDTO mapToDTO(JobApplication app) {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setId(app.getId());
        dto.setJobId(app.getJob().getId());
        dto.setCandidateId(app.getCandidate().getId());
        dto.setAppliedDate(app.getAppliedDate());
        return dto;
    }
}