package com.ismail.insuranceassistant.dto;

import com.ismail.insuranceassistant.domain.ClaimStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClaimRequest(
        @NotBlank String claimNumber,
        @NotBlank String policyNumber,
        @NotBlank String customerId,
        @NotNull LocalDate incidentDate,
        @NotBlank String description,
        @NotNull ClaimStatus status,
        @NotNull @DecimalMin("0.0") BigDecimal estimatedAmount,
        @NotBlank String requiredDocuments,
        @NotBlank String nextSteps
) {}
