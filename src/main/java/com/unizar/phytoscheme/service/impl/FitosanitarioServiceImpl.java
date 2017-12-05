package com.unizar.phytoscheme.service.impl;

import com.unizar.phytoscheme.service.FitosanitarioService;
import com.unizar.phytoscheme.domain.Fitosanitario;
import com.unizar.phytoscheme.repository.FitosanitarioRepository;
import com.unizar.phytoscheme.repository.search.FitosanitarioSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Fitosanitario.
 */
@Service
@Transactional
public class FitosanitarioServiceImpl implements FitosanitarioService{

    private final Logger log = LoggerFactory.getLogger(FitosanitarioServiceImpl.class);

    private final FitosanitarioRepository fitosanitarioRepository;

    private final FitosanitarioSearchRepository fitosanitarioSearchRepository;

    public FitosanitarioServiceImpl(FitosanitarioRepository fitosanitarioRepository, FitosanitarioSearchRepository fitosanitarioSearchRepository) {
        this.fitosanitarioRepository = fitosanitarioRepository;
        this.fitosanitarioSearchRepository = fitosanitarioSearchRepository;
    }

    /**
     * Save a fitosanitario.
     *
     * @param fitosanitario the entity to save
     * @return the persisted entity
     */
    @Override
    public Fitosanitario save(Fitosanitario fitosanitario) {
        log.debug("Request to save Fitosanitario : {}", fitosanitario);
        Fitosanitario result = fitosanitarioRepository.save(fitosanitario);
        fitosanitarioSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the fitosanitarios.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Fitosanitario> findAll(Pageable pageable) {
        log.debug("Request to get all Fitosanitarios");
        return fitosanitarioRepository.findAll(pageable);
    }

    /**
     * Get one fitosanitario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Fitosanitario findOne(Long id) {
        log.debug("Request to get Fitosanitario : {}", id);
        return fitosanitarioRepository.findOne(id);
    }

    /**
     * Delete the fitosanitario by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fitosanitario : {}", id);
        fitosanitarioRepository.delete(id);
        fitosanitarioSearchRepository.delete(id);
    }

    /**
     * Search for the fitosanitario corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Fitosanitario> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Fitosanitarios for query {}", query);
        Page<Fitosanitario> result = fitosanitarioSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
