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
import org.springframework.web.bind.annotation.RestController;

import com.channa.jobx_portal.dto.CandidateDTO;
import com.channa.jobx_portal.service.CandidateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;


    // CREATE
    @PostMapping
    public ResponseEntity<CandidateDTO> create(@Valid @RequestBody CandidateDTO dto) {
        return new ResponseEntity<>(candidateService.createCandidate(dto), HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public List<CandidateDTO> getAll() {
        return candidateService.getAllCandidates();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CandidateDTO getById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CandidateDTO update(@PathVariable Long id, @Valid @RequestBody CandidateDTO dto) {
        return candidateService.updateCandidate(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
}