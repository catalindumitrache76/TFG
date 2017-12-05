package com.unizar.phytoscheme.web.rest;

import com.unizar.phytoscheme.TfgjHipsterApp;

import com.unizar.phytoscheme.domain.Fitosanitario;
import com.unizar.phytoscheme.repository.FitosanitarioRepository;
import com.unizar.phytoscheme.service.FitosanitarioService;
import com.unizar.phytoscheme.repository.search.FitosanitarioSearchRepository;
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
 * Test class for the FitosanitarioResource REST controller.
 *
 * @see FitosanitarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgjHipsterApp.class)
public class FitosanitarioResourceIntTest {

    private static final String DEFAULT_NUM_REGISTRO = "AAAAAAAAAA";
    private static final String UPDATED_NUM_REGISTRO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_COMERCIAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_COMERCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULADO = "AAAAAAAAAA";
    private static final String UPDATED_FORMULADO = "BBBBBBBBBB";

    @Autowired
    private FitosanitarioRepository fitosanitarioRepository;

    @Autowired
    private FitosanitarioService fitosanitarioService;

    @Autowired
    private FitosanitarioSearchRepository fitosanitarioSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFitosanitarioMockMvc;

    private Fitosanitario fitosanitario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FitosanitarioResource fitosanitarioResource = new FitosanitarioResource(fitosanitarioService);
        this.restFitosanitarioMockMvc = MockMvcBuilders.standaloneSetup(fitosanitarioResource)
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
    public static Fitosanitario createEntity(EntityManager em) {
        Fitosanitario fitosanitario = new Fitosanitario()
            .num_registro(DEFAULT_NUM_REGISTRO)
            .nombre_comercial(DEFAULT_NOMBRE_COMERCIAL)
            .titular(DEFAULT_TITULAR)
            .formulado(DEFAULT_FORMULADO);
        return fitosanitario;
    }

    @Before
    public void initTest() {
        fitosanitarioSearchRepository.deleteAll();
        fitosanitario = createEntity(em);
    }

    @Test
    @Transactional
    public void createFitosanitario() throws Exception {
        int databaseSizeBeforeCreate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario
        restFitosanitarioMockMvc.perform(post("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeCreate + 1);
        Fitosanitario testFitosanitario = fitosanitarioList.get(fitosanitarioList.size() - 1);
        assertThat(testFitosanitario.getNum_registro()).isEqualTo(DEFAULT_NUM_REGISTRO);
        assertThat(testFitosanitario.getNombre_comercial()).isEqualTo(DEFAULT_NOMBRE_COMERCIAL);
        assertThat(testFitosanitario.getTitular()).isEqualTo(DEFAULT_TITULAR);
        assertThat(testFitosanitario.getFormulado()).isEqualTo(DEFAULT_FORMULADO);

        // Validate the Fitosanitario in Elasticsearch
        Fitosanitario fitosanitarioEs = fitosanitarioSearchRepository.findOne(testFitosanitario.getId());
        assertThat(fitosanitarioEs).isEqualToComparingFieldByField(testFitosanitario);
    }

    @Test
    @Transactional
    public void createFitosanitarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario with an existing ID
        fitosanitario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFitosanitarioMockMvc.perform(post("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario)))
            .andExpect(status().isBadRequest());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFitosanitarios() throws Exception {
        // Initialize the database
        fitosanitarioRepository.saveAndFlush(fitosanitario);

        // Get all the fitosanitarioList
        restFitosanitarioMockMvc.perform(get("/api/fitosanitarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fitosanitario.getId().intValue())))
            .andExpect(jsonPath("$.[*].num_registro").value(hasItem(DEFAULT_NUM_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].nombre_comercial").value(hasItem(DEFAULT_NOMBRE_COMERCIAL.toString())))
            .andExpect(jsonPath("$.[*].titular").value(hasItem(DEFAULT_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].formulado").value(hasItem(DEFAULT_FORMULADO.toString())));
    }

    @Test
    @Transactional
    public void getFitosanitario() throws Exception {
        // Initialize the database
        fitosanitarioRepository.saveAndFlush(fitosanitario);

        // Get the fitosanitario
        restFitosanitarioMockMvc.perform(get("/api/fitosanitarios/{id}", fitosanitario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fitosanitario.getId().intValue()))
            .andExpect(jsonPath("$.num_registro").value(DEFAULT_NUM_REGISTRO.toString()))
            .andExpect(jsonPath("$.nombre_comercial").value(DEFAULT_NOMBRE_COMERCIAL.toString()))
            .andExpect(jsonPath("$.titular").value(DEFAULT_TITULAR.toString()))
            .andExpect(jsonPath("$.formulado").value(DEFAULT_FORMULADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFitosanitario() throws Exception {
        // Get the fitosanitario
        restFitosanitarioMockMvc.perform(get("/api/fitosanitarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFitosanitario() throws Exception {
        // Initialize the database
        fitosanitarioService.save(fitosanitario);

        int databaseSizeBeforeUpdate = fitosanitarioRepository.findAll().size();

        // Update the fitosanitario
        Fitosanitario updatedFitosanitario = fitosanitarioRepository.findOne(fitosanitario.getId());
        updatedFitosanitario
            .num_registro(UPDATED_NUM_REGISTRO)
            .nombre_comercial(UPDATED_NOMBRE_COMERCIAL)
            .titular(UPDATED_TITULAR)
            .formulado(UPDATED_FORMULADO);

        restFitosanitarioMockMvc.perform(put("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFitosanitario)))
            .andExpect(status().isOk());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeUpdate);
        Fitosanitario testFitosanitario = fitosanitarioList.get(fitosanitarioList.size() - 1);
        assertThat(testFitosanitario.getNum_registro()).isEqualTo(UPDATED_NUM_REGISTRO);
        assertThat(testFitosanitario.getNombre_comercial()).isEqualTo(UPDATED_NOMBRE_COMERCIAL);
        assertThat(testFitosanitario.getTitular()).isEqualTo(UPDATED_TITULAR);
        assertThat(testFitosanitario.getFormulado()).isEqualTo(UPDATED_FORMULADO);

        // Validate the Fitosanitario in Elasticsearch
        Fitosanitario fitosanitarioEs = fitosanitarioSearchRepository.findOne(testFitosanitario.getId());
        assertThat(fitosanitarioEs).isEqualToComparingFieldByField(testFitosanitario);
    }

    @Test
    @Transactional
    public void updateNonExistingFitosanitario() throws Exception {
        int databaseSizeBeforeUpdate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFitosanitarioMockMvc.perform(put("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitario)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFitosanitario() throws Exception {
        // Initialize the database
        fitosanitarioService.save(fitosanitario);

        int databaseSizeBeforeDelete = fitosanitarioRepository.findAll().size();

        // Get the fitosanitario
        restFitosanitarioMockMvc.perform(delete("/api/fitosanitarios/{id}", fitosanitario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean fitosanitarioExistsInEs = fitosanitarioSearchRepository.exists(fitosanitario.getId());
        assertThat(fitosanitarioExistsInEs).isFalse();

        // Validate the database is empty
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFitosanitario() throws Exception {
        // Initialize the database
        fitosanitarioService.save(fitosanitario);

        // Search the fitosanitario
        restFitosanitarioMockMvc.perform(get("/api/_search/fitosanitarios?query=id:" + fitosanitario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fitosanitario.getId().intValue())))
            .andExpect(jsonPath("$.[*].num_registro").value(hasItem(DEFAULT_NUM_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].nombre_comercial").value(hasItem(DEFAULT_NOMBRE_COMERCIAL.toString())))
            .andExpect(jsonPath("$.[*].titular").value(hasItem(DEFAULT_TITULAR.toString())))
            .andExpect(jsonPath("$.[*].formulado").value(hasItem(DEFAULT_FORMULADO.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fitosanitario.class);
        Fitosanitario fitosanitario1 = new Fitosanitario();
        fitosanitario1.setId(1L);
        Fitosanitario fitosanitario2 = new Fitosanitario();
        fitosanitario2.setId(fitosanitario1.getId());
        assertThat(fitosanitario1).isEqualTo(fitosanitario2);
        fitosanitario2.setId(2L);
        assertThat(fitosanitario1).isNotEqualTo(fitosanitario2);
        fitosanitario1.setId(null);
        assertThat(fitosanitario1).isNotEqualTo(fitosanitario2);
    }
}
