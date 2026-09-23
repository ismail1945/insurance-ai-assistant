package com.insurance.copilot.policy;

import com.insurance.copilot.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAll() {
        return policyRepository.findAll();
    }

    public Policy getById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found: " + id));
    }

    public PolicySummaryResponse getSummary(Long id) {
        Policy policy = getById(id);
        return new PolicySummaryResponse(policy.getId(), policy.getPolicyNumber(), policy.getCustomerName(),
                policy.getPlanType(), policy.getCoverageDetails(), policy.getPremiumAmount(), policy.getRenewalDate());
    }

    public List<PolicySummaryResponse> findRenewals(int daysAhead) {
        LocalDate now = LocalDate.now();
        return policyRepository.findByRenewalDateBetween(now, now.plusDays(daysAhead))
                .stream()
                .map(policy -> new PolicySummaryResponse(policy.getId(), policy.getPolicyNumber(), policy.getCustomerName(),
                        policy.getPlanType(), policy.getCoverageDetails(), policy.getPremiumAmount(), policy.getRenewalDate()))
                .toList();
    }
}
