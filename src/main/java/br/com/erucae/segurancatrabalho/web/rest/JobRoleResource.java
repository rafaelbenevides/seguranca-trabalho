package br.com.erucae.segurancatrabalho.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.erucae.segurancatrabalho.service.JobRoleService;
import br.com.erucae.segurancatrabalho.web.rest.errors.BadRequestAlertException;
import br.com.erucae.segurancatrabalho.web.rest.util.HeaderUtil;
import br.com.erucae.segurancatrabalho.service.dto.JobRoleDTO;
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
 * REST controller for managing JobRole.
 */
@RestController
@RequestMapping("/api")
public class JobRoleResource {

    private final Logger log = LoggerFactory.getLogger(JobRoleResource.class);

    private static final String ENTITY_NAME = "jobRole";

    private final JobRoleService jobRoleService;

    public JobRoleResource(JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    /**
     * POST  /job-roles : Create a new jobRole.
     *
     * @param jobRoleDTO the jobRoleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobRoleDTO, or with status 400 (Bad Request) if the jobRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-roles")
    @Timed
    public ResponseEntity<JobRoleDTO> createJobRole(@Valid @RequestBody JobRoleDTO jobRoleDTO) throws URISyntaxException {
        log.debug("REST request to save JobRole : {}", jobRoleDTO);
        if (jobRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobRoleDTO result = jobRoleService.save(jobRoleDTO);
        return ResponseEntity.created(new URI("/api/job-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-roles : Updates an existing jobRole.
     *
     * @param jobRoleDTO the jobRoleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobRoleDTO,
     * or with status 400 (Bad Request) if the jobRoleDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobRoleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-roles")
    @Timed
    public ResponseEntity<JobRoleDTO> updateJobRole(@Valid @RequestBody JobRoleDTO jobRoleDTO) throws URISyntaxException {
        log.debug("REST request to update JobRole : {}", jobRoleDTO);
        if (jobRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobRoleDTO result = jobRoleService.save(jobRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-roles : get all the jobRoles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobRoles in body
     */
    @GetMapping("/job-roles")
    @Timed
    public List<JobRoleDTO> getAllJobRoles() {
        log.debug("REST request to get all JobRoles");
        return jobRoleService.findAll();
    }

    /**
     * GET  /job-roles/:id : get the "id" jobRole.
     *
     * @param id the id of the jobRoleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobRoleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-roles/{id}")
    @Timed
    public ResponseEntity<JobRoleDTO> getJobRole(@PathVariable Long id) {
        log.debug("REST request to get JobRole : {}", id);
        Optional<JobRoleDTO> jobRoleDTO = jobRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobRoleDTO);
    }

    /**
     * DELETE  /job-roles/:id : delete the "id" jobRole.
     *
     * @param id the id of the jobRoleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobRole(@PathVariable Long id) {
        log.debug("REST request to delete JobRole : {}", id);
        jobRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
