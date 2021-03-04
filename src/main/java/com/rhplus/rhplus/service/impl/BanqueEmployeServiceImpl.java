package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.BanqueEmployeService;
import com.rhplus.rhplus.domain.BanqueEmploye;
import com.rhplus.rhplus.repository.BanqueEmployeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link BanqueEmploye}.
 */
@Service
@Transactional
public class BanqueEmployeServiceImpl implements BanqueEmployeService {

    private final Logger log = LoggerFactory.getLogger(BanqueEmployeServiceImpl.class);

    private final BanqueEmployeRepository banqueEmployeRepository;

    public BanqueEmployeServiceImpl(BanqueEmployeRepository banqueEmployeRepository) {
        this.banqueEmployeRepository = banqueEmployeRepository;
    }

    @Override
    public BanqueEmploye save(BanqueEmploye banqueEmploye) {
        log.debug("Request to save BanqueEmploye : {}", banqueEmploye);
        return banqueEmployeRepository.save(banqueEmploye);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BanqueEmploye> findAll() {
        log.debug("Request to get all BanqueEmployes");
        return banqueEmployeRepository.findAll();
    }



    /**
     *  Get all the banqueEmployes where Employe is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BanqueEmploye> findAllWhereEmployeIsNull() {
        log.debug("Request to get all banqueEmployes where Employe is null");
        return StreamSupport
            .stream(banqueEmployeRepository.findAll().spliterator(), false)
            .filter(banqueEmploye -> banqueEmploye.getEmploye() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BanqueEmploye> findOne(Long id) {
        log.debug("Request to get BanqueEmploye : {}", id);
        return banqueEmployeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BanqueEmploye : {}", id);
        banqueEmployeRepository.deleteById(id);
    }
}
