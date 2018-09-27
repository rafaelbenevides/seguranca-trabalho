package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.domain.TrainingItem;
import br.com.erucae.segurancatrabalho.repository.TrainingItemRepository;
import br.com.erucae.segurancatrabalho.service.dto.TrainingItemDTO;
import br.com.erucae.segurancatrabalho.service.mapper.TrainingItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing TrainingItem.
 */
@Service
@Transactional
public class TrainingItemService {

    private final Logger log = LoggerFactory.getLogger(TrainingItemService.class);

    private final TrainingItemRepository trainingItemRepository;

    private final TrainingItemMapper trainingItemMapper;

    public TrainingItemService(TrainingItemRepository trainingItemRepository, TrainingItemMapper trainingItemMapper) {
        this.trainingItemRepository = trainingItemRepository;
        this.trainingItemMapper = trainingItemMapper;
    }

    /**
     * Save a trainingItem.
     *
     * @param trainingItemDTO the entity to save
     * @return the persisted entity
     */
    public TrainingItemDTO save(TrainingItemDTO trainingItemDTO) {
        log.debug("Request to save TrainingItem : {}", trainingItemDTO);
        TrainingItem trainingItem = trainingItemMapper.toEntity(trainingItemDTO);
        trainingItem = trainingItemRepository.save(trainingItem);
        return trainingItemMapper.toDto(trainingItem);
    }

    /**
     * Get all the trainingItems.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TrainingItemDTO> findAll() {
        log.debug("Request to get all TrainingItems");
        return trainingItemRepository.findAll().stream()
            .map(trainingItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one trainingItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TrainingItemDTO> findOne(Long id) {
        log.debug("Request to get TrainingItem : {}", id);
        return trainingItemRepository.findById(id)
            .map(trainingItemMapper::toDto);
    }

    /**
     * Delete the trainingItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TrainingItem : {}", id);
        trainingItemRepository.deleteById(id);
    }
}
