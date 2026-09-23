package com.ismail.insuranceassistant.repository;

import com.ismail.insuranceassistant.domain.Claim;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Optional<Claim> findByClaimNumber(String claimNumber);
    List<Claim> findByCustomerId(String customerId);
    List<Claim> findByPolicyNumber(String policyNumber);
}
