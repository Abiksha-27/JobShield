package com.jobshield.controller;

import com.jobshield.entity.ScamReport;
import com.jobshield.repository.ScamReportRepository;
import com.jobshield.service.ScamReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scam-report")
@CrossOrigin(origins = "*")
public class ScamReportController {
    @Autowired
    private ScamReportService scamReportService;
    @Autowired
    private ScamReportRepository scamReportRepository;

    @PostMapping
    public ScamReport reportScam(
            @RequestBody ScamReport report) {

        return scamReportService.reportScam(report);
    }
    @GetMapping("/company/{companyName}")
    public List<ScamReport> getByCompany(
            @PathVariable String companyName) {

        return scamReportRepository
                .findByCompanyName(companyName);
    }

}
