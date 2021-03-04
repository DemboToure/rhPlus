package com.rhplus.rhplus.repository;

import com.rhplus.rhplus.domain.AppConfiguration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AppConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppConfigurationRepository extends JpaRepository<AppConfiguration, Long> {
}
