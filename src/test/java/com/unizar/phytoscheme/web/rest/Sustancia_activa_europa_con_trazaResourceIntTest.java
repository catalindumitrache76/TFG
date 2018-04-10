package com.unizar.phytoscheme.web.rest;

import com.unizar.phytoscheme.TfgjHipsterApp;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa_con_traza;
import com.unizar.phytoscheme.repository.Sustancia_activa_europa_con_trazaRepository;
import com.unizar.phytoscheme.repository.search.Sustancia_activa_europa_con_trazaSearchRepository;
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
 * Test class for the Sustancia_activa_europa_con_trazaResource REST controller.
 *
 * @see Sustancia_activa_europa_con_trazaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgjHipsterApp.class)
public class Sustancia_activa_europa_con_trazaResourceIntTest {

    private static final String DEFAULT_REAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_REAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    @Autowired
    private Sustancia_activa_europa_con_trazaRepository sustancia_activa_europa_con_trazaRepository;

    @Autowired
    private Sustancia_activa_europa_con_trazaSearchRepository sustancia_activa_europa_con_trazaSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSustancia_activa_europa_con_trazaMockMvc;

    private Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Sustancia_activa_europa_con_trazaResource sustancia_activa_europa_con_trazaResource = new Sustancia_activa_europa_con_trazaResource(sustancia_activa_europa_con_trazaRepository, sustancia_activa_europa_con_trazaSearchRepository);
        this.restSustancia_activa_europa_con_trazaMockMvc = MockMvcBuilders.standaloneSetup(sustancia_activa_europa_con_trazaResource)
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
    public static Sustancia_activa_europa_con_traza createEntity(EntityManager em) {
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza = new Sustancia_activa_europa_con_traza()
            .real_id(DEFAULT_REAL_ID)
            .tipo(DEFAULT_TIPO)
            .language(DEFAULT_LANGUAGE)
            .name(DEFAULT_NAME)
            .source(DEFAULT_SOURCE)
            .date(DEFAULT_DATE);
        return sustancia_activa_europa_con_traza;
    }

    @Before
    public void initTest() {
        sustancia_activa_europa_con_trazaSearchRepository.deleteAll();
        sustancia_activa_europa_con_traza = createEntity(em);
    }

    @Test
    @Transactional
    public void createSustancia_activa_europa_con_traza() throws Exception {
        int databaseSizeBeforeCreate = sustancia_activa_europa_con_trazaRepository.findAll().size();

        // Create the Sustancia_activa_europa_con_traza
        restSustancia_activa_europa_con_trazaMockMvc.perform(post("/api/sustancia-activa-europa-con-trazas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa_con_traza)))
            .andExpect(status().isCreated());

        // Validate the Sustancia_activa_europa_con_traza in the database
        List<Sustancia_activa_europa_con_traza> sustancia_activa_europa_con_trazaList = sustancia_activa_europa_con_trazaRepository.findAll();
        assertThat(sustancia_activa_europa_con_trazaList).hasSize(databaseSizeBeforeCreate + 1);
        Sustancia_activa_europa_con_traza testSustancia_activa_europa_con_traza = sustancia_activa_europa_con_trazaList.get(sustancia_activa_europa_con_trazaList.size() - 1);
        assertThat(testSustancia_activa_europa_con_traza.getReal_id()).isEqualTo(DEFAULT_REAL_ID);
        assertThat(testSustancia_activa_europa_con_traza.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testSustancia_activa_europa_con_traza.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testSustancia_activa_europa_con_traza.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSustancia_activa_europa_con_traza.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testSustancia_activa_europa_con_traza.getDate()).isEqualTo(DEFAULT_DATE);

        // Validate the Sustancia_activa_europa_con_traza in Elasticsearch
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_trazaEs = sustancia_activa_europa_con_trazaSearchRepository.findOne(testSustancia_activa_europa_con_traza.getId());
        assertThat(sustancia_activa_europa_con_trazaEs).isEqualToComparingFieldByField(testSustancia_activa_europa_con_traza);
    }

    @Test
    @Transactional
    public void createSustancia_activa_europa_con_trazaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sustancia_activa_europa_con_trazaRepository.findAll().size();

        // Create the Sustancia_activa_europa_con_traza with an existing ID
        sustancia_activa_europa_con_traza.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSustancia_activa_europa_con_trazaMockMvc.perform(post("/api/sustancia-activa-europa-con-trazas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa_con_traza)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Sustancia_activa_europa_con_traza> sustancia_activa_europa_con_trazaList = sustancia_activa_europa_con_trazaRepository.findAll();
        assertThat(sustancia_activa_europa_con_trazaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSustancia_activa_europa_con_trazas() throws Exception {
        // Initialize the database
        sustancia_activa_europa_con_trazaRepository.saveAndFlush(sustancia_activa_europa_con_traza);

        // Get all the sustancia_activa_europa_con_trazaList
        restSustancia_activa_europa_con_trazaMockMvc.perform(get("/api/sustancia-activa-europa-con-trazas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sustancia_activa_europa_con_traza.getId().intValue())))
            .andExpect(jsonPath("$.[*].real_id").value(hasItem(DEFAULT_REAL_ID.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getSustancia_activa_europa_con_traza() throws Exception {
        // Initialize the database
        sustancia_activa_europa_con_trazaRepository.saveAndFlush(sustancia_activa_europa_con_traza);

        // Get the sustancia_activa_europa_con_traza
        restSustancia_activa_europa_con_trazaMockMvc.perform(get("/api/sustancia-activa-europa-con-trazas/{id}", sustancia_activa_europa_con_traza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sustancia_activa_europa_con_traza.getId().intValue()))
            .andExpect(jsonPath("$.real_id").value(DEFAULT_REAL_ID.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSustancia_activa_europa_con_traza() throws Exception {
        // Get the sustancia_activa_europa_con_traza
        restSustancia_activa_europa_con_trazaMockMvc.perform(get("/api/sustancia-activa-europa-con-trazas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSustancia_activa_europa_con_traza() throws Exception {
        // Initialize the database
        sustancia_activa_europa_con_trazaRepository.saveAndFlush(sustancia_activa_europa_con_traza);
        sustancia_activa_europa_con_trazaSearchRepository.save(sustancia_activa_europa_con_traza);
        int databaseSizeBeforeUpdate = sustancia_activa_europa_con_trazaRepository.findAll().size();

        // Update the sustancia_activa_europa_con_traza
        Sustancia_activa_europa_con_traza updatedSustancia_activa_europa_con_traza = sustancia_activa_europa_con_trazaRepository.findOne(sustancia_activa_europa_con_traza.getId());
        updatedSustancia_activa_europa_con_traza
            .real_id(UPDATED_REAL_ID)
            .tipo(UPDATED_TIPO)
            .language(UPDATED_LANGUAGE)
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .date(UPDATED_DATE);

        restSustancia_activa_europa_con_trazaMockMvc.perform(put("/api/sustancia-activa-europa-con-trazas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSustancia_activa_europa_con_traza)))
            .andExpect(status().isOk());

        // Validate the Sustancia_activa_europa_con_traza in the database
        List<Sustancia_activa_europa_con_traza> sustancia_activa_europa_con_trazaList = sustancia_activa_europa_con_trazaRepository.findAll();
        assertThat(sustancia_activa_europa_con_trazaList).hasSize(databaseSizeBeforeUpdate);
        Sustancia_activa_europa_con_traza testSustancia_activa_europa_con_traza = sustancia_activa_europa_con_trazaList.get(sustancia_activa_europa_con_trazaList.size() - 1);
        assertThat(testSustancia_activa_europa_con_traza.getReal_id()).isEqualTo(UPDATED_REAL_ID);
        assertThat(testSustancia_activa_europa_con_traza.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testSustancia_activa_europa_con_traza.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSustancia_activa_europa_con_traza.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSustancia_activa_europa_con_traza.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testSustancia_activa_europa_con_traza.getDate()).isEqualTo(UPDATED_DATE);

        // Validate the Sustancia_activa_europa_con_traza in Elasticsearch
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_trazaEs = sustancia_activa_europa_con_trazaSearchRepository.findOne(testSustancia_activa_europa_con_traza.getId());
        assertThat(sustancia_activa_europa_con_trazaEs).isEqualToComparingFieldByField(testSustancia_activa_europa_con_traza);
    }

    @Test
    @Transactional
    public void updateNonExistingSustancia_activa_europa_con_traza() throws Exception {
        int databaseSizeBeforeUpdate = sustancia_activa_europa_con_trazaRepository.findAll().size();

        // Create the Sustancia_activa_europa_con_traza

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSustancia_activa_europa_con_trazaMockMvc.perform(put("/api/sustancia-activa-europa-con-trazas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sustancia_activa_europa_con_traza)))
            .andExpect(status().isCreated());

        // Validate the Sustancia_activa_europa_con_traza in the database
        List<Sustancia_activa_europa_con_traza> sustancia_activa_europa_con_trazaList = sustancia_activa_europa_con_trazaRepository.findAll();
        assertThat(sustancia_activa_europa_con_trazaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSustancia_activa_europa_con_traza() throws Exception {
        // Initialize the database
        sustancia_activa_europa_con_trazaRepository.saveAndFlush(sustancia_activa_europa_con_traza);
        sustancia_activa_europa_con_trazaSearchRepository.save(sustancia_activa_europa_con_traza);
        int databaseSizeBeforeDelete = sustancia_activa_europa_con_trazaRepository.findAll().size();

        // Get the sustancia_activa_europa_con_traza
        restSustancia_activa_europa_con_trazaMockMvc.perform(delete("/api/sustancia-activa-europa-con-trazas/{id}", sustancia_activa_europa_con_traza.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sustancia_activa_europa_con_trazaExistsInEs = sustancia_activa_europa_con_trazaSearchRepository.exists(sustancia_activa_europa_con_traza.getId());
        assertThat(sustancia_activa_europa_con_trazaExistsInEs).isFalse();

        // Validate the database is empty
        List<Sustancia_activa_europa_con_traza> sustancia_activa_europa_con_trazaList = sustancia_activa_europa_con_trazaRepository.findAll();
        assertThat(sustancia_activa_europa_con_trazaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSustancia_activa_europa_con_traza() throws Exception {
        // Initialize the database
        sustancia_activa_europa_con_trazaRepository.saveAndFlush(sustancia_activa_europa_con_traza);
        sustancia_activa_europa_con_trazaSearchRepository.save(sustancia_activa_europa_con_traza);

        // Search the sustancia_activa_europa_con_traza
        restSustancia_activa_europa_con_trazaMockMvc.perform(get("/api/_search/sustancia-activa-europa-con-trazas?query=id:" + sustancia_activa_europa_con_traza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sustancia_activa_europa_con_traza.getId().intValue())))
            .andExpect(jsonPath("$.[*].real_id").value(hasItem(DEFAULT_REAL_ID.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sustancia_activa_europa_con_traza.class);
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza1 = new Sustancia_activa_europa_con_traza();
        sustancia_activa_europa_con_traza1.setId(1L);
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza2 = new Sustancia_activa_europa_con_traza();
        sustancia_activa_europa_con_traza2.setId(sustancia_activa_europa_con_traza1.getId());
        assertThat(sustancia_activa_europa_con_traza1).isEqualTo(sustancia_activa_europa_con_traza2);
        sustancia_activa_europa_con_traza2.setId(2L);
        assertThat(sustancia_activa_europa_con_traza1).isNotEqualTo(sustancia_activa_europa_con_traza2);
        sustancia_activa_europa_con_traza1.setId(null);
        assertThat(sustancia_activa_europa_con_traza1).isNotEqualTo(sustancia_activa_europa_con_traza2);
    }
}
