package br.com.erucae.segurancatrabalho.web.rest;

import br.com.erucae.segurancatrabalho.service.TrainingService;
import br.com.erucae.segurancatrabalho.service.dto.TrainingDTO;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.web.rest.util.PaginationUtil;
import br.com.erucae.segurancatrabalho.web.rest.vm.TrainingVM;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Training.
 */
@RestController
@RequestMapping("/api")
public class TrainingResource {

    private final Logger log = LoggerFactory.getLogger(TrainingResource.class);

    private static final String ENTITY_NAME = "training";

    private final TrainingService trainingService;

    public TrainingResource(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /**
     * POST  /trainings : Create a new training.
     *
     * @param trainingVM the trainingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trainingDTO, or with status 400 (Bad Request) if the training has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trainings")
    @Timed
    public ResponseEntity<TrainingVM> createTraining(@RequestBody TrainingVM trainingVM) throws URISyntaxException {
        log.debug("REST request to save Training : {}", trainingVM);
        if (trainingVM.getId() != null) {
            throw new BadRequestAlertException("A new training cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingVM result = trainingService.save(trainingVM);
        return ResponseEntity.created(new URI("/api/trainings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trainings : Updates an existing training.
     *
     * @param trainingVM the trainingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trainingDTO,
     * or with status 400 (Bad Request) if the trainingDTO is not valid,
     * or with status 500 (Internal Server Error) if the trainingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trainings")
    @Timed
    public ResponseEntity<TrainingVM> updateTraining(@RequestBody TrainingVM trainingVM) throws URISyntaxException {
        log.debug("REST request to update Training : {}", trainingVM);
        if (trainingVM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrainingVM result = trainingService.save(trainingVM);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trainingVM.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trainings : get all the trainings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trainings in body
     */
    @GetMapping("/trainings")
    @Timed
    public ResponseEntity<List<TrainingDTO>> getAllTrainings(Pageable pageable) {
        log.debug("REST request to get a page of Trainings");
        Page<TrainingDTO> page = trainingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trainings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /trainings/:id : get the "id" training.
     *
     * @param id the id of the trainingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trainingVM, or with status 404 (Not Found)
     */
    @GetMapping("/trainings/{id}")
    @Timed
    public ResponseEntity<TrainingVM> getTraining(@PathVariable Long id) {
        log.debug("REST request to get Training : {}", id);
        Optional<TrainingVM> trainingVM = trainingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingVM);
    }

    /**
     * DELETE  /trainings/:id : delete the "id" training.
     *
     * @param id the id of the trainingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trainings/{id}")
    @Timed
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        log.debug("REST request to delete Training : {}", id);
        trainingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
