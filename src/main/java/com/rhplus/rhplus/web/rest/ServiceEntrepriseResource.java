package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.domain.ServiceEntreprise;
import com.rhplus.rhplus.service.ServiceEntrepriseService;
import com.rhplus.rhplus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rhplus.rhplus.domain.ServiceEntreprise}.
 */
@RestController
@RequestMapping("/api")
public class ServiceEntrepriseResource {

    private final Logger log = LoggerFactory.getLogger(ServiceEntrepriseResource.class);

    private static final String ENTITY_NAME = "serviceEntreprise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceEntrepriseService serviceEntrepriseService;

    public ServiceEntrepriseResource(ServiceEntrepriseService serviceEntrepriseService) {
        this.serviceEntrepriseService = serviceEntrepriseService;
    }

    /**
     * {@code POST  /service-entreprises} : Create a new serviceEntreprise.
     *
     * @param serviceEntreprise the serviceEntreprise to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceEntreprise, or with status {@code 400 (Bad Request)} if the serviceEntreprise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-entreprises")
    public ResponseEntity<ServiceEntreprise> createServiceEntreprise(@RequestBody ServiceEntreprise serviceEntreprise) throws URISyntaxException {
        log.debug("REST request to save ServiceEntreprise : {}", serviceEntreprise);
        if (serviceEntreprise.getId() != null) {
            throw new BadRequestAlertException("A new serviceEntreprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceEntreprise result = serviceEntrepriseService.save(serviceEntreprise);
        return ResponseEntity.created(new URI("/api/service-entreprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-entreprises} : Updates an existing serviceEntreprise.
     *
     * @param serviceEntreprise the serviceEntreprise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceEntreprise,
     * or with status {@code 400 (Bad Request)} if the serviceEntreprise is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceEntreprise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-entreprises")
    public ResponseEntity<ServiceEntreprise> updateServiceEntreprise(@RequestBody ServiceEntreprise serviceEntreprise) throws URISyntaxException {
        log.debug("REST request to update ServiceEntreprise : {}", serviceEntreprise);
        if (serviceEntreprise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceEntreprise result = serviceEntrepriseService.save(serviceEntreprise);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, serviceEntreprise.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-entreprises} : get all the serviceEntreprises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceEntreprises in body.
     */
    @GetMapping("/service-entreprises")
    public List<ServiceEntreprise> getAllServiceEntreprises() {
        log.debug("REST request to get all ServiceEntreprises");
        return serviceEntrepriseService.findAll();
    }

    /**
     * {@code GET  /service-entreprises/:id} : get the "id" serviceEntreprise.
     *
     * @param id the id of the serviceEntreprise to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceEntreprise, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-entreprises/{id}")
    public ResponseEntity<ServiceEntreprise> getServiceEntreprise(@PathVariable Long id) {
        log.debug("REST request to get ServiceEntreprise : {}", id);
        Optional<ServiceEntreprise> serviceEntreprise = serviceEntrepriseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceEntreprise);
    }

    /**
     * {@code DELETE  /service-entreprises/:id} : delete the "id" serviceEntreprise.
     *
     * @param id the id of the serviceEntreprise to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-entreprises/{id}")
    public ResponseEntity<Void> deleteServiceEntreprise(@PathVariable Long id) {
        log.debug("REST request to delete ServiceEntreprise : {}", id);
        serviceEntrepriseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
