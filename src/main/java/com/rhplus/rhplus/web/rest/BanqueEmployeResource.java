package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.domain.BanqueEmploye;
import com.rhplus.rhplus.service.BanqueEmployeService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.rhplus.rhplus.domain.BanqueEmploye}.
 */
@RestController
@RequestMapping("/api")
public class BanqueEmployeResource {

    private final Logger log = LoggerFactory.getLogger(BanqueEmployeResource.class);

    private static final String ENTITY_NAME = "banqueEmploye";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BanqueEmployeService banqueEmployeService;

    public BanqueEmployeResource(BanqueEmployeService banqueEmployeService) {
        this.banqueEmployeService = banqueEmployeService;
    }

    /**
     * {@code POST  /banque-employes} : Create a new banqueEmploye.
     *
     * @param banqueEmploye the banqueEmploye to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new banqueEmploye, or with status {@code 400 (Bad Request)} if the banqueEmploye has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banque-employes")
    public ResponseEntity<BanqueEmploye> createBanqueEmploye(@Valid @RequestBody BanqueEmploye banqueEmploye) throws URISyntaxException {
        log.debug("REST request to save BanqueEmploye : {}", banqueEmploye);
        if (banqueEmploye.getId() != null) {
            throw new BadRequestAlertException("A new banqueEmploye cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BanqueEmploye result = banqueEmployeService.save(banqueEmploye);
        return ResponseEntity.created(new URI("/api/banque-employes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /banque-employes} : Updates an existing banqueEmploye.
     *
     * @param banqueEmploye the banqueEmploye to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated banqueEmploye,
     * or with status {@code 400 (Bad Request)} if the banqueEmploye is not valid,
     * or with status {@code 500 (Internal Server Error)} if the banqueEmploye couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banque-employes")
    public ResponseEntity<BanqueEmploye> updateBanqueEmploye(@Valid @RequestBody BanqueEmploye banqueEmploye) throws URISyntaxException {
        log.debug("REST request to update BanqueEmploye : {}", banqueEmploye);
        if (banqueEmploye.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BanqueEmploye result = banqueEmployeService.save(banqueEmploye);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, banqueEmploye.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /banque-employes} : get all the banqueEmployes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of banqueEmployes in body.
     */
    @GetMapping("/banque-employes")
    public List<BanqueEmploye> getAllBanqueEmployes(@RequestParam(required = false) String filter) {
        if ("employe-is-null".equals(filter)) {
            log.debug("REST request to get all BanqueEmployes where employe is null");
            return banqueEmployeService.findAllWhereEmployeIsNull();
        }
        log.debug("REST request to get all BanqueEmployes");
        return banqueEmployeService.findAll();
    }

    /**
     * {@code GET  /banque-employes/:id} : get the "id" banqueEmploye.
     *
     * @param id the id of the banqueEmploye to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the banqueEmploye, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banque-employes/{id}")
    public ResponseEntity<BanqueEmploye> getBanqueEmploye(@PathVariable Long id) {
        log.debug("REST request to get BanqueEmploye : {}", id);
        Optional<BanqueEmploye> banqueEmploye = banqueEmployeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(banqueEmploye);
    }

    /**
     * {@code DELETE  /banque-employes/:id} : delete the "id" banqueEmploye.
     *
     * @param id the id of the banqueEmploye to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banque-employes/{id}")
    public ResponseEntity<Void> deleteBanqueEmploye(@PathVariable Long id) {
        log.debug("REST request to delete BanqueEmploye : {}", id);
        banqueEmployeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
