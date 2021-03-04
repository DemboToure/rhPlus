package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.domain.Poste;
import com.rhplus.rhplus.service.PosteService;
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
 * REST controller for managing {@link com.rhplus.rhplus.domain.Poste}.
 */
@RestController
@RequestMapping("/api")
public class PosteResource {

    private final Logger log = LoggerFactory.getLogger(PosteResource.class);

    private static final String ENTITY_NAME = "poste";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PosteService posteService;

    public PosteResource(PosteService posteService) {
        this.posteService = posteService;
    }

    /**
     * {@code POST  /postes} : Create a new poste.
     *
     * @param poste the poste to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new poste, or with status {@code 400 (Bad Request)} if the poste has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/postes")
    public ResponseEntity<Poste> createPoste(@RequestBody Poste poste) throws URISyntaxException {
        log.debug("REST request to save Poste : {}", poste);
        if (poste.getId() != null) {
            throw new BadRequestAlertException("A new poste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Poste result = posteService.save(poste);
        return ResponseEntity.created(new URI("/api/postes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /postes} : Updates an existing poste.
     *
     * @param poste the poste to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated poste,
     * or with status {@code 400 (Bad Request)} if the poste is not valid,
     * or with status {@code 500 (Internal Server Error)} if the poste couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/postes")
    public ResponseEntity<Poste> updatePoste(@RequestBody Poste poste) throws URISyntaxException {
        log.debug("REST request to update Poste : {}", poste);
        if (poste.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Poste result = posteService.save(poste);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, poste.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /postes} : get all the postes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postes in body.
     */
    @GetMapping("/postes")
    public List<Poste> getAllPostes() {
        log.debug("REST request to get all Postes");
        return posteService.findAll();
    }

    /**
     * {@code GET  /postes/:id} : get the "id" poste.
     *
     * @param id the id of the poste to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the poste, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes/{id}")
    public ResponseEntity<Poste> getPoste(@PathVariable Long id) {
        log.debug("REST request to get Poste : {}", id);
        Optional<Poste> poste = posteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(poste);
    }

    /**
     * {@code DELETE  /postes/:id} : delete the "id" poste.
     *
     * @param id the id of the poste to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/postes/{id}")
    public ResponseEntity<Void> deletePoste(@PathVariable Long id) {
        log.debug("REST request to delete Poste : {}", id);
        posteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
