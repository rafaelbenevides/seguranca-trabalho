package br.com.erucae.segurancatrabalho.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Plant entity.
 */
public class PlantDTO implements Serializable {

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

        PlantDTO plantDTO = (PlantDTO) o;
        if (plantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), plantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
