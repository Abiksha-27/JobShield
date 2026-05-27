package com.jobshield.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String website;
    private String email;
    private String phone;
    private String status;
    private Integer trustScore;
   
    private Integer totalReviews;
    private Integer totalScamReports = 0;
    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getTrustScore() {
        return trustScore;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTrustScore(int trustScore) {
        this.trustScore = trustScore;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	
	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public void setTrustScore(Integer trustScore) {
		this.trustScore = trustScore;
	}

	
	public void setTotalScamReports(Integer totalScamReports) {
		this.totalScamReports = totalScamReports;
	}

	public Integer getTotalScamReports() {
		return totalScamReports;
	}
}