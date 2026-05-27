package com.jobshield.controller;

import com.jobshield.entity.Company;
import com.jobshield.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public Company addCompany(
            @RequestBody Company company) {

        return companyService.addCompany(company);
    }

    @GetMapping("/search")
    public List<Company> searchCompany(
            @RequestParam String name) {

        return companyService.searchCompany(name);
    }

    @GetMapping("/all")
    public List<Company> getAllCompanies() {

        return companyService.getAllCompanies();
    }
}
   
