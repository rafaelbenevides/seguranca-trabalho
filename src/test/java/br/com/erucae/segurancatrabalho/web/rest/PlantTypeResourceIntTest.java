package br.com.erucae.segurancatrabalho.web.rest;

import br.com.erucae.segurancatrabalho.SegurancatrabalhoApp;

import br.com.erucae.segurancatrabalho.domain.PlantType;
import br.com.erucae.segurancatrabalho.repository.PlantTypeRepository;
import br.com.erucae.segurancatrabalho.service.PlantTypeService;
import br.com.erucae.segurancatrabalho.service.dto.PlantTypeDTO;
import br.com.erucae.segurancatrabalho.service.mapper.PlantTypeMapper;
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
 * Test class for the PlantTypeResource REST controller.
 *
 * @see PlantTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SegurancatrabalhoApp.class)
public class PlantTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private PlantTypeRepository plantTypeRepository;


    @Autowired
    private PlantTypeMapper plantTypeMapper;
    

    @Autowired
    private PlantTypeService plantTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlantTypeMockMvc;

    private PlantType plantType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlantTypeResource plantTypeResource = new PlantTypeResource(plantTypeService);
        this.restPlantTypeMockMvc = MockMvcBuilders.standaloneSetup(plantTypeResource)
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
    public static PlantType createEntity(EntityManager em) {
        PlantType plantType = new PlantType()
            .name(DEFAULT_NAME);
        return plantType;
    }

    @Before
    public void initTest() {
        plantType = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlantType() throws Exception {
        int databaseSizeBeforeCreate = plantTypeRepository.findAll().size();

        // Create the PlantType
        PlantTypeDTO plantTypeDTO = plantTypeMapper.toDto(plantType);
        restPlantTypeMockMvc.perform(post("/api/plant-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the PlantType in the database
        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeCreate + 1);
        PlantType testPlantType = plantTypeList.get(plantTypeList.size() - 1);
        assertThat(testPlantType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createPlantTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = plantTypeRepository.findAll().size();

        // Create the PlantType with an existing ID
        plantType.setId(1L);
        PlantTypeDTO plantTypeDTO = plantTypeMapper.toDto(plantType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlantTypeMockMvc.perform(post("/api/plant-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlantType in the database
        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = plantTypeRepository.findAll().size();
        // set the field null
        plantType.setName(null);

        // Create the PlantType, which fails.
        PlantTypeDTO plantTypeDTO = plantTypeMapper.toDto(plantType);

        restPlantTypeMockMvc.perform(post("/api/plant-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantTypeDTO)))
            .andExpect(status().isBadRequest());

        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlantTypes() throws Exception {
        // Initialize the database
        plantTypeRepository.saveAndFlush(plantType);

        // Get all the plantTypeList
        restPlantTypeMockMvc.perform(get("/api/plant-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plantType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getPlantType() throws Exception {
        // Initialize the database
        plantTypeRepository.saveAndFlush(plantType);

        // Get the plantType
        restPlantTypeMockMvc.perform(get("/api/plant-types/{id}", plantType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(plantType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPlantType() throws Exception {
        // Get the plantType
        restPlantTypeMockMvc.perform(get("/api/plant-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlantType() throws Exception {
        // Initialize the database
        plantTypeRepository.saveAndFlush(plantType);

        int databaseSizeBeforeUpdate = plantTypeRepository.findAll().size();

        // Update the plantType
        PlantType updatedPlantType = plantTypeRepository.findById(plantType.getId()).get();
        // Disconnect from session so that the updates on updatedPlantType are not directly saved in db
        em.detach(updatedPlantType);
        updatedPlantType
            .name(UPDATED_NAME);
        PlantTypeDTO plantTypeDTO = plantTypeMapper.toDto(updatedPlantType);

        restPlantTypeMockMvc.perform(put("/api/plant-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantTypeDTO)))
            .andExpect(status().isOk());

        // Validate the PlantType in the database
        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeUpdate);
        PlantType testPlantType = plantTypeList.get(plantTypeList.size() - 1);
        assertThat(testPlantType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPlantType() throws Exception {
        int databaseSizeBeforeUpdate = plantTypeRepository.findAll().size();

        // Create the PlantType
        PlantTypeDTO plantTypeDTO = plantTypeMapper.toDto(plantType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restPlantTypeMockMvc.perform(put("/api/plant-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plantTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlantType in the database
        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlantType() throws Exception {
        // Initialize the database
        plantTypeRepository.saveAndFlush(plantType);

        int databaseSizeBeforeDelete = plantTypeRepository.findAll().size();

        // Get the plantType
        restPlantTypeMockMvc.perform(delete("/api/plant-types/{id}", plantType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlantType> plantTypeList = plantTypeRepository.findAll();
        assertThat(plantTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlantType.class);
        PlantType plantType1 = new PlantType();
        plantType1.setId(1L);
        PlantType plantType2 = new PlantType();
        plantType2.setId(plantType1.getId());
        assertThat(plantType1).isEqualTo(plantType2);
        plantType2.setId(2L);
        assertThat(plantType1).isNotEqualTo(plantType2);
        plantType1.setId(null);
        assertThat(plantType1).isNotEqualTo(plantType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlantTypeDTO.class);
        PlantTypeDTO plantTypeDTO1 = new PlantTypeDTO();
        plantTypeDTO1.setId(1L);
        PlantTypeDTO plantTypeDTO2 = new PlantTypeDTO();
        assertThat(plantTypeDTO1).isNotEqualTo(plantTypeDTO2);
        plantTypeDTO2.setId(plantTypeDTO1.getId());
        assertThat(plantTypeDTO1).isEqualTo(plantTypeDTO2);
        plantTypeDTO2.setId(2L);
        assertThat(plantTypeDTO1).isNotEqualTo(plantTypeDTO2);
        plantTypeDTO1.setId(null);
        assertThat(plantTypeDTO1).isNotEqualTo(plantTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(plantTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(plantTypeMapper.fromId(null)).isNull();
    }
}
