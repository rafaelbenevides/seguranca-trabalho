package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.PlantTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlantType and its DTO PlantTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlantTypeMapper extends EntityMapper<PlantTypeDTO, PlantType> {



    default PlantType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlantType plantType = new PlantType();
        plantType.setId(id);
        return plantType;
    }
}
