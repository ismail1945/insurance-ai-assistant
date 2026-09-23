package com.insurance.copilot.claims;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public List<Claim> getClaims() {
        return claimService.getAll();
    }

    @GetMapping("/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return claimService.getById(id);
    }

    @GetMapping("/{id}/status")
    public Map<String, String> getStatus(@PathVariable Long id) {
        Claim claim = claimService.getById(id);
        return Map.of("claimNumber", claim.getClaimNumber(), "status", claim.getStatus().name());
    }

    @GetMapping("/{id}/required-documents")
    public Map<String, String> getRequiredDocuments(@PathVariable Long id) {
        return Map.of("requiredDocuments", claimService.getRequiredDocuments(id));
    }
}
