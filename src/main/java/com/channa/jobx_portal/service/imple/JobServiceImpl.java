package com.channa.jobx_portal.service.imple;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.channa.jobx_portal.dto.JobDTO;
import com.channa.jobx_portal.entity.Company;
import com.channa.jobx_portal.entity.Job;
import com.channa.jobx_portal.exception.ResourceNotFoundException;
import com.channa.jobx_portal.repositary.CompanyRepository;
import com.channa.jobx_portal.repositary.JobRepository;
import com.channa.jobx_portal.service.JobService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;


    // CREATE JOB
    @Override
    public JobDTO createJob(JobDTO dto) {

        // Fetch company from DB
        Company company = companyRepository.findById(dto.getCompanyId())
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setCompany(company);

        Job saved = jobRepository.save(job);

        return mapToDTO(saved);
    }

    // GET ALL JOBS
//    @Override
//    public List<JobDTO> getAllJobs() {
//        return jobRepository.findAll()
//                .stream()
//                .map(this::mapToDTO)
//                .toList();
//    }
    
    @Override
    public Page<JobDTO> getAllJobs(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return jobRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    // GET JOB BY ID
    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return mapToDTO(job);
    }

    // SEARCH BY LOCATION (CUSTOM QUERY)
    @Override
    public List<JobDTO> searchJobsByLocation(String location) {
        return jobRepository.findByLocation(location)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // UPDATE JOB
    @Override
    public JobDTO updateJob(Long id, JobDTO dto) {

        Job job = jobRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());

        // update company if changed
        if (dto.getCompanyId() != null) {
            Company company = companyRepository.findById(dto.getCompanyId())
            		.orElseThrow(() -> new ResourceNotFoundException("Company not found"));
            job.setCompany(company);
        }

        return mapToDTO(jobRepository.save(job));
    }

    // DELETE JOB
    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    // 🔁 Mapper method
    private JobDTO mapToDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setCompanyId(job.getCompany().getId());
        return dto;
    }
}