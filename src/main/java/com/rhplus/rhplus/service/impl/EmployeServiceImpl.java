package com.rhplus.rhplus.service.impl;

import com.rhplus.rhplus.service.EmployeService;
import com.rhplus.rhplus.domain.Employe;
import com.rhplus.rhplus.repository.EmployeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Employe}.
 */
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe save(Employe employe) {
        log.debug("Request to save Employe : {}", employe);
        return employeRepository.save(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> findAll() {
        log.debug("Request to get all Employes");
        return employeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Employe> findOne(Long id) {
        log.debug("Request to get Employe : {}", id);
        return employeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employe : {}", id);
        employeRepository.deleteById(id);
    }
}
