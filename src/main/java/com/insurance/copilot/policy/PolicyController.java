package com.insurance.copilot.policy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@Validated
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<Policy> getPolicies() {
        return policyService.getAll();
    }

    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable Long id) {
        return policyService.getById(id);
    }

    @GetMapping("/{id}/summary")
    public PolicySummaryResponse getSummary(@PathVariable Long id) {
        return policyService.getSummary(id);
    }

    @GetMapping("/renewals")
    public List<PolicySummaryResponse> getRenewalReminders(@RequestParam(defaultValue = "30") @Min(1) @Max(365) int days) {
        return policyService.findRenewals(days);
    }
}
