package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.domain.PlantType;
import br.com.erucae.segurancatrabalho.repository.PlantTypeRepository;
import br.com.erucae.segurancatrabalho.service.dto.PlantTypeDTO;
import br.com.erucae.segurancatrabalho.service.mapper.PlantTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing PlantType.
 */
@Service
@Transactional
public class PlantTypeService {

    private final Logger log = LoggerFactory.getLogger(PlantTypeService.class);

    private final PlantTypeRepository plantTypeRepository;

    private final PlantTypeMapper plantTypeMapper;

    public PlantTypeService(PlantTypeRepository plantTypeRepository, PlantTypeMapper plantTypeMapper) {
        this.plantTypeRepository = plantTypeRepository;
        this.plantTypeMapper = plantTypeMapper;
    }

    /**
     * Save a plantType.
     *
     * @param plantTypeDTO the entity to save
     * @return the persisted entity
     */
    public PlantTypeDTO save(PlantTypeDTO plantTypeDTO) {
        log.debug("Request to save PlantType : {}", plantTypeDTO);
        PlantType plantType = plantTypeMapper.toEntity(plantTypeDTO);
        plantType = plantTypeRepository.save(plantType);
        return plantTypeMapper.toDto(plantType);
    }

    /**
     * Get all the plantTypes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PlantTypeDTO> findAll() {
        log.debug("Request to get all PlantTypes");
        return plantTypeRepository.findAll().stream()
            .map(plantTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one plantType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PlantTypeDTO> findOne(Long id) {
        log.debug("Request to get PlantType : {}", id);
        return plantTypeRepository.findById(id)
            .map(plantTypeMapper::toDto);
    }

    /**
     * Delete the plantType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PlantType : {}", id);
        plantTypeRepository.deleteById(id);
    }
}
