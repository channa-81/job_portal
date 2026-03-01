package com.channa.jobx_portal.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.channa.jobx_portal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}