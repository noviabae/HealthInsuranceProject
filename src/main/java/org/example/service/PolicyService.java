package org.example.service;

import org.example.entity.PolicyEntity;

import java.util.List;
import java.util.Optional;

public interface PolicyService {
    List<PolicyEntity> getAllPolicies();

    Optional<PolicyEntity> getPolicyById(Long id);

    List<PolicyEntity> getPoliciesByUserId(Long userId);

    PolicyEntity createPolicy(PolicyEntity policy);

    PolicyEntity updatePolicy(PolicyEntity policy);

    void deletePolicy(Long id);
}

