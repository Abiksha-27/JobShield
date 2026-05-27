package com.jobshield.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobshield.entity.ScamReport;

public interface ScamReportRepository
        extends JpaRepository<ScamReport, Long> {

    List<ScamReport> findByCompanyName(String companyName);

    int countByCompanyNameIgnoreCase(String companyName);
}  