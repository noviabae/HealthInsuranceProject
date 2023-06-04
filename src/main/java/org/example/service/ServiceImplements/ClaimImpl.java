package org.example.service.ServiceImplements;

import org.example.entity.ClaimEntity;
import org.example.service.ClaimService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimImpl implements ClaimService {
    private List<ClaimEntity> claimList = new ArrayList<>();

    @Override
    public List<ClaimEntity> getAllClaims() {
        return claimList;
    }

    @Override
    public Optional<ClaimEntity> getClaimById(Long id) {
        return claimList.stream()
                .filter(claim -> claim.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ClaimEntity> getClaimsByUserId(Long userId) {
        return claimList.stream()
                .filter(claim -> claim.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimEntity> getClaimsByPolicyId(Long policyId) {
        return claimList.stream()
                .filter(claim -> claim.getPolicyId().equals(policyId))
                .collect(Collectors.toList());
    }

    @Override
    public ClaimEntity createClaim(ClaimEntity claim) {
        Long nextId = getNextClaimId();
        claim.setId(nextId);
        claimList.add(claim);
        return claim;
    }

    @Override
    public ClaimEntity updateClaim(ClaimEntity claim) {
        Optional<ClaimEntity> existingClaim = getClaimById(claim.getId());
        if (existingClaim.isPresent()) {
            ClaimEntity updatedClaim = existingClaim.get();
            updatedClaim.setUserId(claim.getUserId());
            updatedClaim.setPolicyId(claim.getPolicyId());
            updatedClaim.setClaimNumber(claim.getClaimNumber());
            updatedClaim.setClaimStatus(claim.getClaimStatus());
            updatedClaim.setClaimDate(claim.getClaimDate());
            return updatedClaim;
        }
        return null;
    }

    @Override
    public void deleteClaim(Long id) {
        claimList.removeIf(claim -> claim.getId().equals(id));
    }

    private Long getNextClaimId() {
        if (claimList.isEmpty()) {
            return 1L;
        } else {
            Long lastClaimId = claimList.get(claimList.size() - 1).getId();
            return lastClaimId + 1;
        }
    }
}
