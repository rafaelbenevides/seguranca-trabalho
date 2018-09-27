package br.com.erucae.segurancatrabalho.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Training entity.
 */
public class TrainingDTO implements Serializable {

    private Long id;

    private Long plantId;

    private Long plantTypeId;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public Long getPlantTypeId() {
        return plantTypeId;
    }

    public void setPlantTypeId(Long plantTypeId) {
        this.plantTypeId = plantTypeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrainingDTO trainingDTO = (TrainingDTO) o;
        if (trainingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trainingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrainingDTO{" +
            "id=" + getId() +
            ", plant=" + getPlantId() +
            ", plantType=" + getPlantTypeId() +
            ", employee=" + getEmployeeId() +
            "}";
    }
}
