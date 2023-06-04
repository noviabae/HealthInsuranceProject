package org.example.service;

import org.example.entity.ClaimEntity;

import java.util.List;
import java.util.Optional;

public interface ClaimService {
    List<ClaimEntity> getAllClaims();

    Optional<ClaimEntity> getClaimById(Long id);

    List<ClaimEntity> getClaimsByUserId(Long userId);

    List<ClaimEntity> getClaimsByPolicyId(Long policyId);

    ClaimEntity createClaim(ClaimEntity claim);

    ClaimEntity updateClaim(ClaimEntity claim);

    void deleteClaim(Long id);
}
