package com.ismail.insuranceassistant.dto;

import com.ismail.insuranceassistant.domain.PolicyStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyRequest(
        @NotBlank String policyNumber,
        @NotBlank String customerId,
        @NotBlank String type,
        @NotNull PolicyStatus status,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull @DecimalMin("0.0") BigDecimal premium,
        @NotNull @DecimalMin("0.0") BigDecimal deductible,
        @NotBlank String coverageSummary,
        @NotBlank String exclusions
) {}
