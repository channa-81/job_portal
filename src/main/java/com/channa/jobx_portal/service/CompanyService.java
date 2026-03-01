package com.channa.jobx_portal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.channa.jobx_portal.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO createCompany(CompanyDTO dto);

//    List<CompanyDTO> getAllCompanies();
    

	Page<CompanyDTO> getAllCompanies(int page, int size, String sortBy, String direction);

    CompanyDTO getCompanyById(Long id);

    CompanyDTO updateCompany(Long id, CompanyDTO dto);

    void deleteCompany(Long id);
}