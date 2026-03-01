package com.channa.jobx_portal.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.channa.jobx_portal.dto.CompanyDTO;
import com.channa.jobx_portal.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@Tag(name = "Company APIs", description = "Operations related to Companies")
@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

	 // Constructor Injection (BEST PRACTICE)
    private final CompanyService companyService;

   
    @Operation(summary = "Create new company")
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO dto) {
        return new ResponseEntity<>(companyService.createCompany(dto), HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<CompanyDTO> getAllCompanies() {
//        return companyService.getAllCompanies();
//    }

//    Getting companies with structured set of numbers using page nation.
    @Operation(summary = "Get companys")
    @GetMapping
    public Page<CompanyDTO> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return companyService.getAllCompanies(page, size, sortBy, direction);
    }
    
    
    @Operation(summary = "Get company by id")
    @GetMapping("/{id}")
    public CompanyDTO getById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    
    @Operation(summary = "Update company by id")
    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO dto) {
        return companyService.updateCompany(id, dto);
    }

    
    @Operation(summary = "Delete company by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
