package org.example.service.ServiceImplements;

import org.example.entity.PolicyEntity;
import org.example.service.PolicyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolicyImpl implements PolicyService {
    private List<PolicyEntity> policyList = new ArrayList<>();

    @Override
    public List<PolicyEntity> getAllPolicies() {
        return policyList;
    }

    @Override
    public Optional<PolicyEntity> getPolicyById(Long id) {
        return policyList.stream()
                .filter(policy -> policy.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<PolicyEntity> getPoliciesByUserId(Long userId) {
        return policyList.stream()
                .filter(policy -> policy.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public PolicyEntity createPolicy(PolicyEntity policy) {
        Long nextId = getNextPolicyId();
        policy.setId(nextId);
        policyList.add(policy);
        return policy;
    }

    @Override
    public PolicyEntity updatePolicy(PolicyEntity policy) {
        Optional<PolicyEntity> existingPolicy = getPolicyById(policy.getId());
        if (existingPolicy.isPresent()) {
            PolicyEntity updatedPolicy = existingPolicy.get();
            updatedPolicy.setUserId(policy.getUserId());
            updatedPolicy.setPolicyNumber(policy.getPolicyNumber());
            updatedPolicy.setPolicyType(policy.getPolicyType());
            updatedPolicy.setStartDate(policy.getStartDate());
            updatedPolicy.setEndDate(policy.getEndDate());
            return updatedPolicy;
        }
        return null;
    }

    @Override
    public void deletePolicy(Long id) {
        policyList.removeIf(policy -> policy.getId().equals(id));
    }

    private Long getNextPolicyId() {
        if (policyList.isEmpty()) {
            return 1L;
        } else {
            Long lastPolicyId = policyList.get(policyList.size() - 1).getId();
            return lastPolicyId + 1;
        }
    }
}
