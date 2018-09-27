package br.com.erucae.segurancatrabalho.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.erucae.segurancatrabalho.service.PlantService;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.service.dto.PlantDTO;
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
 * REST controller for managing Plant.
 */
@RestController
@RequestMapping("/api")
public class PlantResource {

    private final Logger log = LoggerFactory.getLogger(PlantResource.class);

    private static final String ENTITY_NAME = "plant";

    private final PlantService plantService;

    public PlantResource(PlantService plantService) {
        this.plantService = plantService;
    }

    /**
     * POST  /plants : Create a new plant.
     *
     * @param plantDTO the plantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantDTO, or with status 400 (Bad Request) if the plant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plants")
    @Timed
    public ResponseEntity<PlantDTO> createPlant(@Valid @RequestBody PlantDTO plantDTO) throws URISyntaxException {
        log.debug("REST request to save Plant : {}", plantDTO);
        if (plantDTO.getId() != null) {
            throw new BadRequestAlertException("A new plant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantDTO result = plantService.save(plantDTO);
        return ResponseEntity.created(new URI("/api/plants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plants : Updates an existing plant.
     *
     * @param plantDTO the plantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantDTO,
     * or with status 400 (Bad Request) if the plantDTO is not valid,
     * or with status 500 (Internal Server Error) if the plantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plants")
    @Timed
    public ResponseEntity<PlantDTO> updatePlant(@Valid @RequestBody PlantDTO plantDTO) throws URISyntaxException {
        log.debug("REST request to update Plant : {}", plantDTO);
        if (plantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantDTO result = plantService.save(plantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plants : get all the plants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of plants in body
     */
    @GetMapping("/plants")
    @Timed
    public List<PlantDTO> getAllPlants() {
        log.debug("REST request to get all Plants");
        return plantService.findAll();
    }

    /**
     * GET  /plants/:id : get the "id" plant.
     *
     * @param id the id of the plantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plants/{id}")
    @Timed
    public ResponseEntity<PlantDTO> getPlant(@PathVariable Long id) {
        log.debug("REST request to get Plant : {}", id);
        Optional<PlantDTO> plantDTO = plantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plantDTO);
    }

    /**
     * DELETE  /plants/:id : delete the "id" plant.
     *
     * @param id the id of the plantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plants/{id}")
    @Timed
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        log.debug("REST request to delete Plant : {}", id);
        plantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
