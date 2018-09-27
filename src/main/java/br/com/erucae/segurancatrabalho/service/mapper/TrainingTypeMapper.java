package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.TrainingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TrainingType and its DTO TrainingTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrainingTypeMapper extends EntityMapper<TrainingTypeDTO, TrainingType> {



    default TrainingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrainingType trainingType = new TrainingType();
        trainingType.setId(id);
        return trainingType;
    }
}
