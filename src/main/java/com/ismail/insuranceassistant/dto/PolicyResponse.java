package com.ismail.insuranceassistant.dto;

import com.ismail.insuranceassistant.domain.PolicyStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyResponse(
        Long id,
        String policyNumber,
        String customerId,
        String type,
        PolicyStatus status,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal premium,
        BigDecimal deductible,
        String coverageSummary,
        String exclusions
) {}
