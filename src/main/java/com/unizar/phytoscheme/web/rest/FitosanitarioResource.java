package com.unizar.phytoscheme.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.unizar.phytoscheme.domain.Fitosanitario;
import com.unizar.phytoscheme.service.FitosanitarioService;
import com.unizar.phytoscheme.web.rest.errors.BadRequestAlertException;
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
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Fitosanitario.
 */
@RestController
@RequestMapping("/api")
public class FitosanitarioResource {

    private final Logger log = LoggerFactory.getLogger(FitosanitarioResource.class);

    private static final String ENTITY_NAME = "fitosanitario";

    private final FitosanitarioService fitosanitarioService;

    public FitosanitarioResource(FitosanitarioService fitosanitarioService) {
        this.fitosanitarioService = fitosanitarioService;
    }

    /**
     * POST  /fitosanitarios : Create a new fitosanitario.
     *
     * @param fitosanitario the fitosanitario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fitosanitario, or with status 400 (Bad Request) if the fitosanitario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fitosanitarios")
    @Timed
    public ResponseEntity<Fitosanitario> createFitosanitario(@RequestBody Fitosanitario fitosanitario) throws URISyntaxException {
        log.debug("REST request to save Fitosanitario : {}", fitosanitario);
        if (fitosanitario.getId() != null) {
            throw new BadRequestAlertException("A new fitosanitario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Fitosanitario result = fitosanitarioService.save(fitosanitario);
        return ResponseEntity.created(new URI("/api/fitosanitarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fitosanitarios : Updates an existing fitosanitario.
     *
     * @param fitosanitario the fitosanitario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fitosanitario,
     * or with status 400 (Bad Request) if the fitosanitario is not valid,
     * or with status 500 (Internal Server Error) if the fitosanitario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fitosanitarios")
    @Timed
    public ResponseEntity<Fitosanitario> updateFitosanitario(@RequestBody Fitosanitario fitosanitario) throws URISyntaxException {
        log.debug("REST request to update Fitosanitario : {}", fitosanitario);
        if (fitosanitario.getId() == null) {
            return createFitosanitario(fitosanitario);
        }
        Fitosanitario result = fitosanitarioService.save(fitosanitario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fitosanitario.getId().toString()))
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
    public ResponseEntity<List<Fitosanitario>> getAllFitosanitarios(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Fitosanitarios");
        Page<Fitosanitario> page = fitosanitarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fitosanitarios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fitosanitarios/:id : get the "id" fitosanitario.
     *
     * @param id the id of the fitosanitario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fitosanitario, or with status 404 (Not Found)
     */
    @GetMapping("/fitosanitarios/{id}")
    @Timed
    public ResponseEntity<Fitosanitario> getFitosanitario(@PathVariable Long id) {
        log.debug("REST request to get Fitosanitario : {}", id);
        Fitosanitario fitosanitario = fitosanitarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fitosanitario));
    }

    /**
     * DELETE  /fitosanitarios/:id : delete the "id" fitosanitario.
     *
     * @param id the id of the fitosanitario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fitosanitarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteFitosanitario(@PathVariable Long id) {
        log.debug("REST request to delete Fitosanitario : {}", id);
        fitosanitarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/fitosanitarios?query=:query : search for the fitosanitario corresponding
     * to the query.
     *
     * @param query the query of the fitosanitario search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/fitosanitarios")
    @Timed
    public ResponseEntity<List<Fitosanitario>> searchFitosanitarios(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Fitosanitarios for query {}", query);
        Page<Fitosanitario> page = fitosanitarioService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/fitosanitarios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
