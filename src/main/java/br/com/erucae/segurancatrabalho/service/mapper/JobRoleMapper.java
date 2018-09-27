package br.com.erucae.segurancatrabalho.service.mapper;

import br.com.erucae.segurancatrabalho.domain.*;
import br.com.erucae.segurancatrabalho.service.dto.JobRoleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobRole and its DTO JobRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JobRoleMapper extends EntityMapper<JobRoleDTO, JobRole> {



    default JobRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobRole jobRole = new JobRole();
        jobRole.setId(id);
        return jobRole;
    }
}
