package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.EnfantService;
import com.rhplus.rhplus.domain.Enfant;
import com.rhplus.rhplus.repository.EnfantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Enfant}.
 */
@Service
@Transactional
public class EnfantServiceImpl implements EnfantService {

    private final Logger log = LoggerFactory.getLogger(EnfantServiceImpl.class);

    private final EnfantRepository enfantRepository;

    public EnfantServiceImpl(EnfantRepository enfantRepository) {
        this.enfantRepository = enfantRepository;
    }

    @Override
    public Enfant save(Enfant enfant) {
        log.debug("Request to save Enfant : {}", enfant);
        return enfantRepository.save(enfant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enfant> findAll() {
        log.debug("Request to get all Enfants");
        return enfantRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Enfant> findOne(Long id) {
        log.debug("Request to get Enfant : {}", id);
        return enfantRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enfant : {}", id);
        enfantRepository.deleteById(id);
    }
}
