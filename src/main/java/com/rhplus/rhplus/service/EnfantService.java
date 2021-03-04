package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.Enfant;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Enfant}.
 */
public interface EnfantService {

    /**
     * Save a enfant.
     *
     * @param enfant the entity to save.
     * @return the persisted entity.
     */
    Enfant save(Enfant enfant);

    /**
     * Get all the enfants.
     *
     * @return the list of entities.
     */
    List<Enfant> findAll();


    /**
     * Get the "id" enfant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Enfant> findOne(Long id);

    /**
     * Delete the "id" enfant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
