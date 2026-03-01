package com.channa.jobx_portal.service.imple;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.channa.jobx_portal.dto.CompanyDTO;
import com.channa.jobx_portal.entity.Company;
import com.channa.jobx_portal.exception.ResourceNotFoundException;
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
    
    
// Getting All Companies at a time with out Page nation features.
//    @Override
//    public List<CompanyDTO> getAllCompanies() {
//        return companyRepository.findAll()
//                .stream()
//                .map(CompanyMapper::toDTO)
//                .toList();
//    }
    
    
// Getting Companies in limit at a time with Page nation features.
    @Override
    public Page<CompanyDTO> getAllCompanies(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return companyRepository.findAll(pageable)
                .map(CompanyMapper::toDTO);
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return CompanyMapper.toDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {
        Company company = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        company.setName(dto.getName());
        company.setLocation(dto.getLocation());

        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}