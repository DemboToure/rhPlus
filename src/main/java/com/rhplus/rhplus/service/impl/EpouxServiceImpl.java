package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.EpouxService;
import com.rhplus.rhplus.domain.Epoux;
import com.rhplus.rhplus.repository.EpouxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Epoux}.
 */
@Service
@Transactional
public class EpouxServiceImpl implements EpouxService {

    private final Logger log = LoggerFactory.getLogger(EpouxServiceImpl.class);

    private final EpouxRepository epouxRepository;

    public EpouxServiceImpl(EpouxRepository epouxRepository) {
        this.epouxRepository = epouxRepository;
    }

    @Override
    public Epoux save(Epoux epoux) {
        log.debug("Request to save Epoux : {}", epoux);
        return epouxRepository.save(epoux);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Epoux> findAll() {
        log.debug("Request to get all Epouxes");
        return epouxRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Epoux> findOne(Long id) {
        log.debug("Request to get Epoux : {}", id);
        return epouxRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Epoux : {}", id);
        epouxRepository.deleteById(id);
    }
}
