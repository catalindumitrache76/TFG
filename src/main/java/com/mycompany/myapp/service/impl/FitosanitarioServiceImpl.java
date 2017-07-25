package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.FitosanitarioService;
import com.mycompany.myapp.domain.Fitosanitario;
import com.mycompany.myapp.repository.FitosanitarioRepository;
import com.mycompany.myapp.service.dto.FitosanitarioDTO;
import com.mycompany.myapp.service.mapper.FitosanitarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Fitosanitario.
 */
@Service
@Transactional
public class FitosanitarioServiceImpl implements FitosanitarioService{

    private final Logger log = LoggerFactory.getLogger(FitosanitarioServiceImpl.class);

    private final FitosanitarioRepository fitosanitarioRepository;

    private final FitosanitarioMapper fitosanitarioMapper;

    public FitosanitarioServiceImpl(FitosanitarioRepository fitosanitarioRepository, FitosanitarioMapper fitosanitarioMapper) {
        this.fitosanitarioRepository = fitosanitarioRepository;
        this.fitosanitarioMapper = fitosanitarioMapper;
    }

    /**
     * Save a fitosanitario.
     *
     * @param fitosanitarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FitosanitarioDTO save(FitosanitarioDTO fitosanitarioDTO) {
        log.debug("Request to save Fitosanitario : {}", fitosanitarioDTO);
        Fitosanitario fitosanitario = fitosanitarioMapper.toEntity(fitosanitarioDTO);
        fitosanitario = fitosanitarioRepository.save(fitosanitario);
        return fitosanitarioMapper.toDto(fitosanitario);
    }

    /**
     *  Get all the fitosanitarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FitosanitarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fitosanitarios");
        return fitosanitarioRepository.findAll(pageable)
            .map(fitosanitarioMapper::toDto);
    }

    /**
     *  Get one fitosanitario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FitosanitarioDTO findOne(Long id) {
        log.debug("Request to get Fitosanitario : {}", id);
        Fitosanitario fitosanitario = fitosanitarioRepository.findOne(id);
        return fitosanitarioMapper.toDto(fitosanitario);
    }

    /**
     *  Delete the  fitosanitario by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fitosanitario : {}", id);
        fitosanitarioRepository.delete(id);
    }
}
