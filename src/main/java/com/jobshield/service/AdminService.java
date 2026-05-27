package com.jobshield.service;
import com.jobshield.entity.Company;
import com.jobshield.entity.Review;
import com.jobshield.entity.ScamReport;
import com.jobshield.entity.User;
import com.jobshield.repository.CompanyRepository;
import com.jobshield.repository.ReviewRepository;
import com.jobshield.repository.ScamReportRepository;
import com.jobshield.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private CompanyRepository companyRepository;

	    @Autowired
	    private ScamReportRepository scamReportRepository;

	    @Autowired
	    private ReviewRepository reviewRepository;

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public List<Company> getAllCompanies() {
	        return companyRepository.findAll();
	    }

	    public List<ScamReport> getAllReports() {
	        return scamReportRepository.findAll();
	    }

	    public void deleteCompany(Long id) {
	        companyRepository.deleteById(id);
	    }

	    public void deleteReview(Long id) {
	        reviewRepository.deleteById(id);
	    }

}
