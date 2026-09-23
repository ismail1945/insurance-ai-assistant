package com.ismail.insuranceassistant.dto;

import com.ismail.insuranceassistant.domain.ClaimStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClaimResponse(
        Long id,
        String claimNumber,
        String policyNumber,
        String customerId,
        LocalDate incidentDate,
        String description,
        ClaimStatus status,
        BigDecimal estimatedAmount,
        String requiredDocuments,
        String nextSteps
) {}
