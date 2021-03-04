package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.domain.Enfant;
import com.rhplus.rhplus.service.EnfantService;
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
 * REST controller for managing {@link com.rhplus.rhplus.domain.Enfant}.
 */
@RestController
@RequestMapping("/api")
public class EnfantResource {

    private final Logger log = LoggerFactory.getLogger(EnfantResource.class);

    private static final String ENTITY_NAME = "enfant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnfantService enfantService;

    public EnfantResource(EnfantService enfantService) {
        this.enfantService = enfantService;
    }

    /**
     * {@code POST  /enfants} : Create a new enfant.
     *
     * @param enfant the enfant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enfant, or with status {@code 400 (Bad Request)} if the enfant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enfants")
    public ResponseEntity<Enfant> createEnfant(@Valid @RequestBody Enfant enfant) throws URISyntaxException {
        log.debug("REST request to save Enfant : {}", enfant);
        if (enfant.getId() != null) {
            throw new BadRequestAlertException("A new enfant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Enfant result = enfantService.save(enfant);
        return ResponseEntity.created(new URI("/api/enfants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enfants} : Updates an existing enfant.
     *
     * @param enfant the enfant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enfant,
     * or with status {@code 400 (Bad Request)} if the enfant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enfant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enfants")
    public ResponseEntity<Enfant> updateEnfant(@Valid @RequestBody Enfant enfant) throws URISyntaxException {
        log.debug("REST request to update Enfant : {}", enfant);
        if (enfant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Enfant result = enfantService.save(enfant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, enfant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enfants} : get all the enfants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enfants in body.
     */
    @GetMapping("/enfants")
    public List<Enfant> getAllEnfants() {
        log.debug("REST request to get all Enfants");
        return enfantService.findAll();
    }

    /**
     * {@code GET  /enfants/:id} : get the "id" enfant.
     *
     * @param id the id of the enfant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enfant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enfants/{id}")
    public ResponseEntity<Enfant> getEnfant(@PathVariable Long id) {
        log.debug("REST request to get Enfant : {}", id);
        Optional<Enfant> enfant = enfantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enfant);
    }

    /**
     * {@code DELETE  /enfants/:id} : delete the "id" enfant.
     *
     * @param id the id of the enfant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enfants/{id}")
    public ResponseEntity<Void> deleteEnfant(@PathVariable Long id) {
        log.debug("REST request to delete Enfant : {}", id);
        enfantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
