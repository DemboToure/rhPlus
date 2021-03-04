package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.Poste;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Poste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {
}
