package br.com.erucae.segurancatrabalho.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.erucae.segurancatrabalho.service.PlantTypeService;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.service.dto.PlantTypeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PlantType.
 */
@RestController
@RequestMapping("/api")
public class PlantTypeResource {

    private final Logger log = LoggerFactory.getLogger(PlantTypeResource.class);

    private static final String ENTITY_NAME = "plantType";

    private final PlantTypeService plantTypeService;

    public PlantTypeResource(PlantTypeService plantTypeService) {
        this.plantTypeService = plantTypeService;
    }

    /**
     * POST  /plant-types : Create a new plantType.
     *
     * @param plantTypeDTO the plantTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantTypeDTO, or with status 400 (Bad Request) if the plantType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plant-types")
    @Timed
    public ResponseEntity<PlantTypeDTO> createPlantType(@Valid @RequestBody PlantTypeDTO plantTypeDTO) throws URISyntaxException {
        log.debug("REST request to save PlantType : {}", plantTypeDTO);
        if (plantTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new plantType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantTypeDTO result = plantTypeService.save(plantTypeDTO);
        return ResponseEntity.created(new URI("/api/plant-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plant-types : Updates an existing plantType.
     *
     * @param plantTypeDTO the plantTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantTypeDTO,
     * or with status 400 (Bad Request) if the plantTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the plantTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plant-types")
    @Timed
    public ResponseEntity<PlantTypeDTO> updatePlantType(@Valid @RequestBody PlantTypeDTO plantTypeDTO) throws URISyntaxException {
        log.debug("REST request to update PlantType : {}", plantTypeDTO);
        if (plantTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantTypeDTO result = plantTypeService.save(plantTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plant-types : get all the plantTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of plantTypes in body
     */
    @GetMapping("/plant-types")
    @Timed
    public List<PlantTypeDTO> getAllPlantTypes() {
        log.debug("REST request to get all PlantTypes");
        return plantTypeService.findAll();
    }

    /**
     * GET  /plant-types/:id : get the "id" plantType.
     *
     * @param id the id of the plantTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plant-types/{id}")
    @Timed
    public ResponseEntity<PlantTypeDTO> getPlantType(@PathVariable Long id) {
        log.debug("REST request to get PlantType : {}", id);
        Optional<PlantTypeDTO> plantTypeDTO = plantTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plantTypeDTO);
    }

    /**
     * DELETE  /plant-types/:id : delete the "id" plantType.
     *
     * @param id the id of the plantTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plant-types/{id}")
    @Timed
    public ResponseEntity<Void> deletePlantType(@PathVariable Long id) {
        log.debug("REST request to delete PlantType : {}", id);
        plantTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
