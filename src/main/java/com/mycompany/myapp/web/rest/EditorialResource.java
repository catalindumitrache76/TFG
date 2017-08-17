package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Editorial;

import com.mycompany.myapp.repository.EditorialRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.EditorialDTO;
import com.mycompany.myapp.service.mapper.EditorialMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Editorial.
 */
@RestController
@RequestMapping("/api")
public class EditorialResource {

    private final Logger log = LoggerFactory.getLogger(EditorialResource.class);

    private static final String ENTITY_NAME = "editorial";

    private final EditorialRepository editorialRepository;

    private final EditorialMapper editorialMapper;

    public EditorialResource(EditorialRepository editorialRepository, EditorialMapper editorialMapper) {
        this.editorialRepository = editorialRepository;
        this.editorialMapper = editorialMapper;
    }

    /**
     * POST  /editorials : Create a new editorial.
     *
     * @param editorialDTO the editorialDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new editorialDTO, or with status 400 (Bad Request) if the editorial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/editorials")
    @Timed
    public ResponseEntity<EditorialDTO> createEditorial(@Valid @RequestBody EditorialDTO editorialDTO) throws URISyntaxException {
        log.debug("REST request to save Editorial : {}", editorialDTO);
        if (editorialDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new editorial cannot already have an ID")).body(null);
        }
        Editorial editorial = editorialMapper.toEntity(editorialDTO);
        editorial = editorialRepository.save(editorial);
        EditorialDTO result = editorialMapper.toDto(editorial);
        return ResponseEntity.created(new URI("/api/editorials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /editorials : Updates an existing editorial.
     *
     * @param editorialDTO the editorialDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated editorialDTO,
     * or with status 400 (Bad Request) if the editorialDTO is not valid,
     * or with status 500 (Internal Server Error) if the editorialDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/editorials")
    @Timed
    public ResponseEntity<EditorialDTO> updateEditorial(@Valid @RequestBody EditorialDTO editorialDTO) throws URISyntaxException {
        log.debug("REST request to update Editorial : {}", editorialDTO);
        if (editorialDTO.getId() == null) {
            return createEditorial(editorialDTO);
        }
        Editorial editorial = editorialMapper.toEntity(editorialDTO);
        editorial = editorialRepository.save(editorial);
        EditorialDTO result = editorialMapper.toDto(editorial);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, editorialDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /editorials : get all the editorials.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of editorials in body
     */
    @GetMapping("/editorials")
    @Timed
    public List<EditorialDTO> getAllEditorials() {
        log.debug("REST request to get all Editorials");
        List<Editorial> editorials = editorialRepository.findAll();
        return editorialMapper.toDto(editorials);
    }

    /**
     * GET  /editorials/:id : get the "id" editorial.
     *
     * @param id the id of the editorialDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the editorialDTO, or with status 404 (Not Found)
     */
    @GetMapping("/editorials/{id}")
    @Timed
    public ResponseEntity<EditorialDTO> getEditorial(@PathVariable Long id) {
        log.debug("REST request to get Editorial : {}", id);
        Editorial editorial = editorialRepository.findOne(id);
        EditorialDTO editorialDTO = editorialMapper.toDto(editorial);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(editorialDTO));
    }

    /**
     * DELETE  /editorials/:id : delete the "id" editorial.
     *
     * @param id the id of the editorialDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/editorials/{id}")
    @Timed
    public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
        log.debug("REST request to delete Editorial : {}", id);
        editorialRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
