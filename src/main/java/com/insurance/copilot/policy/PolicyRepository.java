package com.insurance.copilot.policy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByRenewalDateBetween(LocalDate from, LocalDate to);
}
