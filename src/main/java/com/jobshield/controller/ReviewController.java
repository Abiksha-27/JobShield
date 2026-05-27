package com.jobshield.controller;

import com.jobshield.entity.Review;
import com.jobshield.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @GetMapping("/company/{companyName}")
    public List<Review> getReviewsByCompany(@PathVariable String companyName) {
        return reviewService.getReviewsByCompany(companyName);
    }
}