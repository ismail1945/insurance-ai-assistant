package com.ismail.insuranceassistant.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insurance_policies", indexes = {
        @Index(name = "idx_policy_number", columnList = "policyNumber", unique = true),
        @Index(name = "idx_policy_customer", columnList = "customerId")
})
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PolicyStatus status;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal premium;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal deductible;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String coverageSummary;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exclusions;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public PolicyStatus getStatus() { return status; }
    public void setStatus(PolicyStatus status) { this.status = status; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public BigDecimal getPremium() { return premium; }
    public void setPremium(BigDecimal premium) { this.premium = premium; }
    public BigDecimal getDeductible() { return deductible; }
    public void setDeductible(BigDecimal deductible) { this.deductible = deductible; }
    public String getCoverageSummary() { return coverageSummary; }
    public void setCoverageSummary(String coverageSummary) { this.coverageSummary = coverageSummary; }
    public String getExclusions() { return exclusions; }
    public void setExclusions(String exclusions) { this.exclusions = exclusions; }
}
