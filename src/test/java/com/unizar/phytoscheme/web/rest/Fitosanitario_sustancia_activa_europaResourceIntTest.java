package com.unizar.phytoscheme.web.rest;

import com.unizar.phytoscheme.TfgjHipsterApp;

import com.unizar.phytoscheme.domain.Fitosanitario_sustancia_activa_europa;
import com.unizar.phytoscheme.repository.Fitosanitario_sustancia_activa_europaRepository;
import com.unizar.phytoscheme.repository.search.Fitosanitario_sustancia_activa_europaSearchRepository;
import com.unizar.phytoscheme.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Fitosanitario_sustancia_activa_europaResource REST controller.
 *
 * @see Fitosanitario_sustancia_activa_europaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgjHipsterApp.class)
public class Fitosanitario_sustancia_activa_europaResourceIntTest {

    private static final String DEFAULT_NUMREGISTRO = "AAAAAAAAAA";
    private static final String UPDATED_NUMREGISTRO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRECOMERCIAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRECOMERCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULADO = "AAAAAAAAAA";
    private static final String UPDATED_FORMULADO = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_SUBSTANCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_SUBSTANCE_ID = "BBBBBBBBBB";

    @Autowired
    private Fitosanitario_sustancia_activa_europaRepository fitosanitario_sustancia_activa_europaRepository;

    @Autowired
    private Fitosanitario_sustancia_activa_europaSearchRepository fitosanitario_sustancia_activa_europaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFitosanitario_sustancia_activa_europaMockMvc;

    private Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Fitosanitario_sustancia_activa_europaResource fitosanitario_sustancia_activa_europaResource = new Fitosanitario_sustancia_activa_europaResource(fitosanitario_sustancia_activa_europaRepository, fitosanitario_sustancia_activa_europaSearchRepository);
        this.restFitosanitario_sustancia_activa_europaMockMvc = MockMvcBuilders.standaloneSetup(fitosanitario_sustancia_activa_europaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fitosanitario_sustancia_activa_europa createEntity(EntityManager em) {
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa = new Fitosanitario_sustancia_activa_europa()
            .numregistro(DEFAULT_NUMREGISTRO)
            .nombrecomercial(DEFAULT_NOMBRECOMERCIAL)
            .titular(DEFAULT_TITULAR)
            .formulado(DEFAULT_FORMULADO)
            .activeSubstanceID(DEFAULT_ACTIVE_SUBSTANCE_ID);
        return fitosanitario_sustancia_activa_europa;
    }

    @Before
    public void initTest() {
        fitosanitario_sustancia_activa_europaSearchRepository.deleteAll();
        fitosanitario_sustancia_activa_europa = createEntity(em);
    }

    @Test
    @Transactional
    public void createFitosanitario_sustancia_activa_europa() throws Exception {
        int databaseSizeBeforeCreate = fitosanitario_sustancia_activa_europaRepository.findAll().size();

        // Create the Fitosanitario_sustancia_activa_europa
        restFitosanitario_sustancia_activa_europaMockMvc.perform(post("/api/fitosanitario-sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario_sustancia_activa_europa)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario_sustancia_activa_europa in the database
        List<Fitosanitario_sustancia_activa_europa> fitosanitario_sustancia_activa_europaList = fitosanitario_sustancia_activa_europaRepository.findAll();
        assertThat(fitosanitario_sustancia_activa_europaList).hasSize(databaseSizeBeforeCreate + 1);
        Fitosanitario_sustancia_activa_europa testFitosanitario_sustancia_activa_europa = fitosanitario_sustancia_activa_europaList.get(fitosanitario_sustancia_activa_europaList.size() - 1);
        assertThat(testFitosanitario_sustancia_activa_europa.getNumregistro()).isEqualTo(DEFAULT_NUMREGISTRO);
        assertThat(testFitosanitario_sustancia_activa_europa.getNombrecomercial()).isEqualTo(DEFAULT_NOMBRECOMERCIAL);
        assertThat(testFitosanitario_sustancia_activa_europa.getTitular()).isEqualTo(DEFAULT_TITULAR);
        assertThat(testFitosanitario_sustancia_activa_europa.getFormulado()).isEqualTo(DEFAULT_FORMULADO);
        assertThat(testFitosanitario_sustancia_activa_europa.getActiveSubstanceID()).isEqualTo(DEFAULT_ACTIVE_SUBSTANCE_ID);

        // Validate the Fitosanitario_sustancia_activa_europa in Elasticsearch
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europaEs = fitosanitario_sustancia_activa_europaSearchRepository.findOne(testFitosanitario_sustancia_activa_europa.getId());
        assertThat(fitosanitario_sustancia_activa_europaEs).isEqualToComparingFieldByField(testFitosanitario_sustancia_activa_europa);
    }

    @Test
    @Transactional
    public void createFitosanitario_sustancia_activa_europaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fitosanitario_sustancia_activa_europaRepository.findAll().size();

        // Create the Fitosanitario_sustancia_activa_europa with an existing ID
        fitosanitario_sustancia_activa_europa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFitosanitario_sustancia_activa_europaMockMvc.perform(post("/api/fitosanitario-sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario_sustancia_activa_europa)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fitosanitario_sustancia_activa_europa> fitosanitario_sustancia_activa_europaList = fitosanitario_sustancia_activa_europaRepository.findAll();
        assertThat(fitosanitario_sustancia_activa_europaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFitosanitario_sustancia_activa_europas() throws Exception {
        // Initialize the database
        fitosanitario_sustancia_activa_europaRepository.saveAndFlush(fitosanitario_sustancia_activa_europa);

        // Get all the fitosanitario_sustancia_activa_europaList
        restFitosanitario_sustancia_activa_europaMockMvc.perform(get("/api/fitosanitario-sustancia-activa-europas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fitosanitario_sustancia_activa_europa.getId().intValue())))
            .andExpect(jsonPath("$.[*].numregistro").value(hasItem(DEFAULT_NUMREGISTRO.toString())))
            .andExpect(jsonPath("$.[*].nombrecomercial").value(hasItem(DEFAULT_NOMBRECOMERCIAL.toString())))
            .andExpect(jsonPath("$.[*].titular").value(hasItem(DEFAULT_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].formulado").value(hasItem(DEFAULT_FORMULADO.toString())))
            .andExpect(jsonPath("$.[*].activeSubstanceID").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE_ID.toString())));
    }

    @Test
    @Transactional
    public void getFitosanitario_sustancia_activa_europa() throws Exception {
        // Initialize the database
        fitosanitario_sustancia_activa_europaRepository.saveAndFlush(fitosanitario_sustancia_activa_europa);

        // Get the fitosanitario_sustancia_activa_europa
        restFitosanitario_sustancia_activa_europaMockMvc.perform(get("/api/fitosanitario-sustancia-activa-europas/{id}", fitosanitario_sustancia_activa_europa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fitosanitario_sustancia_activa_europa.getId().intValue()))
            .andExpect(jsonPath("$.numregistro").value(DEFAULT_NUMREGISTRO.toString()))
            .andExpect(jsonPath("$.nombrecomercial").value(DEFAULT_NOMBRECOMERCIAL.toString()))
            .andExpect(jsonPath("$.titular").value(DEFAULT_TITULAR.toString()))
            .andExpect(jsonPath("$.formulado").value(DEFAULT_FORMULADO.toString()))
            .andExpect(jsonPath("$.activeSubstanceID").value(DEFAULT_ACTIVE_SUBSTANCE_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFitosanitario_sustancia_activa_europa() throws Exception {
        // Get the fitosanitario_sustancia_activa_europa
        restFitosanitario_sustancia_activa_europaMockMvc.perform(get("/api/fitosanitario-sustancia-activa-europas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFitosanitario_sustancia_activa_europa() throws Exception {
        // Initialize the database
        fitosanitario_sustancia_activa_europaRepository.saveAndFlush(fitosanitario_sustancia_activa_europa);
        fitosanitario_sustancia_activa_europaSearchRepository.save(fitosanitario_sustancia_activa_europa);
        int databaseSizeBeforeUpdate = fitosanitario_sustancia_activa_europaRepository.findAll().size();

        // Update the fitosanitario_sustancia_activa_europa
        Fitosanitario_sustancia_activa_europa updatedFitosanitario_sustancia_activa_europa = fitosanitario_sustancia_activa_europaRepository.findOne(fitosanitario_sustancia_activa_europa.getId());
        updatedFitosanitario_sustancia_activa_europa
            .numregistro(UPDATED_NUMREGISTRO)
            .nombrecomercial(UPDATED_NOMBRECOMERCIAL)
            .titular(UPDATED_TITULAR)
            .formulado(UPDATED_FORMULADO)
            .activeSubstanceID(UPDATED_ACTIVE_SUBSTANCE_ID);

        restFitosanitario_sustancia_activa_europaMockMvc.perform(put("/api/fitosanitario-sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFitosanitario_sustancia_activa_europa)))
            .andExpect(status().isOk());

        // Validate the Fitosanitario_sustancia_activa_europa in the database
        List<Fitosanitario_sustancia_activa_europa> fitosanitario_sustancia_activa_europaList = fitosanitario_sustancia_activa_europaRepository.findAll();
        assertThat(fitosanitario_sustancia_activa_europaList).hasSize(databaseSizeBeforeUpdate);
        Fitosanitario_sustancia_activa_europa testFitosanitario_sustancia_activa_europa = fitosanitario_sustancia_activa_europaList.get(fitosanitario_sustancia_activa_europaList.size() - 1);
        assertThat(testFitosanitario_sustancia_activa_europa.getNumregistro()).isEqualTo(UPDATED_NUMREGISTRO);
        assertThat(testFitosanitario_sustancia_activa_europa.getNombrecomercial()).isEqualTo(UPDATED_NOMBRECOMERCIAL);
        assertThat(testFitosanitario_sustancia_activa_europa.getTitular()).isEqualTo(UPDATED_TITULAR);
        assertThat(testFitosanitario_sustancia_activa_europa.getFormulado()).isEqualTo(UPDATED_FORMULADO);
        assertThat(testFitosanitario_sustancia_activa_europa.getActiveSubstanceID()).isEqualTo(UPDATED_ACTIVE_SUBSTANCE_ID);

        // Validate the Fitosanitario_sustancia_activa_europa in Elasticsearch
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europaEs = fitosanitario_sustancia_activa_europaSearchRepository.findOne(testFitosanitario_sustancia_activa_europa.getId());
        assertThat(fitosanitario_sustancia_activa_europaEs).isEqualToComparingFieldByField(testFitosanitario_sustancia_activa_europa);
    }

    @Test
    @Transactional
    public void updateNonExistingFitosanitario_sustancia_activa_europa() throws Exception {
        int databaseSizeBeforeUpdate = fitosanitario_sustancia_activa_europaRepository.findAll().size();

        // Create the Fitosanitario_sustancia_activa_europa

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFitosanitario_sustancia_activa_europaMockMvc.perform(put("/api/fitosanitario-sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario_sustancia_activa_europa)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario_sustancia_activa_europa in the database
        List<Fitosanitario_sustancia_activa_europa> fitosanitario_sustancia_activa_europaList = fitosanitario_sustancia_activa_europaRepository.findAll();
        assertThat(fitosanitario_sustancia_activa_europaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFitosanitario_sustancia_activa_europa() throws Exception {
        // Initialize the database
        fitosanitario_sustancia_activa_europaRepository.saveAndFlush(fitosanitario_sustancia_activa_europa);
        fitosanitario_sustancia_activa_europaSearchRepository.save(fitosanitario_sustancia_activa_europa);
        int databaseSizeBeforeDelete = fitosanitario_sustancia_activa_europaRepository.findAll().size();

        // Get the fitosanitario_sustancia_activa_europa
        restFitosanitario_sustancia_activa_europaMockMvc.perform(delete("/api/fitosanitario-sustancia-activa-europas/{id}", fitosanitario_sustancia_activa_europa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fitosanitario_sustancia_activa_europaExistsInEs = fitosanitario_sustancia_activa_europaSearchRepository.exists(fitosanitario_sustancia_activa_europa.getId());
        assertThat(fitosanitario_sustancia_activa_europaExistsInEs).isFalse();

        // Validate the database is empty
        List<Fitosanitario_sustancia_activa_europa> fitosanitario_sustancia_activa_europaList = fitosanitario_sustancia_activa_europaRepository.findAll();
        assertThat(fitosanitario_sustancia_activa_europaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFitosanitario_sustancia_activa_europa() throws Exception {
        // Initialize the database
        fitosanitario_sustancia_activa_europaRepository.saveAndFlush(fitosanitario_sustancia_activa_europa);
        fitosanitario_sustancia_activa_europaSearchRepository.save(fitosanitario_sustancia_activa_europa);

        // Search the fitosanitario_sustancia_activa_europa
        restFitosanitario_sustancia_activa_europaMockMvc.perform(get("/api/_search/fitosanitario-sustancia-activa-europas?query=id:" + fitosanitario_sustancia_activa_europa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fitosanitario_sustancia_activa_europa.getId().intValue())))
            .andExpect(jsonPath("$.[*].numregistro").value(hasItem(DEFAULT_NUMREGISTRO.toString())))
            .andExpect(jsonPath("$.[*].nombrecomercial").value(hasItem(DEFAULT_NOMBRECOMERCIAL.toString())))
            .andExpect(jsonPath("$.[*].titular").value(hasItem(DEFAULT_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].formulado").value(hasItem(DEFAULT_FORMULADO.toString())))
            .andExpect(jsonPath("$.[*].activeSubstanceID").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE_ID.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fitosanitario_sustancia_activa_europa.class);
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa1 = new Fitosanitario_sustancia_activa_europa();
        fitosanitario_sustancia_activa_europa1.setId(1L);
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa2 = new Fitosanitario_sustancia_activa_europa();
        fitosanitario_sustancia_activa_europa2.setId(fitosanitario_sustancia_activa_europa1.getId());
        assertThat(fitosanitario_sustancia_activa_europa1).isEqualTo(fitosanitario_sustancia_activa_europa2);
        fitosanitario_sustancia_activa_europa2.setId(2L);
        assertThat(fitosanitario_sustancia_activa_europa1).isNotEqualTo(fitosanitario_sustancia_activa_europa2);
        fitosanitario_sustancia_activa_europa1.setId(null);
        assertThat(fitosanitario_sustancia_activa_europa1).isNotEqualTo(fitosanitario_sustancia_activa_europa2);
    }
}
