package com.channa.jobx_portal.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channa.jobx_portal.dto.CompanyDTO;
import com.channa.jobx_portal.entity.Company;
import com.channa.jobx_portal.mapper.CompanyMapper;
import com.channa.jobx_portal.repositary.CompanyRepository;
import com.channa.jobx_portal.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDTO createCompany(CompanyDTO dto) {
        Company company = CompanyMapper.toEntity(dto);
        Company saved = companyRepository.save(company);
        return CompanyMapper.toDTO(saved);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::toDTO)
                .toList();
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return CompanyMapper.toDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(dto.getName());
        company.setLocation(dto.getLocation());

        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}