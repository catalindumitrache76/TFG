package com.unizar.phytoscheme.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unizar.phytoscheme.domain.Sustancia_activa_europa_con_traza;

import com.unizar.phytoscheme.repository.Sustancia_activa_europa_con_trazaRepository;
import com.unizar.phytoscheme.repository.search.Sustancia_activa_europa_con_trazaSearchRepository;
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
 * REST controller for managing Sustancia_activa_europa_con_traza.
 */
@RestController
@RequestMapping("/api")
public class Sustancia_activa_europa_con_trazaResource {

    private final Logger log = LoggerFactory.getLogger(Sustancia_activa_europa_con_trazaResource.class);

    private static final String ENTITY_NAME = "sustancia_activa_europa_con_traza";

    private final Sustancia_activa_europa_con_trazaRepository sustancia_activa_europa_con_trazaRepository;

    private final Sustancia_activa_europa_con_trazaSearchRepository sustancia_activa_europa_con_trazaSearchRepository;

    public Sustancia_activa_europa_con_trazaResource(Sustancia_activa_europa_con_trazaRepository sustancia_activa_europa_con_trazaRepository, Sustancia_activa_europa_con_trazaSearchRepository sustancia_activa_europa_con_trazaSearchRepository) {
        this.sustancia_activa_europa_con_trazaRepository = sustancia_activa_europa_con_trazaRepository;
        this.sustancia_activa_europa_con_trazaSearchRepository = sustancia_activa_europa_con_trazaSearchRepository;
    }

    /**
     * POST  /sustancia-activa-europa-con-trazas : Create a new sustancia_activa_europa_con_traza.
     *
     * @param sustancia_activa_europa_con_traza the sustancia_activa_europa_con_traza to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sustancia_activa_europa_con_traza, or with status 400 (Bad Request) if the sustancia_activa_europa_con_traza has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sustancia-activa-europa-con-trazas")
    @Timed
    public ResponseEntity<Sustancia_activa_europa_con_traza> createSustancia_activa_europa_con_traza(@RequestBody Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza) throws URISyntaxException {
        log.debug("REST request to save Sustancia_activa_europa_con_traza : {}", sustancia_activa_europa_con_traza);
        if (sustancia_activa_europa_con_traza.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sustancia_activa_europa_con_traza cannot already have an ID")).body(null);
        }
        Sustancia_activa_europa_con_traza result = sustancia_activa_europa_con_trazaRepository.save(sustancia_activa_europa_con_traza);
        sustancia_activa_europa_con_trazaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sustancia-activa-europa-con-trazas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sustancia-activa-europa-con-trazas : Updates an existing sustancia_activa_europa_con_traza.
     *
     * @param sustancia_activa_europa_con_traza the sustancia_activa_europa_con_traza to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sustancia_activa_europa_con_traza,
     * or with status 400 (Bad Request) if the sustancia_activa_europa_con_traza is not valid,
     * or with status 500 (Internal Server Error) if the sustancia_activa_europa_con_traza couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sustancia-activa-europa-con-trazas")
    @Timed
    public ResponseEntity<Sustancia_activa_europa_con_traza> updateSustancia_activa_europa_con_traza(@RequestBody Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza) throws URISyntaxException {
        log.debug("REST request to update Sustancia_activa_europa_con_traza : {}", sustancia_activa_europa_con_traza);
        if (sustancia_activa_europa_con_traza.getId() == null) {
            return createSustancia_activa_europa_con_traza(sustancia_activa_europa_con_traza);
        }
        Sustancia_activa_europa_con_traza result = sustancia_activa_europa_con_trazaRepository.save(sustancia_activa_europa_con_traza);
        sustancia_activa_europa_con_trazaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sustancia_activa_europa_con_traza.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sustancia-activa-europa-con-trazas : get all the sustancia_activa_europa_con_trazas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sustancia_activa_europa_con_trazas in body
     */
    @GetMapping("/sustancia-activa-europa-con-trazas")
    @Timed
    public ResponseEntity<List<Sustancia_activa_europa_con_traza>> getAllSustancia_activa_europa_con_trazas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Sustancia_activa_europa_con_trazas");
        Page<Sustancia_activa_europa_con_traza> page = sustancia_activa_europa_con_trazaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sustancia-activa-europa-con-trazas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sustancia-activa-europa-con-trazas/:id : get the "id" sustancia_activa_europa_con_traza.
     *
     * @param id the id of the sustancia_activa_europa_con_traza to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sustancia_activa_europa_con_traza, or with status 404 (Not Found)
     */
    @GetMapping("/sustancia-activa-europa-con-trazas/{id}")
    @Timed
    public ResponseEntity<Sustancia_activa_europa_con_traza> getSustancia_activa_europa_con_traza(@PathVariable Long id) {
        log.debug("REST request to get Sustancia_activa_europa_con_traza : {}", id);
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza = sustancia_activa_europa_con_trazaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sustancia_activa_europa_con_traza));
    }

    /**
     * DELETE  /sustancia-activa-europa-con-trazas/:id : delete the "id" sustancia_activa_europa_con_traza.
     *
     * @param id the id of the sustancia_activa_europa_con_traza to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sustancia-activa-europa-con-trazas/{id}")
    @Timed
    public ResponseEntity<Void> deleteSustancia_activa_europa_con_traza(@PathVariable Long id) {
        log.debug("REST request to delete Sustancia_activa_europa_con_traza : {}", id);
        sustancia_activa_europa_con_trazaRepository.delete(id);
        sustancia_activa_europa_con_trazaSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sustancia-activa-europa-con-trazas?query=:query : search for the sustancia_activa_europa_con_traza corresponding
     * to the query.
     *
     * @param query the query of the sustancia_activa_europa_con_traza search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sustancia-activa-europa-con-trazas")
    @Timed
    public ResponseEntity<List<Sustancia_activa_europa_con_traza>> searchSustancia_activa_europa_con_trazas(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Sustancia_activa_europa_con_trazas for query {}", query);
        Page<Sustancia_activa_europa_con_traza> page = sustancia_activa_europa_con_trazaSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sustancia-activa-europa-con-trazas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
