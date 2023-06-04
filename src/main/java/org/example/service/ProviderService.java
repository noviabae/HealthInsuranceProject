package org.example.service;

import org.example.entity.ProviderEntity;

import java.util.List;
import java.util.Optional;

public interface ProviderService {
    List<ProviderEntity> getAllProviders();

    Optional<ProviderEntity> getProviderById(Long id);

    ProviderEntity createProvider(ProviderEntity provider);

    ProviderEntity updateProvider(ProviderEntity provider);

    void deleteProvider(Long id);
}
