package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.PlantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Plant and its DTO PlantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlantMapper extends EntityMapper<PlantDTO, Plant> {



    default Plant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Plant plant = new Plant();
        plant.setId(id);
        return plant;
    }
}
