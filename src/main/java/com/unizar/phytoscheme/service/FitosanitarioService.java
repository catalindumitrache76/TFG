package com.unizar.phytoscheme.service;

import com.unizar.phytoscheme.domain.Fitosanitario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Fitosanitario.
 */
public interface FitosanitarioService {

    /**
     * Save a fitosanitario.
     *
     * @param fitosanitario the entity to save
     * @return the persisted entity
     */
    Fitosanitario save(Fitosanitario fitosanitario);

    /**
     *  Get all the fitosanitarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Fitosanitario> findAll(Pageable pageable);

    /**
     *  Get the "id" fitosanitario.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Fitosanitario findOne(Long id);

    /**
     *  Delete the "id" fitosanitario.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the fitosanitario corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Fitosanitario> search(String query, Pageable pageable);
}
