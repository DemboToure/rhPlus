package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.domain.Epoux;
import com.rhplus.rhplus.service.EpouxService;
import com.rhplus.rhplus.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rhplus.rhplus.domain.Epoux}.
 */
@RestController
@RequestMapping("/api")
public class EpouxResource {

    private final Logger log = LoggerFactory.getLogger(EpouxResource.class);

    private static final String ENTITY_NAME = "epoux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EpouxService epouxService;

    public EpouxResource(EpouxService epouxService) {
        this.epouxService = epouxService;
    }

    /**
     * {@code POST  /epouxes} : Create a new epoux.
     *
     * @param epoux the epoux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new epoux, or with status {@code 400 (Bad Request)} if the epoux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/epouxes")
    public ResponseEntity<Epoux> createEpoux(@Valid @RequestBody Epoux epoux) throws URISyntaxException {
        log.debug("REST request to save Epoux : {}", epoux);
        if (epoux.getId() != null) {
            throw new BadRequestAlertException("A new epoux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Epoux result = epouxService.save(epoux);
        return ResponseEntity.created(new URI("/api/epouxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /epouxes} : Updates an existing epoux.
     *
     * @param epoux the epoux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated epoux,
     * or with status {@code 400 (Bad Request)} if the epoux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the epoux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/epouxes")
    public ResponseEntity<Epoux> updateEpoux(@Valid @RequestBody Epoux epoux) throws URISyntaxException {
        log.debug("REST request to update Epoux : {}", epoux);
        if (epoux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Epoux result = epouxService.save(epoux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, epoux.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /epouxes} : get all the epouxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of epouxes in body.
     */
    @GetMapping("/epouxes")
    public List<Epoux> getAllEpouxes() {
        log.debug("REST request to get all Epouxes");
        return epouxService.findAll();
    }

    /**
     * {@code GET  /epouxes/:id} : get the "id" epoux.
     *
     * @param id the id of the epoux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the epoux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/epouxes/{id}")
    public ResponseEntity<Epoux> getEpoux(@PathVariable Long id) {
        log.debug("REST request to get Epoux : {}", id);
        Optional<Epoux> epoux = epouxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(epoux);
    }

    /**
     * {@code DELETE  /epouxes/:id} : delete the "id" epoux.
     *
     * @param id the id of the epoux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/epouxes/{id}")
    public ResponseEntity<Void> deleteEpoux(@PathVariable Long id) {
        log.debug("REST request to delete Epoux : {}", id);
        epouxService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
