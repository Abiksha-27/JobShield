package com.jobshield.service;

import com.jobshield.entity.Company;
import com.jobshield.entity.Review;
import com.jobshield.repository.CompanyRepository;
import com.jobshield.repository.ReviewRepository;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Review addReview(Review review) {

        Review savedReview = reviewRepository.save(review);

        Optional<Company> optionalCompany =
                companyRepository.findByCompanyNameIgnoreCase(
                        review.getCompanyName()
                );

        if (!optionalCompany.isPresent()) {

            Company newCompany = new Company();

            newCompany.setCompanyName(review.getCompanyName());
            newCompany.setEmail("Not Available");
            newCompany.setPhone("Not Available");
            newCompany.setWebsite("Not Available");
            newCompany.setTrustScore(100);
            newCompany.setStatus("TRUSTED");
            newCompany.setTotalScamReports(0);

            companyRepository.save(newCompany);
        }

        return savedReview;
    }

    public List<Review> getReviewsByCompany(String companyName) {

        return reviewRepository.findByCompanyNameIgnoreCase(companyName);
    }
}