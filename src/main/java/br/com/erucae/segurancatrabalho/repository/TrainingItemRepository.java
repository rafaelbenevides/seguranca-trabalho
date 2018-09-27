package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.TrainingItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrainingItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrainingItemRepository extends JpaRepository<TrainingItem, Long> {

}
