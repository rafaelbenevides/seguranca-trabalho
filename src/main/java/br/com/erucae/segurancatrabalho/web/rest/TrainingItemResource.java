package br.com.erucae.segurancatrabalho.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.erucae.segurancatrabalho.service.TrainingItemService;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.service.dto.TrainingItemDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TrainingItem.
 */
@RestController
@RequestMapping("/api")
public class TrainingItemResource {

    private final Logger log = LoggerFactory.getLogger(TrainingItemResource.class);

    private static final String ENTITY_NAME = "trainingItem";

    private final TrainingItemService trainingItemService;

    public TrainingItemResource(TrainingItemService trainingItemService) {
        this.trainingItemService = trainingItemService;
    }

    /**
     * POST  /training-items : Create a new trainingItem.
     *
     * @param trainingItemDTO the trainingItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trainingItemDTO, or with status 400 (Bad Request) if the trainingItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/training-items")
    @Timed
    public ResponseEntity<TrainingItemDTO> createTrainingItem(@RequestBody TrainingItemDTO trainingItemDTO) throws URISyntaxException {
        log.debug("REST request to save TrainingItem : {}", trainingItemDTO);
        if (trainingItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new trainingItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingItemDTO result = trainingItemService.save(trainingItemDTO);
        return ResponseEntity.created(new URI("/api/training-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /training-items : Updates an existing trainingItem.
     *
     * @param trainingItemDTO the trainingItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trainingItemDTO,
     * or with status 400 (Bad Request) if the trainingItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the trainingItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/training-items")
    @Timed
    public ResponseEntity<TrainingItemDTO> updateTrainingItem(@RequestBody TrainingItemDTO trainingItemDTO) throws URISyntaxException {
        log.debug("REST request to update TrainingItem : {}", trainingItemDTO);
        if (trainingItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrainingItemDTO result = trainingItemService.save(trainingItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trainingItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /training-items : get all the trainingItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trainingItems in body
     */
    @GetMapping("/training-items")
    @Timed
    public List<TrainingItemDTO> getAllTrainingItems() {
        log.debug("REST request to get all TrainingItems");
        return trainingItemService.findAll();
    }

    /**
     * GET  /training-items/:id : get the "id" trainingItem.
     *
     * @param id the id of the trainingItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trainingItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/training-items/{id}")
    @Timed
    public ResponseEntity<TrainingItemDTO> getTrainingItem(@PathVariable Long id) {
        log.debug("REST request to get TrainingItem : {}", id);
        Optional<TrainingItemDTO> trainingItemDTO = trainingItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingItemDTO);
    }

    /**
     * DELETE  /training-items/:id : delete the "id" trainingItem.
     *
     * @param id the id of the trainingItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/training-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrainingItem(@PathVariable Long id) {
        log.debug("REST request to delete TrainingItem : {}", id);
        trainingItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
