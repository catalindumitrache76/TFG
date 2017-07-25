package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Fitosanitario;

import com.mycompany.myapp.repository.FitosanitarioRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.FitosanitarioDTO;
import com.mycompany.myapp.service.mapper.FitosanitarioMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Fitosanitario.
 */
@RestController
@RequestMapping("/api")
public class FitosanitarioResource {

    private final Logger log = LoggerFactory.getLogger(FitosanitarioResource.class);

    private static final String ENTITY_NAME = "fitosanitario";

    private final FitosanitarioRepository fitosanitarioRepository;

    private final FitosanitarioMapper fitosanitarioMapper;

    public FitosanitarioResource(FitosanitarioRepository fitosanitarioRepository, FitosanitarioMapper fitosanitarioMapper) {
        this.fitosanitarioRepository = fitosanitarioRepository;
        this.fitosanitarioMapper = fitosanitarioMapper;
    }

    /**
     * POST  /fitosanitarios : Create a new fitosanitario.
     *
     * @param fitosanitarioDTO the fitosanitarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fitosanitarioDTO, or with status 400 (Bad Request) if the fitosanitario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fitosanitarios")
    @Timed
    public ResponseEntity<FitosanitarioDTO> createFitosanitario(@Valid @RequestBody FitosanitarioDTO fitosanitarioDTO) throws URISyntaxException {
        log.debug("REST request to save Fitosanitario : {}", fitosanitarioDTO);
        if (fitosanitarioDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fitosanitario cannot already have an ID")).body(null);
        }
        Fitosanitario fitosanitario = fitosanitarioMapper.toEntity(fitosanitarioDTO);
        fitosanitario = fitosanitarioRepository.save(fitosanitario);
        FitosanitarioDTO result = fitosanitarioMapper.toDto(fitosanitario);
        return ResponseEntity.created(new URI("/api/fitosanitarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fitosanitarios : Updates an existing fitosanitario.
     *
     * @param fitosanitarioDTO the fitosanitarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fitosanitarioDTO,
     * or with status 400 (Bad Request) if the fitosanitarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the fitosanitarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fitosanitarios")
    @Timed
    public ResponseEntity<FitosanitarioDTO> updateFitosanitario(@Valid @RequestBody FitosanitarioDTO fitosanitarioDTO) throws URISyntaxException {
        log.debug("REST request to update Fitosanitario : {}", fitosanitarioDTO);
        if (fitosanitarioDTO.getId() == null) {
            return createFitosanitario(fitosanitarioDTO);
        }
        Fitosanitario fitosanitario = fitosanitarioMapper.toEntity(fitosanitarioDTO);
        fitosanitario = fitosanitarioRepository.save(fitosanitario);
        FitosanitarioDTO result = fitosanitarioMapper.toDto(fitosanitario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fitosanitarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fitosanitarios : get all the fitosanitarios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fitosanitarios in body
     */
    @GetMapping("/fitosanitarios")
    @Timed
    public ResponseEntity<List<FitosanitarioDTO>> getAllFitosanitarios(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Fitosanitarios");
        Page<Fitosanitario> page = fitosanitarioRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fitosanitarios");
        return new ResponseEntity<>(fitosanitarioMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /fitosanitarios/:id : get the "id" fitosanitario.
     *
     * @param id the id of the fitosanitarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fitosanitarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fitosanitarios/{id}")
    @Timed
    public ResponseEntity<FitosanitarioDTO> getFitosanitario(@PathVariable Long id) {
        log.debug("REST request to get Fitosanitario : {}", id);
        Fitosanitario fitosanitario = fitosanitarioRepository.findOne(id);
        FitosanitarioDTO fitosanitarioDTO = fitosanitarioMapper.toDto(fitosanitario);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fitosanitarioDTO));
    }

    /**
     * DELETE  /fitosanitarios/:id : delete the "id" fitosanitario.
     *
     * @param id the id of the fitosanitarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fitosanitarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteFitosanitario(@PathVariable Long id) {
        log.debug("REST request to delete Fitosanitario : {}", id);
        fitosanitarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
