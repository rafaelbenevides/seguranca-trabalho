package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.service.dto.TrainingDTO;

import br.com.erucae.segurancatrabalho.web.rest.vm.TrainingVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Training.
 */
public interface TrainingService {

    /**
     * Save a training.
     *
     * @param trainingVM the entity to save
     * @return the persisted entity
     */
    TrainingVM save(TrainingVM trainingVM);

    /**
     * Get all the trainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TrainingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" training.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TrainingVM> findOne(Long id);

    /**
     * Delete the "id" training.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
