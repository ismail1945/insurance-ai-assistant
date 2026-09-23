package com.insurance.copilot.config;

import com.insurance.copilot.auth.AppUser;
import com.insurance.copilot.auth.AppUserRepository;
import com.insurance.copilot.auth.Role;
import com.insurance.copilot.claims.Claim;
import com.insurance.copilot.claims.ClaimRepository;
import com.insurance.copilot.claims.ClaimStatus;
import com.insurance.copilot.documents.DocumentService;
import com.insurance.copilot.documents.IngestDocumentRequest;
import com.insurance.copilot.policy.Policy;
import com.insurance.copilot.policy.PolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Configuration
public class SeedDataConfig {

    @Bean
    CommandLineRunner seedData(AppUserRepository userRepository,
                               PolicyRepository policyRepository,
                               ClaimRepository claimRepository,
                               DocumentService documentService,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(Role.ROLE_ADMIN, Role.ROLE_AGENT));
                userRepository.save(admin);

                AppUser customer = new AppUser();
                customer.setUsername("customer");
                customer.setPassword(passwordEncoder.encode("customer123"));
                customer.setRoles(Set.of(Role.ROLE_CUSTOMER));
                userRepository.save(customer);
            }

            if (policyRepository.count() == 0) {
                Policy policy = new Policy();
                policy.setPolicyNumber("POL-1001");
                policy.setCustomerName("John Carter");
                policy.setPlanType("Comprehensive Auto");
                policy.setCoverageDetails("Collision, theft, third-party liability up to $1M.");
                policy.setPremiumAmount(new BigDecimal("1450.00"));
                policy.setStartDate(LocalDate.now().minusMonths(8));
                policy.setRenewalDate(LocalDate.now().plusDays(20));
                policyRepository.save(policy);

                Claim claim = new Claim();
                claim.setClaimNumber("CLM-2026-001");
                claim.setPolicy(policy);
                claim.setStatus(ClaimStatus.IN_REVIEW);
                claim.setIncidentType("Rear-end collision");
                claim.setIncidentDate(LocalDate.now().minusDays(7));
                claim.setRequiredDocuments("Driver license, police report, repair estimate, incident photos");
                claimRepository.save(claim);
            }

            if (claimRepository.count() > 0) {
                documentService.ingest(new IngestDocumentRequest(
                        "Claims FAQ",
                        "FAQ",
                        "For collision claims, submit police report and photos. Claim reviews usually complete in 5-10 business days."
                ));
                documentService.ingest(new IngestDocumentRequest(
                        "Policy Coverage Guide",
                        "POLICY",
                        "Comprehensive auto plans include collision damage and theft subject to deductible. Renewal reminder begins 30 days before due date."
                ));
            }
        };
    }
}
