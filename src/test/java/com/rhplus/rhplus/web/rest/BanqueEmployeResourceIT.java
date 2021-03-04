package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.BanqueEmploye;
import com.rhplus.rhplus.repository.BanqueEmployeRepository;
import com.rhplus.rhplus.service.BanqueEmployeService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BanqueEmployeResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BanqueEmployeResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_COMPTE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_BANQUE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BANQUE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_GUICHET = "AAAAAAAAAA";
    private static final String UPDATED_CODE_GUICHET = "BBBBBBBBBB";

    private static final String DEFAULT_CLE_RIB = "AAAAAAAAAA";
    private static final String UPDATED_CLE_RIB = "BBBBBBBBBB";

    private static final String DEFAULT_PDF_DOMICILIATION = "AAAAAAAAAA";
    private static final String UPDATED_PDF_DOMICILIATION = "BBBBBBBBBB";

    @Autowired
    private BanqueEmployeRepository banqueEmployeRepository;

    @Autowired
    private BanqueEmployeService banqueEmployeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBanqueEmployeMockMvc;

    private BanqueEmploye banqueEmploye;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BanqueEmploye createEntity(EntityManager em) {
        BanqueEmploye banqueEmploye = new BanqueEmploye()
            .nom(DEFAULT_NOM)
            .numeroCompte(DEFAULT_NUMERO_COMPTE)
            .codeBanque(DEFAULT_CODE_BANQUE)
            .codeGuichet(DEFAULT_CODE_GUICHET)
            .cleRib(DEFAULT_CLE_RIB)
            .pdfDomiciliation(DEFAULT_PDF_DOMICILIATION);
        return banqueEmploye;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BanqueEmploye createUpdatedEntity(EntityManager em) {
        BanqueEmploye banqueEmploye = new BanqueEmploye()
            .nom(UPDATED_NOM)
            .numeroCompte(UPDATED_NUMERO_COMPTE)
            .codeBanque(UPDATED_CODE_BANQUE)
            .codeGuichet(UPDATED_CODE_GUICHET)
            .cleRib(UPDATED_CLE_RIB)
            .pdfDomiciliation(UPDATED_PDF_DOMICILIATION);
        return banqueEmploye;
    }

    @BeforeEach
    public void initTest() {
        banqueEmploye = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanqueEmploye() throws Exception {
        int databaseSizeBeforeCreate = banqueEmployeRepository.findAll().size();
        // Create the BanqueEmploye
        restBanqueEmployeMockMvc.perform(post("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isCreated());

        // Validate the BanqueEmploye in the database
        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeCreate + 1);
        BanqueEmploye testBanqueEmploye = banqueEmployeList.get(banqueEmployeList.size() - 1);
        assertThat(testBanqueEmploye.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBanqueEmploye.getNumeroCompte()).isEqualTo(DEFAULT_NUMERO_COMPTE);
        assertThat(testBanqueEmploye.getCodeBanque()).isEqualTo(DEFAULT_CODE_BANQUE);
        assertThat(testBanqueEmploye.getCodeGuichet()).isEqualTo(DEFAULT_CODE_GUICHET);
        assertThat(testBanqueEmploye.getCleRib()).isEqualTo(DEFAULT_CLE_RIB);
        assertThat(testBanqueEmploye.getPdfDomiciliation()).isEqualTo(DEFAULT_PDF_DOMICILIATION);
    }

    @Test
    @Transactional
    public void createBanqueEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = banqueEmployeRepository.findAll().size();

        // Create the BanqueEmploye with an existing ID
        banqueEmploye.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBanqueEmployeMockMvc.perform(post("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isBadRequest());

        // Validate the BanqueEmploye in the database
        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueEmployeRepository.findAll().size();
        // set the field null
        banqueEmploye.setNom(null);

        // Create the BanqueEmploye, which fails.


        restBanqueEmployeMockMvc.perform(post("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isBadRequest());

        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroCompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueEmployeRepository.findAll().size();
        // set the field null
        banqueEmploye.setNumeroCompte(null);

        // Create the BanqueEmploye, which fails.


        restBanqueEmployeMockMvc.perform(post("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isBadRequest());

        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeBanqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueEmployeRepository.findAll().size();
        // set the field null
        banqueEmploye.setCodeBanque(null);

        // Create the BanqueEmploye, which fails.


        restBanqueEmployeMockMvc.perform(post("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isBadRequest());

        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBanqueEmployes() throws Exception {
        // Initialize the database
        banqueEmployeRepository.saveAndFlush(banqueEmploye);

        // Get all the banqueEmployeList
        restBanqueEmployeMockMvc.perform(get("/api/banque-employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banqueEmploye.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].numeroCompte").value(hasItem(DEFAULT_NUMERO_COMPTE)))
            .andExpect(jsonPath("$.[*].codeBanque").value(hasItem(DEFAULT_CODE_BANQUE)))
            .andExpect(jsonPath("$.[*].codeGuichet").value(hasItem(DEFAULT_CODE_GUICHET)))
            .andExpect(jsonPath("$.[*].cleRib").value(hasItem(DEFAULT_CLE_RIB)))
            .andExpect(jsonPath("$.[*].pdfDomiciliation").value(hasItem(DEFAULT_PDF_DOMICILIATION)));
    }
    
    @Test
    @Transactional
    public void getBanqueEmploye() throws Exception {
        // Initialize the database
        banqueEmployeRepository.saveAndFlush(banqueEmploye);

        // Get the banqueEmploye
        restBanqueEmployeMockMvc.perform(get("/api/banque-employes/{id}", banqueEmploye.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(banqueEmploye.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.numeroCompte").value(DEFAULT_NUMERO_COMPTE))
            .andExpect(jsonPath("$.codeBanque").value(DEFAULT_CODE_BANQUE))
            .andExpect(jsonPath("$.codeGuichet").value(DEFAULT_CODE_GUICHET))
            .andExpect(jsonPath("$.cleRib").value(DEFAULT_CLE_RIB))
            .andExpect(jsonPath("$.pdfDomiciliation").value(DEFAULT_PDF_DOMICILIATION));
    }
    @Test
    @Transactional
    public void getNonExistingBanqueEmploye() throws Exception {
        // Get the banqueEmploye
        restBanqueEmployeMockMvc.perform(get("/api/banque-employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanqueEmploye() throws Exception {
        // Initialize the database
        banqueEmployeService.save(banqueEmploye);

        int databaseSizeBeforeUpdate = banqueEmployeRepository.findAll().size();

        // Update the banqueEmploye
        BanqueEmploye updatedBanqueEmploye = banqueEmployeRepository.findById(banqueEmploye.getId()).get();
        // Disconnect from session so that the updates on updatedBanqueEmploye are not directly saved in db
        em.detach(updatedBanqueEmploye);
        updatedBanqueEmploye
            .nom(UPDATED_NOM)
            .numeroCompte(UPDATED_NUMERO_COMPTE)
            .codeBanque(UPDATED_CODE_BANQUE)
            .codeGuichet(UPDATED_CODE_GUICHET)
            .cleRib(UPDATED_CLE_RIB)
            .pdfDomiciliation(UPDATED_PDF_DOMICILIATION);

        restBanqueEmployeMockMvc.perform(put("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBanqueEmploye)))
            .andExpect(status().isOk());

        // Validate the BanqueEmploye in the database
        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeUpdate);
        BanqueEmploye testBanqueEmploye = banqueEmployeList.get(banqueEmployeList.size() - 1);
        assertThat(testBanqueEmploye.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testBanqueEmploye.getNumeroCompte()).isEqualTo(UPDATED_NUMERO_COMPTE);
        assertThat(testBanqueEmploye.getCodeBanque()).isEqualTo(UPDATED_CODE_BANQUE);
        assertThat(testBanqueEmploye.getCodeGuichet()).isEqualTo(UPDATED_CODE_GUICHET);
        assertThat(testBanqueEmploye.getCleRib()).isEqualTo(UPDATED_CLE_RIB);
        assertThat(testBanqueEmploye.getPdfDomiciliation()).isEqualTo(UPDATED_PDF_DOMICILIATION);
    }

    @Test
    @Transactional
    public void updateNonExistingBanqueEmploye() throws Exception {
        int databaseSizeBeforeUpdate = banqueEmployeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanqueEmployeMockMvc.perform(put("/api/banque-employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banqueEmploye)))
            .andExpect(status().isBadRequest());

        // Validate the BanqueEmploye in the database
        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBanqueEmploye() throws Exception {
        // Initialize the database
        banqueEmployeService.save(banqueEmploye);

        int databaseSizeBeforeDelete = banqueEmployeRepository.findAll().size();

        // Delete the banqueEmploye
        restBanqueEmployeMockMvc.perform(delete("/api/banque-employes/{id}", banqueEmploye.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BanqueEmploye> banqueEmployeList = banqueEmployeRepository.findAll();
        assertThat(banqueEmployeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
