package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.Plant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Plant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}
