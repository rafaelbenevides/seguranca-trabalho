
package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.Training;
import br.com.erucae.segurancatrabalho.web.rest.vm.TrainingVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Training and its VM TrainingVM.
 */
@Mapper(componentModel = "spring", uses = {PlantMapper.class, PlantTypeMapper.class, EmployeeMapper.class})
public interface TrainingMapperVM extends EntityMapper<TrainingVM, Training> {

    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plantType.id", target = "plantTypeId")
    @Mapping(source = "employee.id", target = "employeeId")
    TrainingVM toDto(Training training);

    @Mapping(target = "items", ignore = true)
    @Mapping(source = "plantId", target = "plant")
    @Mapping(source = "plantTypeId", target = "plantType")
    @Mapping(source = "employeeId", target = "employee")
    Training toEntity(TrainingVM trainingVM);

    default Training fromId(Long id) {
        if (id == null) {
            return null;
        }
        Training training = new Training();
        training.setId(id);
        return training;
    }
}
