package com.insurance.copilot.policy;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicySummaryResponse(Long id, String policyNumber, String customerName, String planType,
                                    String coverageDetails, BigDecimal premiumAmount, LocalDate renewalDate) {
}
