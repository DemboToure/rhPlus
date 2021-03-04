package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.Epoux;
import com.rhplus.rhplus.repository.EpouxRepository;
import com.rhplus.rhplus.service.EpouxService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EpouxResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EpouxResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEU_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_NAISSANCE = "BBBBBBBBBB";

    @Autowired
    private EpouxRepository epouxRepository;

    @Autowired
    private EpouxService epouxService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEpouxMockMvc;

    private Epoux epoux;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Epoux createEntity(EntityManager em) {
        Epoux epoux = new Epoux()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .lieuNaissance(DEFAULT_LIEU_NAISSANCE);
        return epoux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Epoux createUpdatedEntity(EntityManager em) {
        Epoux epoux = new Epoux()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE);
        return epoux;
    }

    @BeforeEach
    public void initTest() {
        epoux = createEntity(em);
    }

    @Test
    @Transactional
    public void createEpoux() throws Exception {
        int databaseSizeBeforeCreate = epouxRepository.findAll().size();
        // Create the Epoux
        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isCreated());

        // Validate the Epoux in the database
        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeCreate + 1);
        Epoux testEpoux = epouxList.get(epouxList.size() - 1);
        assertThat(testEpoux.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEpoux.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEpoux.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testEpoux.getLieuNaissance()).isEqualTo(DEFAULT_LIEU_NAISSANCE);
    }

    @Test
    @Transactional
    public void createEpouxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = epouxRepository.findAll().size();

        // Create the Epoux with an existing ID
        epoux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        // Validate the Epoux in the database
        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = epouxRepository.findAll().size();
        // set the field null
        epoux.setPrenom(null);

        // Create the Epoux, which fails.


        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = epouxRepository.findAll().size();
        // set the field null
        epoux.setNom(null);

        // Create the Epoux, which fails.


        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = epouxRepository.findAll().size();
        // set the field null
        epoux.setDateNaissance(null);

        // Create the Epoux, which fails.


        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLieuNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = epouxRepository.findAll().size();
        // set the field null
        epoux.setLieuNaissance(null);

        // Create the Epoux, which fails.


        restEpouxMockMvc.perform(post("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEpouxes() throws Exception {
        // Initialize the database
        epouxRepository.saveAndFlush(epoux);

        // Get all the epouxList
        restEpouxMockMvc.perform(get("/api/epouxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(epoux.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].lieuNaissance").value(hasItem(DEFAULT_LIEU_NAISSANCE)));
    }
    
    @Test
    @Transactional
    public void getEpoux() throws Exception {
        // Initialize the database
        epouxRepository.saveAndFlush(epoux);

        // Get the epoux
        restEpouxMockMvc.perform(get("/api/epouxes/{id}", epoux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(epoux.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.lieuNaissance").value(DEFAULT_LIEU_NAISSANCE));
    }
    @Test
    @Transactional
    public void getNonExistingEpoux() throws Exception {
        // Get the epoux
        restEpouxMockMvc.perform(get("/api/epouxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEpoux() throws Exception {
        // Initialize the database
        epouxService.save(epoux);

        int databaseSizeBeforeUpdate = epouxRepository.findAll().size();

        // Update the epoux
        Epoux updatedEpoux = epouxRepository.findById(epoux.getId()).get();
        // Disconnect from session so that the updates on updatedEpoux are not directly saved in db
        em.detach(updatedEpoux);
        updatedEpoux
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE);

        restEpouxMockMvc.perform(put("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEpoux)))
            .andExpect(status().isOk());

        // Validate the Epoux in the database
        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeUpdate);
        Epoux testEpoux = epouxList.get(epouxList.size() - 1);
        assertThat(testEpoux.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEpoux.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEpoux.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testEpoux.getLieuNaissance()).isEqualTo(UPDATED_LIEU_NAISSANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingEpoux() throws Exception {
        int databaseSizeBeforeUpdate = epouxRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEpouxMockMvc.perform(put("/api/epouxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(epoux)))
            .andExpect(status().isBadRequest());

        // Validate the Epoux in the database
        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEpoux() throws Exception {
        // Initialize the database
        epouxService.save(epoux);

        int databaseSizeBeforeDelete = epouxRepository.findAll().size();

        // Delete the epoux
        restEpouxMockMvc.perform(delete("/api/epouxes/{id}", epoux.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Epoux> epouxList = epouxRepository.findAll();
        assertThat(epouxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
