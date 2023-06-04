package org.example.controller;

import org.example.entity.ProviderEntity;
import org.example.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderEntity> getProviderById(@PathVariable("id") Long id) {
        ProviderEntity provider = providerRepository.findById(id).orElse(null);
        if (provider != null) {
            return ResponseEntity.ok(provider);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProviderEntity>> searchProviders(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String insuranceNetwork
    ) {
        List<ProviderEntity> providers = providerRepository.findAll();

        if (location != null) {
            providers = providers.stream()
                    .filter(provider -> provider.getLocation().equalsIgnoreCase(location))
                    .collect(Collectors.toList());
        }

        if (specialization != null) {
            providers = providers.stream()
                    .filter(provider -> provider.getSpecialization().equalsIgnoreCase(specialization))
                    .collect(Collectors.toList());
        }

        if (insuranceNetwork != null) {
            providers = providers.stream()
                    .filter(provider -> provider.getInsuranceNetwork().equalsIgnoreCase(insuranceNetwork))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(providers);
    }

    @PostMapping("/create")
    public ResponseEntity<ProviderEntity> createProvider(@RequestBody ProviderEntity provider) {
        ProviderEntity createdProvider = providerRepository.save(provider);
        return ResponseEntity.ok(createdProvider);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProviderEntity> updateProvider(@PathVariable("id") Long id, @RequestBody ProviderEntity updatedProvider) {
        ProviderEntity existingProvider = providerRepository.findById(id).orElse(null);
        if (existingProvider != null) {
            updatedProvider.setId(id);
            ProviderEntity savedProvider = providerRepository.save(updatedProvider);
            return ResponseEntity.ok(savedProvider);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable("id") Long id) {
        ProviderEntity provider = providerRepository.findById(id).orElse(null);
        if (provider != null) {
            providerRepository.delete(provider);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
