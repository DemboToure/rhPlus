package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.ServiceEntreprise;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceEntreprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceEntrepriseRepository extends JpaRepository<ServiceEntreprise, Long> {
}
