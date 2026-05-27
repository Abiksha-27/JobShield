package com.jobshield.service;

import com.jobshield.entity.Company;
import com.jobshield.entity.ScamReport;
import com.jobshield.repository.CompanyRepository;
import com.jobshield.repository.ScamReportRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScamReportService {

    @Autowired
    private ScamReportRepository scamReportRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public ScamReport reportScam(ScamReport report) {

        ScamReport savedReport =
                scamReportRepository.save(report);

        Optional<Company> optionalCompany =
                companyRepository.findByCompanyNameIgnoreCase(
                        report.getCompanyName()
                );

        Company company;

        if (optionalCompany.isPresent()) {

            company = optionalCompany.get();

        } else {

            company = new Company();

            company.setCompanyName(report.getCompanyName());
            company.setEmail("Not Available");
            company.setPhone("Not Available");
            company.setWebsite("Not Available");
            company.setTrustScore(100);
            company.setStatus("TRUSTED");
            company.setTotalScamReports(0);

            companyRepository.save(company);
        }

        int reportCount =
                scamReportRepository.countByCompanyNameIgnoreCase(
                        report.getCompanyName()
                );

        company.setTotalScamReports(reportCount);

        int newScore = 100 - (reportCount * 10);

        if (newScore < 0) {
            newScore = 0;
        }

        company.setTrustScore(newScore);

        if (reportCount >= 8) {

            company.setStatus("SCAM_REPORTED");

        } else if (reportCount >= 5) {

            company.setStatus("SUSPICIOUS");

        } else {

            company.setStatus("TRUSTED");
        }

        companyRepository.save(company);

        return savedReport;
    }
}