package com.channa.jobx_portal.service;

import java.util.List;

import com.channa.jobx_portal.dto.CompanyDTO;

public interface CompanyService {

    CompanyDTO createCompany(CompanyDTO dto);

    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(Long id);

    CompanyDTO updateCompany(Long id, CompanyDTO dto);

    void deleteCompany(Long id);
}