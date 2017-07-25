package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.FitosanitarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Fitosanitario.
 */
public interface FitosanitarioService {

    /**
     * Save a fitosanitario.
     *
     * @param fitosanitarioDTO the entity to save
     * @return the persisted entity
     */
    FitosanitarioDTO save(FitosanitarioDTO fitosanitarioDTO);

    /**
     *  Get all the fitosanitarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<FitosanitarioDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" fitosanitario.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FitosanitarioDTO findOne(Long id);

    /**
     *  Delete the "id" fitosanitario.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
