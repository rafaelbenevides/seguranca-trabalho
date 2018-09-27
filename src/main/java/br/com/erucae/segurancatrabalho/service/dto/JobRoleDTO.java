package br.com.erucae.segurancatrabalho.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JobRole entity.
 */
public class JobRoleDTO implements Serializable {

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

        JobRoleDTO jobRoleDTO = (JobRoleDTO) o;
        if (jobRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobRoleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
