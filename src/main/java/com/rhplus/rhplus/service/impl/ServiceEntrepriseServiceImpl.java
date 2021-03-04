package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.ServiceEntrepriseService;
import com.rhplus.rhplus.domain.ServiceEntreprise;
import com.rhplus.rhplus.repository.ServiceEntrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ServiceEntreprise}.
 */
@Service
@Transactional
public class ServiceEntrepriseServiceImpl implements ServiceEntrepriseService {

    private final Logger log = LoggerFactory.getLogger(ServiceEntrepriseServiceImpl.class);

    private final ServiceEntrepriseRepository serviceEntrepriseRepository;

    public ServiceEntrepriseServiceImpl(ServiceEntrepriseRepository serviceEntrepriseRepository) {
        this.serviceEntrepriseRepository = serviceEntrepriseRepository;
    }

    @Override
    public ServiceEntreprise save(ServiceEntreprise serviceEntreprise) {
        log.debug("Request to save ServiceEntreprise : {}", serviceEntreprise);
        return serviceEntrepriseRepository.save(serviceEntreprise);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceEntreprise> findAll() {
        log.debug("Request to get all ServiceEntreprises");
        return serviceEntrepriseRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceEntreprise> findOne(Long id) {
        log.debug("Request to get ServiceEntreprise : {}", id);
        return serviceEntrepriseRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceEntreprise : {}", id);
        serviceEntrepriseRepository.deleteById(id);
    }
}
