package br.com.erucae.segurancatrabalho.service;

import br.com.erucae.segurancatrabalho.domain.JobRole;
import br.com.erucae.segurancatrabalho.repository.JobRoleRepository;
import br.com.erucae.segurancatrabalho.service.dto.JobRoleDTO;
import br.com.erucae.segurancatrabalho.service.mapper.JobRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing JobRole.
 */
@Service
@Transactional
public class JobRoleService {

    private final Logger log = LoggerFactory.getLogger(JobRoleService.class);

    private final JobRoleRepository jobRoleRepository;

    private final JobRoleMapper jobRoleMapper;

    public JobRoleService(JobRoleRepository jobRoleRepository, JobRoleMapper jobRoleMapper) {
        this.jobRoleRepository = jobRoleRepository;
        this.jobRoleMapper = jobRoleMapper;
    }

    /**
     * Save a jobRole.
     *
     * @param jobRoleDTO the entity to save
     * @return the persisted entity
     */
    public JobRoleDTO save(JobRoleDTO jobRoleDTO) {
        log.debug("Request to save JobRole : {}", jobRoleDTO);
        JobRole jobRole = jobRoleMapper.toEntity(jobRoleDTO);
        jobRole = jobRoleRepository.save(jobRole);
        return jobRoleMapper.toDto(jobRole);
    }

    /**
     * Get all the jobRoles.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<JobRoleDTO> findAll() {
        log.debug("Request to get all JobRoles");
        return jobRoleRepository.findAll().stream()
            .map(jobRoleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one jobRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<JobRoleDTO> findOne(Long id) {
        log.debug("Request to get JobRole : {}", id);
        return jobRoleRepository.findById(id)
            .map(jobRoleMapper::toDto);
    }

    /**
     * Delete the jobRole by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete JobRole : {}", id);
        jobRoleRepository.deleteById(id);
    }
}
