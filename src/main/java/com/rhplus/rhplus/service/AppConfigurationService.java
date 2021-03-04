package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.AppConfiguration;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AppConfiguration}.
 */
public interface AppConfigurationService {

    /**
     * Save a appConfiguration.
     *
     * @param appConfiguration the entity to save.
     * @return the persisted entity.
     */
    AppConfiguration save(AppConfiguration appConfiguration);

    /**
     * Get all the appConfigurations.
     *
     * @return the list of entities.
     */
    List<AppConfiguration> findAll();


    /**
     * Get the "id" appConfiguration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppConfiguration> findOne(Long id);

    /**
     * Delete the "id" appConfiguration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
