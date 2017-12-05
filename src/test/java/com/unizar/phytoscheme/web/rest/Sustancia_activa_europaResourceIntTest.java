package com.unizar.phytoscheme.web.rest;

import com.unizar.phytoscheme.TfgjHipsterApp;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa;
import com.unizar.phytoscheme.repository.Sustancia_activa_europaRepository;
import com.unizar.phytoscheme.repository.search.Sustancia_activa_europaSearchRepository;
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

import static com.unizar.phytoscheme.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Sustancia_activa_europaResource REST controller.
 *
 * @see Sustancia_activa_europaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgjHipsterApp.class)
public class Sustancia_activa_europaResourceIntTest {

    private static final String DEFAULT_REAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_REAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private Sustancia_activa_europaRepository sustancia_activa_europaRepository;

    @Autowired
    private Sustancia_activa_europaSearchRepository sustancia_activa_europaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSustancia_activa_europaMockMvc;

    private Sustancia_activa_europa sustancia_activa_europa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Sustancia_activa_europaResource sustancia_activa_europaResource = new Sustancia_activa_europaResource(sustancia_activa_europaRepository, sustancia_activa_europaSearchRepository);
        this.restSustancia_activa_europaMockMvc = MockMvcBuilders.standaloneSetup(sustancia_activa_europaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sustancia_activa_europa createEntity(EntityManager em) {
        Sustancia_activa_europa sustancia_activa_europa = new Sustancia_activa_europa()
            .real_id(DEFAULT_REAL_ID)
            .tipo(DEFAULT_TIPO)
            .language(DEFAULT_LANGUAGE)
            .name(DEFAULT_NAME);
        return sustancia_activa_europa;
    }

    @Before
    public void initTest() {
        sustancia_activa_europaSearchRepository.deleteAll();
        sustancia_activa_europa = createEntity(em);
    }

    @Test
    @Transactional
    public void createSustancia_activa_europa() throws Exception {
        int databaseSizeBeforeCreate = sustancia_activa_europaRepository.findAll().size();

        // Create the Sustancia_activa_europa
        restSustancia_activa_europaMockMvc.perform(post("/api/sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa)))
            .andExpect(status().isCreated());

        // Validate the Sustancia_activa_europa in the database
        List<Sustancia_activa_europa> sustancia_activa_europaList = sustancia_activa_europaRepository.findAll();
        assertThat(sustancia_activa_europaList).hasSize(databaseSizeBeforeCreate + 1);
        Sustancia_activa_europa testSustancia_activa_europa = sustancia_activa_europaList.get(sustancia_activa_europaList.size() - 1);
        assertThat(testSustancia_activa_europa.getReal_id()).isEqualTo(DEFAULT_REAL_ID);
        assertThat(testSustancia_activa_europa.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testSustancia_activa_europa.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testSustancia_activa_europa.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the Sustancia_activa_europa in Elasticsearch
        Sustancia_activa_europa sustancia_activa_europaEs = sustancia_activa_europaSearchRepository.findOne(testSustancia_activa_europa.getId());
        assertThat(sustancia_activa_europaEs).isEqualToComparingFieldByField(testSustancia_activa_europa);
    }

    @Test
    @Transactional
    public void createSustancia_activa_europaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sustancia_activa_europaRepository.findAll().size();

        // Create the Sustancia_activa_europa with an existing ID
        sustancia_activa_europa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSustancia_activa_europaMockMvc.perform(post("/api/sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa)))
            .andExpect(status().isBadRequest());

        // Validate the Sustancia_activa_europa in the database
        List<Sustancia_activa_europa> sustancia_activa_europaList = sustancia_activa_europaRepository.findAll();
        assertThat(sustancia_activa_europaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSustancia_activa_europas() throws Exception {
        // Initialize the database
        sustancia_activa_europaRepository.saveAndFlush(sustancia_activa_europa);

        // Get all the sustancia_activa_europaList
        restSustancia_activa_europaMockMvc.perform(get("/api/sustancia-activa-europas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sustancia_activa_europa.getId().intValue())))
            .andExpect(jsonPath("$.[*].real_id").value(hasItem(DEFAULT_REAL_ID.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getSustancia_activa_europa() throws Exception {
        // Initialize the database
        sustancia_activa_europaRepository.saveAndFlush(sustancia_activa_europa);

        // Get the sustancia_activa_europa
        restSustancia_activa_europaMockMvc.perform(get("/api/sustancia-activa-europas/{id}", sustancia_activa_europa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sustancia_activa_europa.getId().intValue()))
            .andExpect(jsonPath("$.real_id").value(DEFAULT_REAL_ID.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSustancia_activa_europa() throws Exception {
        // Get the sustancia_activa_europa
        restSustancia_activa_europaMockMvc.perform(get("/api/sustancia-activa-europas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSustancia_activa_europa() throws Exception {
        // Initialize the database
        sustancia_activa_europaRepository.saveAndFlush(sustancia_activa_europa);
        sustancia_activa_europaSearchRepository.save(sustancia_activa_europa);
        int databaseSizeBeforeUpdate = sustancia_activa_europaRepository.findAll().size();

        // Update the sustancia_activa_europa
        Sustancia_activa_europa updatedSustancia_activa_europa = sustancia_activa_europaRepository.findOne(sustancia_activa_europa.getId());
        updatedSustancia_activa_europa
            .real_id(UPDATED_REAL_ID)
            .tipo(UPDATED_TIPO)
            .language(UPDATED_LANGUAGE)
            .name(UPDATED_NAME);

        restSustancia_activa_europaMockMvc.perform(put("/api/sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSustancia_activa_europa)))
            .andExpect(status().isOk());

        // Validate the Sustancia_activa_europa in the database
        List<Sustancia_activa_europa> sustancia_activa_europaList = sustancia_activa_europaRepository.findAll();
        assertThat(sustancia_activa_europaList).hasSize(databaseSizeBeforeUpdate);
        Sustancia_activa_europa testSustancia_activa_europa = sustancia_activa_europaList.get(sustancia_activa_europaList.size() - 1);
        assertThat(testSustancia_activa_europa.getReal_id()).isEqualTo(UPDATED_REAL_ID);
        assertThat(testSustancia_activa_europa.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testSustancia_activa_europa.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSustancia_activa_europa.getName()).isEqualTo(UPDATED_NAME);

        // Validate the Sustancia_activa_europa in Elasticsearch
        Sustancia_activa_europa sustancia_activa_europaEs = sustancia_activa_europaSearchRepository.findOne(testSustancia_activa_europa.getId());
        assertThat(sustancia_activa_europaEs).isEqualToComparingFieldByField(testSustancia_activa_europa);
    }

    @Test
    @Transactional
    public void updateNonExistingSustancia_activa_europa() throws Exception {
        int databaseSizeBeforeUpdate = sustancia_activa_europaRepository.findAll().size();

        // Create the Sustancia_activa_europa

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSustancia_activa_europaMockMvc.perform(put("/api/sustancia-activa-europas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa)))
            .andExpect(status().isCreated());

        // Validate the Sustancia_activa_europa in the database
        List<Sustancia_activa_europa> sustancia_activa_europaList = sustancia_activa_europaRepository.findAll();
        assertThat(sustancia_activa_europaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSustancia_activa_europa() throws Exception {
        // Initialize the database
        sustancia_activa_europaRepository.saveAndFlush(sustancia_activa_europa);
        sustancia_activa_europaSearchRepository.save(sustancia_activa_europa);
        int databaseSizeBeforeDelete = sustancia_activa_europaRepository.findAll().size();

        // Get the sustancia_activa_europa
        restSustancia_activa_europaMockMvc.perform(delete("/api/sustancia-activa-europas/{id}", sustancia_activa_europa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sustancia_activa_europaExistsInEs = sustancia_activa_europaSearchRepository.exists(sustancia_activa_europa.getId());
        assertThat(sustancia_activa_europaExistsInEs).isFalse();

        // Validate the database is empty
        List<Sustancia_activa_europa> sustancia_activa_europaList = sustancia_activa_europaRepository.findAll();
        assertThat(sustancia_activa_europaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSustancia_activa_europa() throws Exception {
        // Initialize the database
        sustancia_activa_europaRepository.saveAndFlush(sustancia_activa_europa);
        sustancia_activa_europaSearchRepository.save(sustancia_activa_europa);

        // Search the sustancia_activa_europa
        restSustancia_activa_europaMockMvc.perform(get("/api/_search/sustancia-activa-europas?query=id:" + sustancia_activa_europa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sustancia_activa_europa.getId().intValue())))
            .andExpect(jsonPath("$.[*].real_id").value(hasItem(DEFAULT_REAL_ID.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sustancia_activa_europa.class);
        Sustancia_activa_europa sustancia_activa_europa1 = new Sustancia_activa_europa();
        sustancia_activa_europa1.setId(1L);
        Sustancia_activa_europa sustancia_activa_europa2 = new Sustancia_activa_europa();
        sustancia_activa_europa2.setId(sustancia_activa_europa1.getId());
        assertThat(sustancia_activa_europa1).isEqualTo(sustancia_activa_europa2);
        sustancia_activa_europa2.setId(2L);
        assertThat(sustancia_activa_europa1).isNotEqualTo(sustancia_activa_europa2);
        sustancia_activa_europa1.setId(null);
        assertThat(sustancia_activa_europa1).isNotEqualTo(sustancia_activa_europa2);
    }
}
