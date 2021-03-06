package br.com.erucae.segurancatrabalho.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PlantType entity.
 */
public class PlantTypeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 120)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlantTypeDTO plantTypeDTO = (PlantTypeDTO) o;
        if (plantTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
