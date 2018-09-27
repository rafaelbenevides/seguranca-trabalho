package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.domain.Plant;
import br.com.erucae.segurancatrabalho.repository.PlantRepository;
import br.com.erucae.segurancatrabalho.service.dto.PlantDTO;
import br.com.erucae.segurancatrabalho.service.mapper.PlantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Plant.
 */
@Service
@Transactional
public class PlantService {

    private final Logger log = LoggerFactory.getLogger(PlantService.class);

    private final PlantRepository plantRepository;

    private final PlantMapper plantMapper;

    public PlantService(PlantRepository plantRepository, PlantMapper plantMapper) {
        this.plantRepository = plantRepository;
        this.plantMapper = plantMapper;
    }

    /**
     * Save a plant.
     *
     * @param plantDTO the entity to save
     * @return the persisted entity
     */
    public PlantDTO save(PlantDTO plantDTO) {
        log.debug("Request to save Plant : {}", plantDTO);
        Plant plant = plantMapper.toEntity(plantDTO);
        plant = plantRepository.save(plant);
        return plantMapper.toDto(plant);
    }

    /**
     * Get all the plants.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PlantDTO> findAll() {
        log.debug("Request to get all Plants");
        return plantRepository.findAll().stream()
            .map(plantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one plant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PlantDTO> findOne(Long id) {
        log.debug("Request to get Plant : {}", id);
        return plantRepository.findById(id)
            .map(plantMapper::toDto);
    }

    /**
     * Delete the plant by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Plant : {}", id);
        plantRepository.deleteById(id);
    }
}
