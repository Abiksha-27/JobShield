package com.jobshield.controller;
import com.jobshield.repository.ScamReportRepository;
import com.jobshield.repository.UserRepository;
import com.jobshield.entity.Company;
import com.jobshield.entity.Review;
import com.jobshield.entity.ScamReport;
import com.jobshield.entity.User;
import com.jobshield.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private ScamReportRepository scamReportRepository;
	  @Autowired
	    private UserRepository userRepository;
	  @Autowired
	    private AdminService adminService;

	    @GetMapping("/users")
	    public List<User> getUsers() {
	        return adminService.getAllUsers();
	    }

	    @GetMapping("/companies")
	    public List<Company> getCompanies() {
	        return adminService.getAllCompanies();
	    }

	    @GetMapping("/reports")
	    public List<ScamReport> getReports() {
	        return adminService.getAllReports();
	    }

	    @DeleteMapping("/company/{id}")
	    public String deleteCompany(@PathVariable Long id) {

	        adminService.deleteCompany(id);

	        return "Company Deleted";
	    }

	    @DeleteMapping("/review/{id}")
	    public String deleteReview(@PathVariable Long id) {

	        adminService.deleteReview(id);

	        return "Review Deleted";
	    }
	    @GetMapping("/scam-reports")
	    public List<ScamReport> getAllScamReports() {
	        return scamReportRepository.findAll();
	    }
	    @DeleteMapping("/scam-report/{id}")
	    public String deleteScamReport(@PathVariable Long id) {

	        scamReportRepository.deleteById(id);

	        return "Scam Report Deleted";
	    }

}
