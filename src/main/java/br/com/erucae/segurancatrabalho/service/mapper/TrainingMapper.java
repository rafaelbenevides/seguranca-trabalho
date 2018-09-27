package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.TrainingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Training and its DTO TrainingDTO.
 */
@Mapper(componentModel = "spring", uses = {PlantMapper.class, PlantTypeMapper.class, EmployeeMapper.class})
public interface TrainingMapper extends EntityMapper<TrainingDTO, Training> {

    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plantType.id", target = "plantTypeId")
    @Mapping(source = "employee.id", target = "employeeId")
    TrainingDTO toDto(Training training);

    @Mapping(target = "items", ignore = true)
    @Mapping(source = "plantId", target = "plant")
    @Mapping(source = "plantTypeId", target = "plantType")
    @Mapping(source = "employeeId", target = "employee")
    Training toEntity(TrainingDTO trainingDTO);

    default Training fromId(Long id) {
        if (id == null) {
            return null;
        }
        Training training = new Training();
        training.setId(id);
        return training;
    }
}
