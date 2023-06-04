package org.example.service.ServiceImplements;

import org.example.entity.ProviderEntity;
import org.example.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderImpl implements ProviderService {
    private List<ProviderEntity> providerList = new ArrayList<>();

    @Override
    public List<ProviderEntity> getAllProviders() {
        return providerList;
    }

    @Override
    public Optional<ProviderEntity> getProviderById(Long id) {
        return providerList.stream()
                .filter(provider -> provider.getId().equals(id))
                .findFirst();
    }

    @Override
    public ProviderEntity createProvider(ProviderEntity provider) {
        Long nextId = getNextProviderId();
        provider.setId(nextId);
        providerList.add(provider);
        return provider;
    }

    @Override
    public ProviderEntity updateProvider(ProviderEntity provider) {
        Optional<ProviderEntity> existingProvider = getProviderById(provider.getId());
        if (existingProvider.isPresent()) {
            ProviderEntity updatedProvider = existingProvider.get();
            updatedProvider.setName(provider.getName());
            updatedProvider.setLocation(provider.getLocation());
            updatedProvider.setSpecialization(provider.getSpecialization());
            updatedProvider.setInsuranceNetwork(provider.getInsuranceNetwork());
            return updatedProvider;
        }
        return null;
    }

    @Override
    public void deleteProvider(Long id) {
        providerList.removeIf(provider -> provider.getId().equals(id));
    }

    private Long getNextProviderId() {
        if (providerList.isEmpty()) {
            return 1L;
        } else {
            Long lastProviderId = providerList.get(providerList.size() - 1).getId();
            return lastProviderId + 1;
        }
    }
}
