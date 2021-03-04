package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.Enfant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Enfant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Long> {
}
