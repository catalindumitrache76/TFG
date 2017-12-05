package com.unizar.phytoscheme.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unizar.phytoscheme.domain.Sustancia_activa_europa;

import com.unizar.phytoscheme.repository.Sustancia_activa_europaRepository;
import com.unizar.phytoscheme.repository.search.Sustancia_activa_europaSearchRepository;
import com.unizar.phytoscheme.web.rest.errors.BadRequestAlertException;
import com.unizar.phytoscheme.web.rest.util.HeaderUtil;
import com.unizar.phytoscheme.web.rest.util.PaginationUtil;
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
 * REST controller for managing Sustancia_activa_europa.
 */
@RestController
@RequestMapping("/api")
public class Sustancia_activa_europaResource {

    private final Logger log = LoggerFactory.getLogger(Sustancia_activa_europaResource.class);

    private static final String ENTITY_NAME = "sustancia_activa_europa";

    private final Sustancia_activa_europaRepository sustancia_activa_europaRepository;

    private final Sustancia_activa_europaSearchRepository sustancia_activa_europaSearchRepository;

    public Sustancia_activa_europaResource(Sustancia_activa_europaRepository sustancia_activa_europaRepository, Sustancia_activa_europaSearchRepository sustancia_activa_europaSearchRepository) {
        this.sustancia_activa_europaRepository = sustancia_activa_europaRepository;
        this.sustancia_activa_europaSearchRepository = sustancia_activa_europaSearchRepository;
    }

    /**
     * POST  /sustancia-activa-europas : Create a new sustancia_activa_europa.
     *
     * @param sustancia_activa_europa the sustancia_activa_europa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sustancia_activa_europa, or with status 400 (Bad Request) if the sustancia_activa_europa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sustancia-activa-europas")
    @Timed
    public ResponseEntity<Sustancia_activa_europa> createSustancia_activa_europa(@RequestBody Sustancia_activa_europa sustancia_activa_europa) throws URISyntaxException {
        log.debug("REST request to save Sustancia_activa_europa : {}", sustancia_activa_europa);
        if (sustancia_activa_europa.getId() != null) {
            throw new BadRequestAlertException("A new sustancia_activa_europa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sustancia_activa_europa result = sustancia_activa_europaRepository.save(sustancia_activa_europa);
        sustancia_activa_europaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sustancia-activa-europas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sustancia-activa-europas : Updates an existing sustancia_activa_europa.
     *
     * @param sustancia_activa_europa the sustancia_activa_europa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sustancia_activa_europa,
     * or with status 400 (Bad Request) if the sustancia_activa_europa is not valid,
     * or with status 500 (Internal Server Error) if the sustancia_activa_europa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sustancia-activa-europas")
    @Timed
    public ResponseEntity<Sustancia_activa_europa> updateSustancia_activa_europa(@RequestBody Sustancia_activa_europa sustancia_activa_europa) throws URISyntaxException {
        log.debug("REST request to update Sustancia_activa_europa : {}", sustancia_activa_europa);
        if (sustancia_activa_europa.getId() == null) {
            return createSustancia_activa_europa(sustancia_activa_europa);
        }
        Sustancia_activa_europa result = sustancia_activa_europaRepository.save(sustancia_activa_europa);
        sustancia_activa_europaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sustancia_activa_europa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sustancia-activa-europas : get all the sustancia_activa_europas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sustancia_activa_europas in body
     */
    @GetMapping("/sustancia-activa-europas")
    @Timed
    public ResponseEntity<List<Sustancia_activa_europa>> getAllSustancia_activa_europas(Pageable pageable) {
        log.debug("REST request to get a page of Sustancia_activa_europas");
        Page<Sustancia_activa_europa> page = sustancia_activa_europaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sustancia-activa-europas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sustancia-activa-europas/:id : get the "id" sustancia_activa_europa.
     *
     * @param id the id of the sustancia_activa_europa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sustancia_activa_europa, or with status 404 (Not Found)
     */
    @GetMapping("/sustancia-activa-europas/{id}")
    @Timed
    public ResponseEntity<Sustancia_activa_europa> getSustancia_activa_europa(@PathVariable Long id) {
        log.debug("REST request to get Sustancia_activa_europa : {}", id);
        Sustancia_activa_europa sustancia_activa_europa = sustancia_activa_europaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sustancia_activa_europa));
    }

    /**
     * DELETE  /sustancia-activa-europas/:id : delete the "id" sustancia_activa_europa.
     *
     * @param id the id of the sustancia_activa_europa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sustancia-activa-europas/{id}")
    @Timed
    public ResponseEntity<Void> deleteSustancia_activa_europa(@PathVariable Long id) {
        log.debug("REST request to delete Sustancia_activa_europa : {}", id);
        sustancia_activa_europaRepository.delete(id);
        sustancia_activa_europaSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sustancia-activa-europas?query=:query : search for the sustancia_activa_europa corresponding
     * to the query.
     *
     * @param query the query of the sustancia_activa_europa search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sustancia-activa-europas")
    @Timed
    public ResponseEntity<List<Sustancia_activa_europa>> searchSustancia_activa_europas(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Sustancia_activa_europas for query {}", query);
        Page<Sustancia_activa_europa> page = sustancia_activa_europaSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sustancia-activa-europas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
