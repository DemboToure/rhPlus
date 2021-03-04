package com.rhplus.rhplus.service;

import com.rhplus.rhplus.domain.BanqueEmploye;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link BanqueEmploye}.
 */
public interface BanqueEmployeService {

    /**
     * Save a banqueEmploye.
     *
     * @param banqueEmploye the entity to save.
     * @return the persisted entity.
     */
    BanqueEmploye save(BanqueEmploye banqueEmploye);

    /**
     * Get all the banqueEmployes.
     *
     * @return the list of entities.
     */
    List<BanqueEmploye> findAll();
    /**
     * Get all the BanqueEmployeDTO where Employe is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<BanqueEmploye> findAllWhereEmployeIsNull();


    /**
     * Get the "id" banqueEmploye.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BanqueEmploye> findOne(Long id);

    /**
     * Delete the "id" banqueEmploye.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
