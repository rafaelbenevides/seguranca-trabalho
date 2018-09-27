package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.domain.TrainingType;
import br.com.erucae.segurancatrabalho.repository.TrainingTypeRepository;
import br.com.erucae.segurancatrabalho.service.dto.TrainingTypeDTO;
import br.com.erucae.segurancatrabalho.service.mapper.TrainingTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing TrainingType.
 */
@Service
@Transactional
public class TrainingTypeService {

    private final Logger log = LoggerFactory.getLogger(TrainingTypeService.class);

    private final TrainingTypeRepository trainingTypeRepository;

    private final TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    /**
     * Save a trainingType.
     *
     * @param trainingTypeDTO the entity to save
     * @return the persisted entity
     */
    public TrainingTypeDTO save(TrainingTypeDTO trainingTypeDTO) {
        log.debug("Request to save TrainingType : {}", trainingTypeDTO);
        TrainingType trainingType = trainingTypeMapper.toEntity(trainingTypeDTO);
        trainingType = trainingTypeRepository.save(trainingType);
        return trainingTypeMapper.toDto(trainingType);
    }

    /**
     * Get all the trainingTypes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TrainingTypeDTO> findAll() {
        log.debug("Request to get all TrainingTypes");
        return trainingTypeRepository.findAll().stream()
            .map(trainingTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one trainingType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TrainingTypeDTO> findOne(Long id) {
        log.debug("Request to get TrainingType : {}", id);
        return trainingTypeRepository.findById(id)
            .map(trainingTypeMapper::toDto);
    }

    /**
     * Delete the trainingType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TrainingType : {}", id);
        trainingTypeRepository.deleteById(id);
    }
}
