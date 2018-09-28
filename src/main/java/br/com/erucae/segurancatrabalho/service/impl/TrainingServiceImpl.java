package br.com.erucae.segurancatrabalho.service.impl;

import br.com.erucae.segurancatrabalho.domain.TrainingItem;
import br.com.erucae.segurancatrabalho.service.TrainingService;
import br.com.erucae.segurancatrabalho.domain.Training;
import br.com.erucae.segurancatrabalho.repository.TrainingRepository;
import br.com.erucae.segurancatrabalho.service.dto.TrainingDTO;
import br.com.erucae.segurancatrabalho.service.mapper.TrainingMapper;
import br.com.erucae.segurancatrabalho.service.mapper.TrainingMapperVM;
import br.com.erucae.segurancatrabalho.web.rest.vm.TrainingVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing Training.
 */
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingRepository trainingRepository;

    private final TrainingMapper trainingMapper;

    private final TrainingMapperVM trainingMapperVM;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper,
                               TrainingMapperVM trainingMapperVM) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.trainingMapperVM = trainingMapperVM;
    }

    /**
     * Save a training.
     *
     * @param trainingVM the entity to save
     * @return the persisted entity
     */
    @Override
    public TrainingVM save(TrainingVM trainingVM) {
        log.debug("Request to save Training : {}", trainingVM);
        Training training = resolveTraining(trainingVM);
        training = trainingRepository.save(training);
        return trainingMapperVM.toDto(training);
    }

    /**
     * Get all the trainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrainingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trainings");
        return trainingRepository.findAll(pageable)
            .map(trainingMapper::toDto);
    }


    /**
     * Get one training by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrainingVM> findOne(Long id) {
        log.debug("Request to get Training : {}", id);
        return trainingRepository.findById(id)
            .map(trainingMapperVM::toDto);
    }

    /**
     * Delete the training by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Training : {}", id);
        trainingRepository.deleteById(id);
    }

    private Training resolveTraining(TrainingVM trainingVM) {
        final Training training = trainingMapper.toEntity(trainingVM);
        training.getItems().forEach(trainingItem -> trainingItem.setTraining(training));
        return training;
    }
}
