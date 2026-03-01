package com.channa.jobx_portal.service.imple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.channa.jobx_portal.dto.CandidateDTO;
import com.channa.jobx_portal.entity.Candidate;
import com.channa.jobx_portal.exception.ResourceNotFoundException;
import com.channa.jobx_portal.repositary.CandidateRepository;
import com.channa.jobx_portal.service.CandidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;


    // CREATE
    @Override
    public CandidateDTO createCandidate(CandidateDTO dto) {
        Candidate candidate = mapToEntity(dto);
        Candidate saved = candidateRepository.save(candidate);
        return mapToDTO(saved);
    }

    // GET ALL
    @Override
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET BY ID
    @Override
    public CandidateDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
        return mapToDTO(candidate);
    }

    // UPDATE
    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setSkills(dto.getSkills());

        return mapToDTO(candidateRepository.save(candidate));
    }

    // DELETE
    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    // 🔁 Mapper methods
    private CandidateDTO mapToDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setEmail(candidate.getEmail());
        dto.setSkills(candidate.getSkills());
        return dto;
    }

    private Candidate mapToEntity(CandidateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setId(dto.getId());
        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setSkills(dto.getSkills());
        return candidate;
    }
}