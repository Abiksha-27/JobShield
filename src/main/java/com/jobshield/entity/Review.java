package com.jobshield.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String reviewerName;

    private int rating;

    private String reviewText;
    
    private Integer totalScamReports = 0;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

	public Integer getTotalScamReports() {
		return totalScamReports;
	}

	public void setTotalScamReports(Integer totalScamReports) {
		this.totalScamReports = totalScamReports;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
