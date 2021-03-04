package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.AppConfiguration;
import com.rhplus.rhplus.repository.AppConfigurationRepository;
import com.rhplus.rhplus.service.AppConfigurationService;

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
 * Integration tests for the {@link AppConfigurationResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AppConfigurationResourceIT {

    private static final String DEFAULT_NOM_ENTREPRISE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ENTREPRISE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FIXE = "AAAAAAAAAA";
    private static final String UPDATED_FIXE = "BBBBBBBBBB";

    private static final String DEFAULT_NINEA = "AAAAAAAAAA";
    private static final String UPDATED_NINEA = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_URL = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_URL = "BBBBBBBBBB";

    @Autowired
    private AppConfigurationRepository appConfigurationRepository;

    @Autowired
    private AppConfigurationService appConfigurationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppConfigurationMockMvc;

    private AppConfiguration appConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfiguration createEntity(EntityManager em) {
        AppConfiguration appConfiguration = new AppConfiguration()
            .nomEntreprise(DEFAULT_NOM_ENTREPRISE)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .fixe(DEFAULT_FIXE)
            .ninea(DEFAULT_NINEA)
            .logoUrl(DEFAULT_LOGO_URL);
        return appConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfiguration createUpdatedEntity(EntityManager em) {
        AppConfiguration appConfiguration = new AppConfiguration()
            .nomEntreprise(UPDATED_NOM_ENTREPRISE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .fixe(UPDATED_FIXE)
            .ninea(UPDATED_NINEA)
            .logoUrl(UPDATED_LOGO_URL);
        return appConfiguration;
    }

    @BeforeEach
    public void initTest() {
        appConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppConfiguration() throws Exception {
        int databaseSizeBeforeCreate = appConfigurationRepository.findAll().size();
        // Create the AppConfiguration
        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isCreated());

        // Validate the AppConfiguration in the database
        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        AppConfiguration testAppConfiguration = appConfigurationList.get(appConfigurationList.size() - 1);
        assertThat(testAppConfiguration.getNomEntreprise()).isEqualTo(DEFAULT_NOM_ENTREPRISE);
        assertThat(testAppConfiguration.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testAppConfiguration.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testAppConfiguration.getFixe()).isEqualTo(DEFAULT_FIXE);
        assertThat(testAppConfiguration.getNinea()).isEqualTo(DEFAULT_NINEA);
        assertThat(testAppConfiguration.getLogoUrl()).isEqualTo(DEFAULT_LOGO_URL);
    }

    @Test
    @Transactional
    public void createAppConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appConfigurationRepository.findAll().size();

        // Create the AppConfiguration with an existing ID
        appConfiguration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the AppConfiguration in the database
        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomEntrepriseIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigurationRepository.findAll().size();
        // set the field null
        appConfiguration.setNomEntreprise(null);

        // Create the AppConfiguration, which fails.


        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigurationRepository.findAll().size();
        // set the field null
        appConfiguration.setAdresse(null);

        // Create the AppConfiguration, which fails.


        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelephoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigurationRepository.findAll().size();
        // set the field null
        appConfiguration.setTelephone(null);

        // Create the AppConfiguration, which fails.


        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFixeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigurationRepository.findAll().size();
        // set the field null
        appConfiguration.setFixe(null);

        // Create the AppConfiguration, which fails.


        restAppConfigurationMockMvc.perform(post("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppConfigurations() throws Exception {
        // Initialize the database
        appConfigurationRepository.saveAndFlush(appConfiguration);

        // Get all the appConfigurationList
        restAppConfigurationMockMvc.perform(get("/api/app-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomEntreprise").value(hasItem(DEFAULT_NOM_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fixe").value(hasItem(DEFAULT_FIXE)))
            .andExpect(jsonPath("$.[*].ninea").value(hasItem(DEFAULT_NINEA)))
            .andExpect(jsonPath("$.[*].logoUrl").value(hasItem(DEFAULT_LOGO_URL)));
    }
    
    @Test
    @Transactional
    public void getAppConfiguration() throws Exception {
        // Initialize the database
        appConfigurationRepository.saveAndFlush(appConfiguration);

        // Get the appConfiguration
        restAppConfigurationMockMvc.perform(get("/api/app-configurations/{id}", appConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.nomEntreprise").value(DEFAULT_NOM_ENTREPRISE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fixe").value(DEFAULT_FIXE))
            .andExpect(jsonPath("$.ninea").value(DEFAULT_NINEA))
            .andExpect(jsonPath("$.logoUrl").value(DEFAULT_LOGO_URL));
    }
    @Test
    @Transactional
    public void getNonExistingAppConfiguration() throws Exception {
        // Get the appConfiguration
        restAppConfigurationMockMvc.perform(get("/api/app-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppConfiguration() throws Exception {
        // Initialize the database
        appConfigurationService.save(appConfiguration);

        int databaseSizeBeforeUpdate = appConfigurationRepository.findAll().size();

        // Update the appConfiguration
        AppConfiguration updatedAppConfiguration = appConfigurationRepository.findById(appConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedAppConfiguration are not directly saved in db
        em.detach(updatedAppConfiguration);
        updatedAppConfiguration
            .nomEntreprise(UPDATED_NOM_ENTREPRISE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .fixe(UPDATED_FIXE)
            .ninea(UPDATED_NINEA)
            .logoUrl(UPDATED_LOGO_URL);

        restAppConfigurationMockMvc.perform(put("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAppConfiguration)))
            .andExpect(status().isOk());

        // Validate the AppConfiguration in the database
        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeUpdate);
        AppConfiguration testAppConfiguration = appConfigurationList.get(appConfigurationList.size() - 1);
        assertThat(testAppConfiguration.getNomEntreprise()).isEqualTo(UPDATED_NOM_ENTREPRISE);
        assertThat(testAppConfiguration.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testAppConfiguration.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testAppConfiguration.getFixe()).isEqualTo(UPDATED_FIXE);
        assertThat(testAppConfiguration.getNinea()).isEqualTo(UPDATED_NINEA);
        assertThat(testAppConfiguration.getLogoUrl()).isEqualTo(UPDATED_LOGO_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingAppConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = appConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppConfigurationMockMvc.perform(put("/api/app-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the AppConfiguration in the database
        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppConfiguration() throws Exception {
        // Initialize the database
        appConfigurationService.save(appConfiguration);

        int databaseSizeBeforeDelete = appConfigurationRepository.findAll().size();

        // Delete the appConfiguration
        restAppConfigurationMockMvc.perform(delete("/api/app-configurations/{id}", appConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppConfiguration> appConfigurationList = appConfigurationRepository.findAll();
        assertThat(appConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
