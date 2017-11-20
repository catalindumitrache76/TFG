package com.unizar.phytoscheme.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unizar.phytoscheme.domain.Fitosanitario_sustancia_activa_europa;

import com.unizar.phytoscheme.repository.Fitosanitario_sustancia_activa_europaRepository;
import com.unizar.phytoscheme.repository.search.Fitosanitario_sustancia_activa_europaSearchRepository;
import com.unizar.phytoscheme.web.rest.util.HeaderUtil;
import com.unizar.phytoscheme.web.rest.util.PaginationUtil;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Fitosanitario_sustancia_activa_europa.
 */
@RestController
@RequestMapping("/api")
public class Fitosanitario_sustancia_activa_europaResource {

    private final Logger log = LoggerFactory.getLogger(Fitosanitario_sustancia_activa_europaResource.class);

    private static final String ENTITY_NAME = "fitosanitario_sustancia_activa_europa";

    private final Fitosanitario_sustancia_activa_europaRepository fitosanitario_sustancia_activa_europaRepository;

    private final Fitosanitario_sustancia_activa_europaSearchRepository fitosanitario_sustancia_activa_europaSearchRepository;

    public Fitosanitario_sustancia_activa_europaResource(Fitosanitario_sustancia_activa_europaRepository fitosanitario_sustancia_activa_europaRepository, Fitosanitario_sustancia_activa_europaSearchRepository fitosanitario_sustancia_activa_europaSearchRepository) {
        this.fitosanitario_sustancia_activa_europaRepository = fitosanitario_sustancia_activa_europaRepository;
        this.fitosanitario_sustancia_activa_europaSearchRepository = fitosanitario_sustancia_activa_europaSearchRepository;
    }

    /**
     * POST  /fitosanitario-sustancia-activa-europas : Create a new fitosanitario_sustancia_activa_europa.
     *
     * @param fitosanitario_sustancia_activa_europa the fitosanitario_sustancia_activa_europa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fitosanitario_sustancia_activa_europa, or with status 400 (Bad Request) if the fitosanitario_sustancia_activa_europa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fitosanitario-sustancia-activa-europas")
    @Timed
    public ResponseEntity<Fitosanitario_sustancia_activa_europa> createFitosanitario_sustancia_activa_europa(@RequestBody Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa) throws URISyntaxException {
        log.debug("REST request to save Fitosanitario_sustancia_activa_europa : {}", fitosanitario_sustancia_activa_europa);
        if (fitosanitario_sustancia_activa_europa.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new fitosanitario_sustancia_activa_europa cannot already have an ID")).body(null);
        }
        Fitosanitario_sustancia_activa_europa result = fitosanitario_sustancia_activa_europaRepository.save(fitosanitario_sustancia_activa_europa);
        fitosanitario_sustancia_activa_europaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/fitosanitario-sustancia-activa-europas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fitosanitario-sustancia-activa-europas : Updates an existing fitosanitario_sustancia_activa_europa.
     *
     * @param fitosanitario_sustancia_activa_europa the fitosanitario_sustancia_activa_europa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fitosanitario_sustancia_activa_europa,
     * or with status 400 (Bad Request) if the fitosanitario_sustancia_activa_europa is not valid,
     * or with status 500 (Internal Server Error) if the fitosanitario_sustancia_activa_europa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fitosanitario-sustancia-activa-europas")
    @Timed
    public ResponseEntity<Fitosanitario_sustancia_activa_europa> updateFitosanitario_sustancia_activa_europa(@RequestBody Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa) throws URISyntaxException {
        log.debug("REST request to update Fitosanitario_sustancia_activa_europa : {}", fitosanitario_sustancia_activa_europa);
        if (fitosanitario_sustancia_activa_europa.getId() == null) {
            return createFitosanitario_sustancia_activa_europa(fitosanitario_sustancia_activa_europa);
        }
        Fitosanitario_sustancia_activa_europa result = fitosanitario_sustancia_activa_europaRepository.save(fitosanitario_sustancia_activa_europa);
        fitosanitario_sustancia_activa_europaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fitosanitario_sustancia_activa_europa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fitosanitario-sustancia-activa-europas : get all the fitosanitario_sustancia_activa_europas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fitosanitario_sustancia_activa_europas in body
     */
    @GetMapping("/fitosanitario-sustancia-activa-europas")
    @Timed
    public ResponseEntity<List<Fitosanitario_sustancia_activa_europa>> getAllFitosanitario_sustancia_activa_europas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Fitosanitario_sustancia_activa_europas");
        Page<Fitosanitario_sustancia_activa_europa> page = fitosanitario_sustancia_activa_europaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fitosanitario-sustancia-activa-europas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fitosanitario-sustancia-activa-europas/:id : get the "id" fitosanitario_sustancia_activa_europa.
     *
     * @param id the id of the fitosanitario_sustancia_activa_europa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fitosanitario_sustancia_activa_europa, or with status 404 (Not Found)
     */
    @GetMapping("/fitosanitario-sustancia-activa-europas/{id}")
    @Timed
    public ResponseEntity<Fitosanitario_sustancia_activa_europa> getFitosanitario_sustancia_activa_europa(@PathVariable Long id) {
        log.debug("REST request to get Fitosanitario_sustancia_activa_europa : {}", id);
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa = fitosanitario_sustancia_activa_europaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fitosanitario_sustancia_activa_europa));
    }

    /**
     * DELETE  /fitosanitario-sustancia-activa-europas/:id : delete the "id" fitosanitario_sustancia_activa_europa.
     *
     * @param id the id of the fitosanitario_sustancia_activa_europa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fitosanitario-sustancia-activa-europas/{id}")
    @Timed
    public ResponseEntity<Void> deleteFitosanitario_sustancia_activa_europa(@PathVariable Long id) {
        log.debug("REST request to delete Fitosanitario_sustancia_activa_europa : {}", id);
        fitosanitario_sustancia_activa_europaRepository.delete(id);
        fitosanitario_sustancia_activa_europaSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/fitosanitario-sustancia-activa-europas?query=:query : search for the fitosanitario_sustancia_activa_europa corresponding
     * to the query.
     *
     * @param query the query of the fitosanitario_sustancia_activa_europa search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/fitosanitario-sustancia-activa-europas")
    @Timed
    public ResponseEntity<List<Fitosanitario_sustancia_activa_europa>> searchFitosanitario_sustancia_activa_europas(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Fitosanitario_sustancia_activa_europas for query {}", query);
        Page<Fitosanitario_sustancia_activa_europa> page = fitosanitario_sustancia_activa_europaSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/fitosanitario-sustancia-activa-europas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
