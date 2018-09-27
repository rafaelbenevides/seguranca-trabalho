package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.PlantType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantTypeRepository extends JpaRepository<PlantType, Long> {

}
