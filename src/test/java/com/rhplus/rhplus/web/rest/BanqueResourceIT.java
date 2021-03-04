package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.Banque;
import com.rhplus.rhplus.repository.BanqueRepository;
import com.rhplus.rhplus.service.BanqueService;

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
 * Integration tests for the {@link BanqueResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BanqueResourceIT {

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

    @Autowired
    private BanqueRepository banqueRepository;

    @Autowired
    private BanqueService banqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBanqueMockMvc;

    private Banque banque;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banque createEntity(EntityManager em) {
        Banque banque = new Banque()
            .nom(DEFAULT_NOM)
            .numeroCompte(DEFAULT_NUMERO_COMPTE)
            .codeBanque(DEFAULT_CODE_BANQUE)
            .codeGuichet(DEFAULT_CODE_GUICHET)
            .cleRib(DEFAULT_CLE_RIB);
        return banque;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banque createUpdatedEntity(EntityManager em) {
        Banque banque = new Banque()
            .nom(UPDATED_NOM)
            .numeroCompte(UPDATED_NUMERO_COMPTE)
            .codeBanque(UPDATED_CODE_BANQUE)
            .codeGuichet(UPDATED_CODE_GUICHET)
            .cleRib(UPDATED_CLE_RIB);
        return banque;
    }

    @BeforeEach
    public void initTest() {
        banque = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanque() throws Exception {
        int databaseSizeBeforeCreate = banqueRepository.findAll().size();
        // Create the Banque
        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isCreated());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeCreate + 1);
        Banque testBanque = banqueList.get(banqueList.size() - 1);
        assertThat(testBanque.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBanque.getNumeroCompte()).isEqualTo(DEFAULT_NUMERO_COMPTE);
        assertThat(testBanque.getCodeBanque()).isEqualTo(DEFAULT_CODE_BANQUE);
        assertThat(testBanque.getCodeGuichet()).isEqualTo(DEFAULT_CODE_GUICHET);
        assertThat(testBanque.getCleRib()).isEqualTo(DEFAULT_CLE_RIB);
    }

    @Test
    @Transactional
    public void createBanqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = banqueRepository.findAll().size();

        // Create the Banque with an existing ID
        banque.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isBadRequest());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueRepository.findAll().size();
        // set the field null
        banque.setNom(null);

        // Create the Banque, which fails.


        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isBadRequest());

        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroCompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueRepository.findAll().size();
        // set the field null
        banque.setNumeroCompte(null);

        // Create the Banque, which fails.


        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isBadRequest());

        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeBanqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = banqueRepository.findAll().size();
        // set the field null
        banque.setCodeBanque(null);

        // Create the Banque, which fails.


        restBanqueMockMvc.perform(post("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isBadRequest());

        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBanques() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        // Get all the banqueList
        restBanqueMockMvc.perform(get("/api/banques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banque.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].numeroCompte").value(hasItem(DEFAULT_NUMERO_COMPTE)))
            .andExpect(jsonPath("$.[*].codeBanque").value(hasItem(DEFAULT_CODE_BANQUE)))
            .andExpect(jsonPath("$.[*].codeGuichet").value(hasItem(DEFAULT_CODE_GUICHET)))
            .andExpect(jsonPath("$.[*].cleRib").value(hasItem(DEFAULT_CLE_RIB)));
    }
    
    @Test
    @Transactional
    public void getBanque() throws Exception {
        // Initialize the database
        banqueRepository.saveAndFlush(banque);

        // Get the banque
        restBanqueMockMvc.perform(get("/api/banques/{id}", banque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(banque.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.numeroCompte").value(DEFAULT_NUMERO_COMPTE))
            .andExpect(jsonPath("$.codeBanque").value(DEFAULT_CODE_BANQUE))
            .andExpect(jsonPath("$.codeGuichet").value(DEFAULT_CODE_GUICHET))
            .andExpect(jsonPath("$.cleRib").value(DEFAULT_CLE_RIB));
    }
    @Test
    @Transactional
    public void getNonExistingBanque() throws Exception {
        // Get the banque
        restBanqueMockMvc.perform(get("/api/banques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanque() throws Exception {
        // Initialize the database
        banqueService.save(banque);

        int databaseSizeBeforeUpdate = banqueRepository.findAll().size();

        // Update the banque
        Banque updatedBanque = banqueRepository.findById(banque.getId()).get();
        // Disconnect from session so that the updates on updatedBanque are not directly saved in db
        em.detach(updatedBanque);
        updatedBanque
            .nom(UPDATED_NOM)
            .numeroCompte(UPDATED_NUMERO_COMPTE)
            .codeBanque(UPDATED_CODE_BANQUE)
            .codeGuichet(UPDATED_CODE_GUICHET)
            .cleRib(UPDATED_CLE_RIB);

        restBanqueMockMvc.perform(put("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBanque)))
            .andExpect(status().isOk());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeUpdate);
        Banque testBanque = banqueList.get(banqueList.size() - 1);
        assertThat(testBanque.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testBanque.getNumeroCompte()).isEqualTo(UPDATED_NUMERO_COMPTE);
        assertThat(testBanque.getCodeBanque()).isEqualTo(UPDATED_CODE_BANQUE);
        assertThat(testBanque.getCodeGuichet()).isEqualTo(UPDATED_CODE_GUICHET);
        assertThat(testBanque.getCleRib()).isEqualTo(UPDATED_CLE_RIB);
    }

    @Test
    @Transactional
    public void updateNonExistingBanque() throws Exception {
        int databaseSizeBeforeUpdate = banqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanqueMockMvc.perform(put("/api/banques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banque)))
            .andExpect(status().isBadRequest());

        // Validate the Banque in the database
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBanque() throws Exception {
        // Initialize the database
        banqueService.save(banque);

        int databaseSizeBeforeDelete = banqueRepository.findAll().size();

        // Delete the banque
        restBanqueMockMvc.perform(delete("/api/banques/{id}", banque.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Banque> banqueList = banqueRepository.findAll();
        assertThat(banqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
