package com.channa.jobx_portal.service;

import java.util.List;

import com.channa.jobx_portal.dto.JobDTO;

public interface JobService {

    JobDTO createJob(JobDTO dto);

    List<JobDTO> getAllJobs();

    JobDTO getJobById(Long id);

    List<JobDTO> searchJobsByLocation(String location);

    JobDTO updateJob(Long id, JobDTO dto);

    void deleteJob(Long id);
}
