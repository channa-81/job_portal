package com.channa.jobx_portal.service;

import java.util.List;

import com.channa.jobx_portal.dto.CandidateDTO;

public interface CandidateService {

    CandidateDTO createCandidate(CandidateDTO dto);

    List<CandidateDTO> getAllCandidates();

    CandidateDTO getCandidateById(Long id);

    CandidateDTO updateCandidate(Long id, CandidateDTO dto);

    void deleteCandidate(Long id);
}