package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.SocieteService;
import com.rhplus.rhplus.domain.Societe;
import com.rhplus.rhplus.repository.SocieteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Societe}.
 */
@Service
@Transactional
public class SocieteServiceImpl implements SocieteService {

    private final Logger log = LoggerFactory.getLogger(SocieteServiceImpl.class);

    private final SocieteRepository societeRepository;

    public SocieteServiceImpl(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    @Override
    public Societe save(Societe societe) {
        log.debug("Request to save Societe : {}", societe);
        return societeRepository.save(societe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Societe> findAll() {
        log.debug("Request to get all Societes");
        return societeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Societe> findOne(Long id) {
        log.debug("Request to get Societe : {}", id);
        return societeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Societe : {}", id);
        societeRepository.deleteById(id);
    }
}
