package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TfgJHipsterApp;

import com.mycompany.myapp.domain.Fitosanitario;
import com.mycompany.myapp.repository.FitosanitarioRepository;
import com.mycompany.myapp.service.dto.FitosanitarioDTO;
import com.mycompany.myapp.service.mapper.FitosanitarioMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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
 * Test class for the FitosanitarioResource REST controller.
 *
 * @see FitosanitarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TfgJHipsterApp.class)
public class FitosanitarioResourceIntTest {

    private static final String DEFAULT_NUMREGISTRO = "AAAAAAAAAA";
    private static final String UPDATED_NUMREGISTRO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRECOMERCIAL = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRECOMERCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULADO = "AAAAAAAAAA";
    private static final String UPDATED_FORMULADO = "BBBBBBBBBB";

    @Autowired
    private FitosanitarioRepository fitosanitarioRepository;

    @Autowired
    private FitosanitarioMapper fitosanitarioMapper;

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
        FitosanitarioResource fitosanitarioResource = new FitosanitarioResource(fitosanitarioRepository, fitosanitarioMapper);
        this.restFitosanitarioMockMvc = MockMvcBuilders.standaloneSetup(fitosanitarioResource)
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
    public static Fitosanitario createEntity(EntityManager em) {
        Fitosanitario fitosanitario = new Fitosanitario()
            .numregistro(DEFAULT_NUMREGISTRO)
            .nombrecomercial(DEFAULT_NOMBRECOMERCIAL)
            .titular(DEFAULT_TITULAR)
            .formulado(DEFAULT_FORMULADO);
        return fitosanitario;
    }

    @Before
    public void initTest() {
        fitosanitario = createEntity(em);
    }

    @Test
    @Transactional
    public void createFitosanitario() throws Exception {
        int databaseSizeBeforeCreate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(fitosanitario);
        restFitosanitarioMockMvc.perform(post("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeCreate + 1);
        Fitosanitario testFitosanitario = fitosanitarioList.get(fitosanitarioList.size() - 1);
        assertThat(testFitosanitario.getNumregistro()).isEqualTo(DEFAULT_NUMREGISTRO);
        assertThat(testFitosanitario.getNombrecomercial()).isEqualTo(DEFAULT_NOMBRECOMERCIAL);
        assertThat(testFitosanitario.getTitular()).isEqualTo(DEFAULT_TITULAR);
        assertThat(testFitosanitario.getFormulado()).isEqualTo(DEFAULT_FORMULADO);
    }

    @Test
    @Transactional
    public void createFitosanitarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario with an existing ID
        fitosanitario.setId(1L);
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(fitosanitario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFitosanitarioMockMvc.perform(post("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumregistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = fitosanitarioRepository.findAll().size();
        // set the field null
        fitosanitario.setNumregistro(null);

        // Create the Fitosanitario, which fails.
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(fitosanitario);

        restFitosanitarioMockMvc.perform(post("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitarioDTO)))
            .andExpect(status().isBadRequest());

        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeTest);
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
            .andExpect(jsonPath("$.[*].numregistro").value(hasItem(DEFAULT_NUMREGISTRO.toString())))
            .andExpect(jsonPath("$.[*].nombrecomercial").value(hasItem(DEFAULT_NOMBRECOMERCIAL.toString())))
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
            .andExpect(jsonPath("$.numregistro").value(DEFAULT_NUMREGISTRO.toString()))
            .andExpect(jsonPath("$.nombrecomercial").value(DEFAULT_NOMBRECOMERCIAL.toString()))
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
        fitosanitarioRepository.saveAndFlush(fitosanitario);
        int databaseSizeBeforeUpdate = fitosanitarioRepository.findAll().size();

        // Update the fitosanitario
        Fitosanitario updatedFitosanitario = fitosanitarioRepository.findOne(fitosanitario.getId());
        updatedFitosanitario
            .numregistro(UPDATED_NUMREGISTRO)
            .nombrecomercial(UPDATED_NOMBRECOMERCIAL)
            .titular(UPDATED_TITULAR)
            .formulado(UPDATED_FORMULADO);
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(updatedFitosanitario);

        restFitosanitarioMockMvc.perform(put("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitarioDTO)))
            .andExpect(status().isOk());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeUpdate);
        Fitosanitario testFitosanitario = fitosanitarioList.get(fitosanitarioList.size() - 1);
        assertThat(testFitosanitario.getNumregistro()).isEqualTo(UPDATED_NUMREGISTRO);
        assertThat(testFitosanitario.getNombrecomercial()).isEqualTo(UPDATED_NOMBRECOMERCIAL);
        assertThat(testFitosanitario.getTitular()).isEqualTo(UPDATED_TITULAR);
        assertThat(testFitosanitario.getFormulado()).isEqualTo(UPDATED_FORMULADO);
    }

    @Test
    @Transactional
    public void updateNonExistingFitosanitario() throws Exception {
        int databaseSizeBeforeUpdate = fitosanitarioRepository.findAll().size();

        // Create the Fitosanitario
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(fitosanitario);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFitosanitarioMockMvc.perform(put("/api/fitosanitarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fitosanitarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Fitosanitario in the database
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFitosanitario() throws Exception {
        // Initialize the database
        fitosanitarioRepository.saveAndFlush(fitosanitario);
        int databaseSizeBeforeDelete = fitosanitarioRepository.findAll().size();

        // Get the fitosanitario
        restFitosanitarioMockMvc.perform(delete("/api/fitosanitarios/{id}", fitosanitario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fitosanitario> fitosanitarioList = fitosanitarioRepository.findAll();
        assertThat(fitosanitarioList).hasSize(databaseSizeBeforeDelete - 1);
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

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FitosanitarioDTO.class);
        FitosanitarioDTO fitosanitarioDTO1 = new FitosanitarioDTO();
        fitosanitarioDTO1.setId(1L);
        FitosanitarioDTO fitosanitarioDTO2 = new FitosanitarioDTO();
        assertThat(fitosanitarioDTO1).isNotEqualTo(fitosanitarioDTO2);
        fitosanitarioDTO2.setId(fitosanitarioDTO1.getId());
        assertThat(fitosanitarioDTO1).isEqualTo(fitosanitarioDTO2);
        fitosanitarioDTO2.setId(2L);
        assertThat(fitosanitarioDTO1).isNotEqualTo(fitosanitarioDTO2);
        fitosanitarioDTO1.setId(null);
        assertThat(fitosanitarioDTO1).isNotEqualTo(fitosanitarioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fitosanitarioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fitosanitarioMapper.fromId(null)).isNull();
    }
}
