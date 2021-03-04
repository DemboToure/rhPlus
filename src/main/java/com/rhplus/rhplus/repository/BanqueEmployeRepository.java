package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.BanqueEmploye;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BanqueEmploye entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BanqueEmployeRepository extends JpaRepository<BanqueEmploye, Long> {
}
