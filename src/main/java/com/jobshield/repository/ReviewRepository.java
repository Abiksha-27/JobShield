package com.jobshield.repository;

import com.jobshield.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCompanyNameIgnoreCase(String companyName);

    long countByCompanyNameIgnoreCase(String companyName);
}