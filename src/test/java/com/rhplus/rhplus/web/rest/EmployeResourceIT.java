package com.rhplus.rhplus.web.rest;

import com.rhplus.rhplus.RhPlusApp;
import com.rhplus.rhplus.domain.Employe;
import com.rhplus.rhplus.repository.EmployeRepository;
import com.rhplus.rhplus.service.EmployeService;

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

import com.rhplus.rhplus.domain.enumeration.SituationMatrimoniale;
import com.rhplus.rhplus.domain.enumeration.Sexe;
/**
 * Integration tests for the {@link EmployeResource} REST controller.
 */
@SpringBootTest(classes = RhPlusApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEU_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_CNI = "AAAAAAAAAA";
    private static final String UPDATED_CNI = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_CAISSE_SECURITE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CAISSE_SECURITE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_IPRES = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_IPRES = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_PERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MERE = "BBBBBBBBBB";

    private static final SituationMatrimoniale DEFAULT_SITUATION_MATRIMONIALE = SituationMatrimoniale.MARIER;
    private static final SituationMatrimoniale UPDATED_SITUATION_MATRIMONIALE = SituationMatrimoniale.CELIBATAIRE;

    private static final Sexe DEFAULT_SEXE = Sexe.FEMME;
    private static final Sexe UPDATED_SEXE = Sexe.HOMME;

    private static final String DEFAULT_TRIMF = "AAAAAAAAAA";
    private static final String UPDATED_TRIMF = "BBBBBBBBBB";

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMBAUCHE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMBAUCHE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeMockMvc;

    private Employe employe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createEntity(EntityManager em) {
        Employe employe = new Employe()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .lieuNaissance(DEFAULT_LIEU_NAISSANCE)
            .cni(DEFAULT_CNI)
            .profession(DEFAULT_PROFESSION)
            .numeroCaisseSecurite(DEFAULT_NUMERO_CAISSE_SECURITE)
            .numeroIpres(DEFAULT_NUMERO_IPRES)
            .matricule(DEFAULT_MATRICULE)
            .imageUrl(DEFAULT_IMAGE_URL)
            .prenomPere(DEFAULT_PRENOM_PERE)
            .prenomMere(DEFAULT_PRENOM_MERE)
            .nomMere(DEFAULT_NOM_MERE)
            .situationMatrimoniale(DEFAULT_SITUATION_MATRIMONIALE)
            .sexe(DEFAULT_SEXE)
            .trimf(DEFAULT_TRIMF)
            .statut(DEFAULT_STATUT)
            .dateEmbauche(DEFAULT_DATE_EMBAUCHE)
            .nationalite(DEFAULT_NATIONALITE);
        return employe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createUpdatedEntity(EntityManager em) {
        Employe employe = new Employe()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE)
            .cni(UPDATED_CNI)
            .profession(UPDATED_PROFESSION)
            .numeroCaisseSecurite(UPDATED_NUMERO_CAISSE_SECURITE)
            .numeroIpres(UPDATED_NUMERO_IPRES)
            .matricule(UPDATED_MATRICULE)
            .imageUrl(UPDATED_IMAGE_URL)
            .prenomPere(UPDATED_PRENOM_PERE)
            .prenomMere(UPDATED_PRENOM_MERE)
            .nomMere(UPDATED_NOM_MERE)
            .situationMatrimoniale(UPDATED_SITUATION_MATRIMONIALE)
            .sexe(UPDATED_SEXE)
            .trimf(UPDATED_TRIMF)
            .statut(UPDATED_STATUT)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .nationalite(UPDATED_NATIONALITE);
        return employe;
    }

    @BeforeEach
    public void initTest() {
        employe = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmploye() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();
        // Create the Employe
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isCreated());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate + 1);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEmploye.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testEmploye.getLieuNaissance()).isEqualTo(DEFAULT_LIEU_NAISSANCE);
        assertThat(testEmploye.getCni()).isEqualTo(DEFAULT_CNI);
        assertThat(testEmploye.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testEmploye.getNumeroCaisseSecurite()).isEqualTo(DEFAULT_NUMERO_CAISSE_SECURITE);
        assertThat(testEmploye.getNumeroIpres()).isEqualTo(DEFAULT_NUMERO_IPRES);
        assertThat(testEmploye.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testEmploye.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testEmploye.getPrenomPere()).isEqualTo(DEFAULT_PRENOM_PERE);
        assertThat(testEmploye.getPrenomMere()).isEqualTo(DEFAULT_PRENOM_MERE);
        assertThat(testEmploye.getNomMere()).isEqualTo(DEFAULT_NOM_MERE);
        assertThat(testEmploye.getSituationMatrimoniale()).isEqualTo(DEFAULT_SITUATION_MATRIMONIALE);
        assertThat(testEmploye.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testEmploye.getTrimf()).isEqualTo(DEFAULT_TRIMF);
        assertThat(testEmploye.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(DEFAULT_DATE_EMBAUCHE);
        assertThat(testEmploye.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
    }

    @Test
    @Transactional
    public void createEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();

        // Create the Employe with an existing ID
        employe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setPrenom(null);

        // Create the Employe, which fails.


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setNom(null);

        // Create the Employe, which fails.


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setDateNaissance(null);

        // Create the Employe, which fails.


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLieuNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setLieuNaissance(null);

        // Create the Employe, which fails.


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCniIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeRepository.findAll().size();
        // set the field null
        employe.setCni(null);

        // Create the Employe, which fails.


        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployes() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get all the employeList
        restEmployeMockMvc.perform(get("/api/employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employe.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].lieuNaissance").value(hasItem(DEFAULT_LIEU_NAISSANCE)))
            .andExpect(jsonPath("$.[*].cni").value(hasItem(DEFAULT_CNI)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].numeroCaisseSecurite").value(hasItem(DEFAULT_NUMERO_CAISSE_SECURITE)))
            .andExpect(jsonPath("$.[*].numeroIpres").value(hasItem(DEFAULT_NUMERO_IPRES)))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].prenomPere").value(hasItem(DEFAULT_PRENOM_PERE)))
            .andExpect(jsonPath("$.[*].prenomMere").value(hasItem(DEFAULT_PRENOM_MERE)))
            .andExpect(jsonPath("$.[*].nomMere").value(hasItem(DEFAULT_NOM_MERE)))
            .andExpect(jsonPath("$.[*].situationMatrimoniale").value(hasItem(DEFAULT_SITUATION_MATRIMONIALE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].trimf").value(hasItem(DEFAULT_TRIMF)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].dateEmbauche").value(hasItem(DEFAULT_DATE_EMBAUCHE.toString())))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE)));
    }
    
    @Test
    @Transactional
    public void getEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", employe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employe.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.lieuNaissance").value(DEFAULT_LIEU_NAISSANCE))
            .andExpect(jsonPath("$.cni").value(DEFAULT_CNI))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.numeroCaisseSecurite").value(DEFAULT_NUMERO_CAISSE_SECURITE))
            .andExpect(jsonPath("$.numeroIpres").value(DEFAULT_NUMERO_IPRES))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.prenomPere").value(DEFAULT_PRENOM_PERE))
            .andExpect(jsonPath("$.prenomMere").value(DEFAULT_PRENOM_MERE))
            .andExpect(jsonPath("$.nomMere").value(DEFAULT_NOM_MERE))
            .andExpect(jsonPath("$.situationMatrimoniale").value(DEFAULT_SITUATION_MATRIMONIALE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.trimf").value(DEFAULT_TRIMF))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.dateEmbauche").value(DEFAULT_DATE_EMBAUCHE.toString()))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE));
    }
    @Test
    @Transactional
    public void getNonExistingEmploye() throws Exception {
        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmploye() throws Exception {
        // Initialize the database
        employeService.save(employe);

        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // Update the employe
        Employe updatedEmploye = employeRepository.findById(employe.getId()).get();
        // Disconnect from session so that the updates on updatedEmploye are not directly saved in db
        em.detach(updatedEmploye);
        updatedEmploye
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .lieuNaissance(UPDATED_LIEU_NAISSANCE)
            .cni(UPDATED_CNI)
            .profession(UPDATED_PROFESSION)
            .numeroCaisseSecurite(UPDATED_NUMERO_CAISSE_SECURITE)
            .numeroIpres(UPDATED_NUMERO_IPRES)
            .matricule(UPDATED_MATRICULE)
            .imageUrl(UPDATED_IMAGE_URL)
            .prenomPere(UPDATED_PRENOM_PERE)
            .prenomMere(UPDATED_PRENOM_MERE)
            .nomMere(UPDATED_NOM_MERE)
            .situationMatrimoniale(UPDATED_SITUATION_MATRIMONIALE)
            .sexe(UPDATED_SEXE)
            .trimf(UPDATED_TRIMF)
            .statut(UPDATED_STATUT)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .nationalite(UPDATED_NATIONALITE);

        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmploye)))
            .andExpect(status().isOk());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEmploye.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testEmploye.getLieuNaissance()).isEqualTo(UPDATED_LIEU_NAISSANCE);
        assertThat(testEmploye.getCni()).isEqualTo(UPDATED_CNI);
        assertThat(testEmploye.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testEmploye.getNumeroCaisseSecurite()).isEqualTo(UPDATED_NUMERO_CAISSE_SECURITE);
        assertThat(testEmploye.getNumeroIpres()).isEqualTo(UPDATED_NUMERO_IPRES);
        assertThat(testEmploye.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testEmploye.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testEmploye.getPrenomPere()).isEqualTo(UPDATED_PRENOM_PERE);
        assertThat(testEmploye.getPrenomMere()).isEqualTo(UPDATED_PRENOM_MERE);
        assertThat(testEmploye.getNomMere()).isEqualTo(UPDATED_NOM_MERE);
        assertThat(testEmploye.getSituationMatrimoniale()).isEqualTo(UPDATED_SITUATION_MATRIMONIALE);
        assertThat(testEmploye.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testEmploye.getTrimf()).isEqualTo(UPDATED_TRIMF);
        assertThat(testEmploye.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(UPDATED_DATE_EMBAUCHE);
        assertThat(testEmploye.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmploye() throws Exception {
        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employe)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmploye() throws Exception {
        // Initialize the database
        employeService.save(employe);

        int databaseSizeBeforeDelete = employeRepository.findAll().size();

        // Delete the employe
        restEmployeMockMvc.perform(delete("/api/employes/{id}", employe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
