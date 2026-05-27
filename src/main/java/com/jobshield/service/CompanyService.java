package com.jobshield.service;
import com.jobshield.entity.Company;
import com.jobshield.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        company.setTrustScore(100);
        company.setStatus("TRUSTED");
        return companyRepository.save(company);
    }

    public List<Company> searchCompany(String name) {
        return companyRepository.findByCompanyNameContainingIgnoreCase(name);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}
