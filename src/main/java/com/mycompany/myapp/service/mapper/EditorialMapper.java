package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EditorialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Editorial and its DTO EditorialDTO.
 */
@Mapper(componentModel = "spring", uses = {FitosanitarioMapper.class, })
public interface EditorialMapper extends EntityMapper <EditorialDTO, Editorial> {

    @Mapping(source = "fitosanitario.id", target = "fitosanitarioId")
    EditorialDTO toDto(Editorial editorial); 

    @Mapping(source = "fitosanitarioId", target = "fitosanitario")
    Editorial toEntity(EditorialDTO editorialDTO); 
    default Editorial fromId(Long id) {
        if (id == null) {
            return null;
        }
        Editorial editorial = new Editorial();
        editorial.setId(id);
        return editorial;
    }
}
