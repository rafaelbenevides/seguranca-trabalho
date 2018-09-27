package br.com.erucae.segurancatrabalho.web.rest;

import br.com.erucae.segurancatrabalho.SegurancatrabalhoApp;

import br.com.erucae.segurancatrabalho.domain.JobRole;
import br.com.erucae.segurancatrabalho.repository.JobRoleRepository;
import br.com.erucae.segurancatrabalho.service.JobRoleService;
import br.com.erucae.segurancatrabalho.service.dto.JobRoleDTO;
import br.com.erucae.segurancatrabalho.service.mapper.JobRoleMapper;
import br.com.erucae.segurancatrabalho.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.erucae.segurancatrabalho.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JobRoleResource REST controller.
 *
 * @see JobRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SegurancatrabalhoApp.class)
public class JobRoleResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private JobRoleRepository jobRoleRepository;


    @Autowired
    private JobRoleMapper jobRoleMapper;
    

    @Autowired
    private JobRoleService jobRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobRoleMockMvc;

    private JobRole jobRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JobRoleResource jobRoleResource = new JobRoleResource(jobRoleService);
        this.restJobRoleMockMvc = MockMvcBuilders.standaloneSetup(jobRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobRole createEntity(EntityManager em) {
        JobRole jobRole = new JobRole()
            .name(DEFAULT_NAME);
        return jobRole;
    }

    @Before
    public void initTest() {
        jobRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobRole() throws Exception {
        int databaseSizeBeforeCreate = jobRoleRepository.findAll().size();

        // Create the JobRole
        JobRoleDTO jobRoleDTO = jobRoleMapper.toDto(jobRole);
        restJobRoleMockMvc.perform(post("/api/job-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the JobRole in the database
        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeCreate + 1);
        JobRole testJobRole = jobRoleList.get(jobRoleList.size() - 1);
        assertThat(testJobRole.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createJobRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobRoleRepository.findAll().size();

        // Create the JobRole with an existing ID
        jobRole.setId(1L);
        JobRoleDTO jobRoleDTO = jobRoleMapper.toDto(jobRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobRoleMockMvc.perform(post("/api/job-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobRole in the database
        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobRoleRepository.findAll().size();
        // set the field null
        jobRole.setName(null);

        // Create the JobRole, which fails.
        JobRoleDTO jobRoleDTO = jobRoleMapper.toDto(jobRole);

        restJobRoleMockMvc.perform(post("/api/job-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobRoleDTO)))
            .andExpect(status().isBadRequest());

        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJobRoles() throws Exception {
        // Initialize the database
        jobRoleRepository.saveAndFlush(jobRole);

        // Get all the jobRoleList
        restJobRoleMockMvc.perform(get("/api/job-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getJobRole() throws Exception {
        // Initialize the database
        jobRoleRepository.saveAndFlush(jobRole);

        // Get the jobRole
        restJobRoleMockMvc.perform(get("/api/job-roles/{id}", jobRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobRole.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingJobRole() throws Exception {
        // Get the jobRole
        restJobRoleMockMvc.perform(get("/api/job-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobRole() throws Exception {
        // Initialize the database
        jobRoleRepository.saveAndFlush(jobRole);

        int databaseSizeBeforeUpdate = jobRoleRepository.findAll().size();

        // Update the jobRole
        JobRole updatedJobRole = jobRoleRepository.findById(jobRole.getId()).get();
        // Disconnect from session so that the updates on updatedJobRole are not directly saved in db
        em.detach(updatedJobRole);
        updatedJobRole
            .name(UPDATED_NAME);
        JobRoleDTO jobRoleDTO = jobRoleMapper.toDto(updatedJobRole);

        restJobRoleMockMvc.perform(put("/api/job-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobRoleDTO)))
            .andExpect(status().isOk());

        // Validate the JobRole in the database
        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeUpdate);
        JobRole testJobRole = jobRoleList.get(jobRoleList.size() - 1);
        assertThat(testJobRole.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingJobRole() throws Exception {
        int databaseSizeBeforeUpdate = jobRoleRepository.findAll().size();

        // Create the JobRole
        JobRoleDTO jobRoleDTO = jobRoleMapper.toDto(jobRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restJobRoleMockMvc.perform(put("/api/job-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JobRole in the database
        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJobRole() throws Exception {
        // Initialize the database
        jobRoleRepository.saveAndFlush(jobRole);

        int databaseSizeBeforeDelete = jobRoleRepository.findAll().size();

        // Get the jobRole
        restJobRoleMockMvc.perform(delete("/api/job-roles/{id}", jobRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobRole> jobRoleList = jobRoleRepository.findAll();
        assertThat(jobRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobRole.class);
        JobRole jobRole1 = new JobRole();
        jobRole1.setId(1L);
        JobRole jobRole2 = new JobRole();
        jobRole2.setId(jobRole1.getId());
        assertThat(jobRole1).isEqualTo(jobRole2);
        jobRole2.setId(2L);
        assertThat(jobRole1).isNotEqualTo(jobRole2);
        jobRole1.setId(null);
        assertThat(jobRole1).isNotEqualTo(jobRole2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobRoleDTO.class);
        JobRoleDTO jobRoleDTO1 = new JobRoleDTO();
        jobRoleDTO1.setId(1L);
        JobRoleDTO jobRoleDTO2 = new JobRoleDTO();
        assertThat(jobRoleDTO1).isNotEqualTo(jobRoleDTO2);
        jobRoleDTO2.setId(jobRoleDTO1.getId());
        assertThat(jobRoleDTO1).isEqualTo(jobRoleDTO2);
        jobRoleDTO2.setId(2L);
        assertThat(jobRoleDTO1).isNotEqualTo(jobRoleDTO2);
        jobRoleDTO1.setId(null);
        assertThat(jobRoleDTO1).isNotEqualTo(jobRoleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jobRoleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jobRoleMapper.fromId(null)).isNull();
    }
}
