package com.insurance.copilot.claims;

import com.insurance.copilot.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public List<Claim> getAll() {
        return claimRepository.findAll();
    }

    public Claim getById(Long id) {
        return claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found: " + id));
    }

    public String getRequiredDocuments(Long id) {
        return getById(id).getRequiredDocuments();
    }
}
