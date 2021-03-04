package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.PosteService;
import com.rhplus.rhplus.domain.Poste;
import com.rhplus.rhplus.repository.PosteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Poste}.
 */
@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private final Logger log = LoggerFactory.getLogger(PosteServiceImpl.class);

    private final PosteRepository posteRepository;

    public PosteServiceImpl(PosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    @Override
    public Poste save(Poste poste) {
        log.debug("Request to save Poste : {}", poste);
        return posteRepository.save(poste);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Poste> findAll() {
        log.debug("Request to get all Postes");
        return posteRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Poste> findOne(Long id) {
        log.debug("Request to get Poste : {}", id);
        return posteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Poste : {}", id);
        posteRepository.deleteById(id);
    }
}
