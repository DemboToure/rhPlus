package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.BanqueService;
import com.rhplus.rhplus.domain.Banque;
import com.rhplus.rhplus.repository.BanqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Banque}.
 */
@Service
@Transactional
public class BanqueServiceImpl implements BanqueService {

    private final Logger log = LoggerFactory.getLogger(BanqueServiceImpl.class);

    private final BanqueRepository banqueRepository;

    public BanqueServiceImpl(BanqueRepository banqueRepository) {
        this.banqueRepository = banqueRepository;
    }

    @Override
    public Banque save(Banque banque) {
        log.debug("Request to save Banque : {}", banque);
        return banqueRepository.save(banque);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Banque> findAll() {
        log.debug("Request to get all Banques");
        return banqueRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Banque> findOne(Long id) {
        log.debug("Request to get Banque : {}", id);
        return banqueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Banque : {}", id);
        banqueRepository.deleteById(id);
    }
}
