package com.channa.jobx_portal.mapper;

import com.channa.jobx_portal.dto.CompanyDTO;
import com.channa.jobx_portal.entity.Company;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setLocation(company.getLocation());
        return dto;
    }

    public static Company toEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setLocation(dto.getLocation());
        return company;
    }
}
