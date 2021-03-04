package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.AppConfigurationService;
import com.rhplus.rhplus.domain.AppConfiguration;
import com.rhplus.rhplus.repository.AppConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AppConfiguration}.
 */
@Service
@Transactional
public class AppConfigurationServiceImpl implements AppConfigurationService {

    private final Logger log = LoggerFactory.getLogger(AppConfigurationServiceImpl.class);

    private final AppConfigurationRepository appConfigurationRepository;

    public AppConfigurationServiceImpl(AppConfigurationRepository appConfigurationRepository) {
        this.appConfigurationRepository = appConfigurationRepository;
    }

    @Override
    public AppConfiguration save(AppConfiguration appConfiguration) {
        log.debug("Request to save AppConfiguration : {}", appConfiguration);
        return appConfigurationRepository.save(appConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppConfiguration> findAll() {
        log.debug("Request to get all AppConfigurations");
        return appConfigurationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AppConfiguration> findOne(Long id) {
        log.debug("Request to get AppConfiguration : {}", id);
        return appConfigurationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppConfiguration : {}", id);
        appConfigurationRepository.deleteById(id);
    }
}
