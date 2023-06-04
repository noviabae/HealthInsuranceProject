package org.example.controller;

import org.example.entity.PolicyEntity;
import org.example.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final PolicyRepository policyRepository;

    @Autowired
    public PolicyController(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyEntity> getPolicyById(@PathVariable("id") Long id) {
        Optional<PolicyEntity> policy = policyRepository.findById(id);
        return policy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<PolicyEntity>> getAllPolicies() {
        List<PolicyEntity> policies = policyRepository.findAll();
        return ResponseEntity.ok(policies);
    }

    @PostMapping("/create")
    public ResponseEntity<PolicyEntity> createPolicy(@RequestBody PolicyEntity policy) {
        PolicyEntity createdPolicy = policyRepository.save(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicy);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PolicyEntity> updatePolicy(@PathVariable("id") Long id, @RequestBody PolicyEntity updatedPolicy) {
        Optional<PolicyEntity> policy = policyRepository.findById(id);
        if (policy.isPresent()) {
            updatedPolicy.setId(id);
            PolicyEntity savedPolicy = policyRepository.save(updatedPolicy);
            return ResponseEntity.ok(savedPolicy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable("id") Long id) {
        Optional<PolicyEntity> policy = policyRepository.findById(id);
        if (policy.isPresent()) {
            policyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
