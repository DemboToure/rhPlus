package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.ServiceEntreprise;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ServiceEntreprise}.
 */
public interface ServiceEntrepriseService {

    /**
     * Save a serviceEntreprise.
     *
     * @param serviceEntreprise the entity to save.
     * @return the persisted entity.
     */
    ServiceEntreprise save(ServiceEntreprise serviceEntreprise);

    /**
     * Get all the serviceEntreprises.
     *
     * @return the list of entities.
     */
    List<ServiceEntreprise> findAll();


    /**
     * Get the "id" serviceEntreprise.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceEntreprise> findOne(Long id);

    /**
     * Delete the "id" serviceEntreprise.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
