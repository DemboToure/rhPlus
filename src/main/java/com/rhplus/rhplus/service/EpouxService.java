package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.Epoux;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Epoux}.
 */
public interface EpouxService {

    /**
     * Save a epoux.
     *
     * @param epoux the entity to save.
     * @return the persisted entity.
     */
    Epoux save(Epoux epoux);

    /**
     * Get all the epouxes.
     *
     * @return the list of entities.
     */
    List<Epoux> findAll();


    /**
     * Get the "id" epoux.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Epoux> findOne(Long id);

    /**
     * Delete the "id" epoux.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
