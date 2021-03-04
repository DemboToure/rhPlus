package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.Societe;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Societe}.
 */
public interface SocieteService {

    /**
     * Save a societe.
     *
     * @param societe the entity to save.
     * @return the persisted entity.
     */
    Societe save(Societe societe);

    /**
     * Get all the societes.
     *
     * @return the list of entities.
     */
    List<Societe> findAll();


    /**
     * Get the "id" societe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Societe> findOne(Long id);

    /**
     * Delete the "id" societe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
