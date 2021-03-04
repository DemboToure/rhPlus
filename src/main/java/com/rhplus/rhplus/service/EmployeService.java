package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.Employe;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Employe}.
 */
public interface EmployeService {

    /**
     * Save a employe.
     *
     * @param employe the entity to save.
     * @return the persisted entity.
     */
    Employe save(Employe employe);

    /**
     * Get all the employes.
     *
     * @return the list of entities.
     */
    List<Employe> findAll();


    /**
     * Get the "id" employe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Employe> findOne(Long id);

    /**
     * Delete the "id" employe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
