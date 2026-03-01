package com.channa.jobx_portal.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@Tag(name = "Job APIs", description = "Operations related to Jobs")
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    
    // CREATE JOB
    @Operation(summary = "Create new job")
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO dto) {
        return new ResponseEntity<>(jobService.createJob(dto), HttpStatus.CREATED);
    }

    // GET ALL JOBS
//    @GetMapping
//    public List<JobDTO> getAllJobs() {
//        return jobService.getAllJobs();
//    }
    
    @Operation(summary = "Fetch All Jobs")
    @GetMapping
    public Page<JobDTO> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return jobService.getAllJobs(page, size, sortBy, direction);
    }

    // GET JOB BY ID
    @Operation(summary = "Fetch job by id")
    @GetMapping("/{id}")
    public JobDTO getById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // SEARCH BY LOCATION
    @Operation(summary = "Serch job by location")
    @GetMapping("/search")
    public List<JobDTO> searchByLocation(@RequestParam String location) {
        return jobService.searchJobsByLocation(location);
    }

    // UPDATE JOB
    @Operation(summary = "Update job by id")
    @PutMapping("/{id}")
    public JobDTO update(@PathVariable Long id, @RequestBody JobDTO dto) {
        return jobService.updateJob(id, dto);
    }

    // DELETE JOB
    @Operation(summary = "Delete job by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}