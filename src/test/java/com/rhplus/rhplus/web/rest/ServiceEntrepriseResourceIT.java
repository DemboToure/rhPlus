package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.ServiceEntreprise;
import com.rhplus.rhplus.repository.ServiceEntrepriseRepository;
import com.rhplus.rhplus.service.ServiceEntrepriseService;

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
 * Integration tests for the {@link ServiceEntrepriseResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ServiceEntrepriseResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_RESPONSABLE = 1;
    private static final Integer UPDATED_RESPONSABLE = 2;

    @Autowired
    private ServiceEntrepriseRepository serviceEntrepriseRepository;

    @Autowired
    private ServiceEntrepriseService serviceEntrepriseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceEntrepriseMockMvc;

    private ServiceEntreprise serviceEntreprise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEntreprise createEntity(EntityManager em) {
        ServiceEntreprise serviceEntreprise = new ServiceEntreprise()
            .nom(DEFAULT_NOM)
            .adresse(DEFAULT_ADRESSE)
            .tel(DEFAULT_TEL)
            .responsable(DEFAULT_RESPONSABLE);
        return serviceEntreprise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEntreprise createUpdatedEntity(EntityManager em) {
        ServiceEntreprise serviceEntreprise = new ServiceEntreprise()
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .responsable(UPDATED_RESPONSABLE);
        return serviceEntreprise;
    }

    @BeforeEach
    public void initTest() {
        serviceEntreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceEntreprise() throws Exception {
        int databaseSizeBeforeCreate = serviceEntrepriseRepository.findAll().size();
        // Create the ServiceEntreprise
        restServiceEntrepriseMockMvc.perform(post("/api/service-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceEntreprise)))
            .andExpect(status().isCreated());

        // Validate the ServiceEntreprise in the database
        List<ServiceEntreprise> serviceEntrepriseList = serviceEntrepriseRepository.findAll();
        assertThat(serviceEntrepriseList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceEntreprise testServiceEntreprise = serviceEntrepriseList.get(serviceEntrepriseList.size() - 1);
        assertThat(testServiceEntreprise.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testServiceEntreprise.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testServiceEntreprise.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testServiceEntreprise.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
    }

    @Test
    @Transactional
    public void createServiceEntrepriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceEntrepriseRepository.findAll().size();

        // Create the ServiceEntreprise with an existing ID
        serviceEntreprise.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceEntrepriseMockMvc.perform(post("/api/service-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceEntreprise)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceEntreprise in the database
        List<ServiceEntreprise> serviceEntrepriseList = serviceEntrepriseRepository.findAll();
        assertThat(serviceEntrepriseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllServiceEntreprises() throws Exception {
        // Initialize the database
        serviceEntrepriseRepository.saveAndFlush(serviceEntreprise);

        // Get all the serviceEntrepriseList
        restServiceEntrepriseMockMvc.perform(get("/api/service-entreprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceEntreprise.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE)));
    }
    
    @Test
    @Transactional
    public void getServiceEntreprise() throws Exception {
        // Initialize the database
        serviceEntrepriseRepository.saveAndFlush(serviceEntreprise);

        // Get the serviceEntreprise
        restServiceEntrepriseMockMvc.perform(get("/api/service-entreprises/{id}", serviceEntreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceEntreprise.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE));
    }
    @Test
    @Transactional
    public void getNonExistingServiceEntreprise() throws Exception {
        // Get the serviceEntreprise
        restServiceEntrepriseMockMvc.perform(get("/api/service-entreprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceEntreprise() throws Exception {
        // Initialize the database
        serviceEntrepriseService.save(serviceEntreprise);

        int databaseSizeBeforeUpdate = serviceEntrepriseRepository.findAll().size();

        // Update the serviceEntreprise
        ServiceEntreprise updatedServiceEntreprise = serviceEntrepriseRepository.findById(serviceEntreprise.getId()).get();
        // Disconnect from session so that the updates on updatedServiceEntreprise are not directly saved in db
        em.detach(updatedServiceEntreprise);
        updatedServiceEntreprise
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .responsable(UPDATED_RESPONSABLE);

        restServiceEntrepriseMockMvc.perform(put("/api/service-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedServiceEntreprise)))
            .andExpect(status().isOk());

        // Validate the ServiceEntreprise in the database
        List<ServiceEntreprise> serviceEntrepriseList = serviceEntrepriseRepository.findAll();
        assertThat(serviceEntrepriseList).hasSize(databaseSizeBeforeUpdate);
        ServiceEntreprise testServiceEntreprise = serviceEntrepriseList.get(serviceEntrepriseList.size() - 1);
        assertThat(testServiceEntreprise.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testServiceEntreprise.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testServiceEntreprise.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testServiceEntreprise.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceEntreprise() throws Exception {
        int databaseSizeBeforeUpdate = serviceEntrepriseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceEntrepriseMockMvc.perform(put("/api/service-entreprises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceEntreprise)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceEntreprise in the database
        List<ServiceEntreprise> serviceEntrepriseList = serviceEntrepriseRepository.findAll();
        assertThat(serviceEntrepriseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceEntreprise() throws Exception {
        // Initialize the database
        serviceEntrepriseService.save(serviceEntreprise);

        int databaseSizeBeforeDelete = serviceEntrepriseRepository.findAll().size();

        // Delete the serviceEntreprise
        restServiceEntrepriseMockMvc.perform(delete("/api/service-entreprises/{id}", serviceEntreprise.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceEntreprise> serviceEntrepriseList = serviceEntrepriseRepository.findAll();
        assertThat(serviceEntrepriseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
