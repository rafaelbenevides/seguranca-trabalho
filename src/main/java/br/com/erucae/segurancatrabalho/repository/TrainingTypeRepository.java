package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.TrainingType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrainingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {

}
