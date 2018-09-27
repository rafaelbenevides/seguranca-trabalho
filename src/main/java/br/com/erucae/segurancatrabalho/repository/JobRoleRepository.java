package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.JobRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JobRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Long> {

}
