package com.channa.jobx_portal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.channa.jobx_portal.dto.JobDTO;
import com.channa.jobx_portal.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // CREATE JOB
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO dto) {
        return new ResponseEntity<>(jobService.createJob(dto), HttpStatus.CREATED);
    }

    // GET ALL JOBS
    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public JobDTO getById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // SEARCH BY LOCATION
    @GetMapping("/search")
    public List<JobDTO> searchByLocation(@RequestParam String location) {
        return jobService.searchJobsByLocation(location);
    }

    // UPDATE JOB
    @PutMapping("/{id}")
    public JobDTO update(@PathVariable Long id, @RequestBody JobDTO dto) {
        return jobService.updateJob(id, dto);
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}