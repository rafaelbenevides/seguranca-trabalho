package br.com.erucae.segurancatrabalho.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.erucae.segurancatrabalho.service.TrainingTypeService;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.service.dto.TrainingTypeDTO;
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
 * REST controller for managing TrainingType.
 */
@RestController
@RequestMapping("/api")
public class TrainingTypeResource {

    private final Logger log = LoggerFactory.getLogger(TrainingTypeResource.class);

    private static final String ENTITY_NAME = "trainingType";

    private final TrainingTypeService trainingTypeService;

    public TrainingTypeResource(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    /**
     * POST  /training-types : Create a new trainingType.
     *
     * @param trainingTypeDTO the trainingTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trainingTypeDTO, or with status 400 (Bad Request) if the trainingType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/training-types")
    @Timed
    public ResponseEntity<TrainingTypeDTO> createTrainingType(@Valid @RequestBody TrainingTypeDTO trainingTypeDTO) throws URISyntaxException {
        log.debug("REST request to save TrainingType : {}", trainingTypeDTO);
        if (trainingTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new trainingType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingTypeDTO result = trainingTypeService.save(trainingTypeDTO);
        return ResponseEntity.created(new URI("/api/training-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /training-types : Updates an existing trainingType.
     *
     * @param trainingTypeDTO the trainingTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trainingTypeDTO,
     * or with status 400 (Bad Request) if the trainingTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the trainingTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/training-types")
    @Timed
    public ResponseEntity<TrainingTypeDTO> updateTrainingType(@Valid @RequestBody TrainingTypeDTO trainingTypeDTO) throws URISyntaxException {
        log.debug("REST request to update TrainingType : {}", trainingTypeDTO);
        if (trainingTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrainingTypeDTO result = trainingTypeService.save(trainingTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trainingTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /training-types : get all the trainingTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trainingTypes in body
     */
    @GetMapping("/training-types")
    @Timed
    public List<TrainingTypeDTO> getAllTrainingTypes() {
        log.debug("REST request to get all TrainingTypes");
        return trainingTypeService.findAll();
    }

    /**
     * GET  /training-types/:id : get the "id" trainingType.
     *
     * @param id the id of the trainingTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trainingTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/training-types/{id}")
    @Timed
    public ResponseEntity<TrainingTypeDTO> getTrainingType(@PathVariable Long id) {
        log.debug("REST request to get TrainingType : {}", id);
        Optional<TrainingTypeDTO> trainingTypeDTO = trainingTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingTypeDTO);
    }

    /**
     * DELETE  /training-types/:id : delete the "id" trainingType.
     *
     * @param id the id of the trainingTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/training-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrainingType(@PathVariable Long id) {
        log.debug("REST request to delete TrainingType : {}", id);
        trainingTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
