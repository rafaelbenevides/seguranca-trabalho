package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.TrainingItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TrainingItem and its DTO TrainingItemDTO.
 */
@Mapper(componentModel = "spring", uses = {TrainingMapper.class, TrainingTypeMapper.class})
public interface TrainingItemMapper extends EntityMapper<TrainingItemDTO, TrainingItem> {

    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "trainingType.id", target = "trainingTypeId")
    TrainingItemDTO toDto(TrainingItem trainingItem);

    @Mapping(source = "trainingId", target = "training")
    @Mapping(source = "trainingTypeId", target = "trainingType")
    TrainingItem toEntity(TrainingItemDTO trainingItemDTO);

    default TrainingItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrainingItem trainingItem = new TrainingItem();
        trainingItem.setId(id);
        return trainingItem;
    }
}
