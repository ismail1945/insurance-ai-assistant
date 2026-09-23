package com.ismail.insuranceassistant.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_claims", indexes = {
        @Index(name = "idx_claim_number", columnList = "claimNumber", unique = true),
        @Index(name = "idx_claim_customer", columnList = "customerId"),
        @Index(name = "idx_claim_policy", columnList = "policyNumber")
})
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @Column(nullable = false)
    private String policyNumber;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private LocalDate incidentDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal estimatedAmount;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String requiredDocuments;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nextSteps;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClaimNumber() { return claimNumber; }
    public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    public BigDecimal getEstimatedAmount() { return estimatedAmount; }
    public void setEstimatedAmount(BigDecimal estimatedAmount) { this.estimatedAmount = estimatedAmount; }
    public String getRequiredDocuments() { return requiredDocuments; }
    public void setRequiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; }
    public String getNextSteps() { return nextSteps; }
    public void setNextSteps(String nextSteps) { this.nextSteps = nextSteps; }
}
