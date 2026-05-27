package com.jobshield.entity;
import com.jobshield.entity.ScamReport;
import jakarta.persistence.*;

@Entity
@Table(name = "scam_reports")
public class ScamReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String reportedBy;
    private String fakeHrName;
    private boolean paymentRequested;
    private String proofFileName;
    private String fakeHrEmail;
    private String fakeHrPhone;
    private String chatPlatform;
    private String paymentAmount;

    @Column(length = 1000)
    private String description;

    public Long getId() { return id; }
    public String getCompanyName() { return companyName; }
    public String getReportedBy() { return reportedBy; }
    public String getFakeHrName() { return fakeHrName; }
    public boolean isPaymentRequested() { return paymentRequested; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setReportedBy(String reportedBy) { this.reportedBy = reportedBy; }
    public void setFakeHrName(String fakeHrName) { this.fakeHrName = fakeHrName; }
    public void setPaymentRequested(boolean paymentRequested) { this.paymentRequested = paymentRequested; }
    public void setDescription(String description) { this.description = description; }
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public String getFakeHrEmail() {
		return fakeHrEmail;
	}
	public void setFakeHrEmail(String fakeHrEmail) {
		this.fakeHrEmail = fakeHrEmail;
	}
	public String getFakeHrPhone() {
		return fakeHrPhone;
	}
	public void setFakeHrPhone(String fakeHrPhone) {
		this.fakeHrPhone = fakeHrPhone;
	}
	public String getChatPlatform() {
		return chatPlatform;
	}
	public void setChatPlatform(String chatPlatform) {
		this.chatPlatform = chatPlatform;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
}