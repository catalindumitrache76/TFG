package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FitosanitarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fitosanitario and its DTO FitosanitarioDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FitosanitarioMapper extends EntityMapper <FitosanitarioDTO, Fitosanitario> {
    
    @Mapping(target = "perteneces", ignore = true)
    Fitosanitario toEntity(FitosanitarioDTO fitosanitarioDTO); 
    default Fitosanitario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fitosanitario fitosanitario = new Fitosanitario();
        fitosanitario.setId(id);
        return fitosanitario;
    }
}
