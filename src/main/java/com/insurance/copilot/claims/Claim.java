package com.insurance.copilot.claims;

import com.insurance.copilot.policy.Policy;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private String incidentType;
    private LocalDate incidentDate;

    @Column(length = 2000)
    private String requiredDocuments;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClaimNumber() { return claimNumber; }
    public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }
    public Policy getPolicy() { return policy; }
    public void setPolicy(Policy policy) { this.policy = policy; }
    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }
    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }
    public String getRequiredDocuments() { return requiredDocuments; }
    public void setRequiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; }
}
