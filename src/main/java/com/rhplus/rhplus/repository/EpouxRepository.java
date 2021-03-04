package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.Epoux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Epoux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EpouxRepository extends JpaRepository<Epoux, Long> {
}
