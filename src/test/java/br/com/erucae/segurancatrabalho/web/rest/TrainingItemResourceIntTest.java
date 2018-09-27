package br.com.erucae.segurancatrabalho.web.rest;

import br.com.erucae.segurancatrabalho.SegurancatrabalhoApp;

import br.com.erucae.segurancatrabalho.domain.TrainingItem;
import br.com.erucae.segurancatrabalho.repository.TrainingItemRepository;
import br.com.erucae.segurancatrabalho.service.TrainingItemService;
import br.com.erucae.segurancatrabalho.service.dto.TrainingItemDTO;
import br.com.erucae.segurancatrabalho.service.mapper.TrainingItemMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.erucae.segurancatrabalho.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.erucae.segurancatrabalho.domain.enumeration.TrainingApplicable;
/**
 * Test class for the TrainingItemResource REST controller.
 *
 * @see TrainingItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SegurancatrabalhoApp.class)
public class TrainingItemResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final TrainingApplicable DEFAULT_TRAINING_APPLICABLE = TrainingApplicable.APPLICABLE;
    private static final TrainingApplicable UPDATED_TRAINING_APPLICABLE = TrainingApplicable.NOT_APPLICABLE;

    private static final Integer DEFAULT_CERTIFICATE_VALIDITY = 1;
    private static final Integer UPDATED_CERTIFICATE_VALIDITY = 2;

    private static final Integer DEFAULT_HOURS_OF_TRAINING = 1;
    private static final Integer UPDATED_HOURS_OF_TRAINING = 2;

    @Autowired
    private TrainingItemRepository trainingItemRepository;


    @Autowired
    private TrainingItemMapper trainingItemMapper;
    

    @Autowired
    private TrainingItemService trainingItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrainingItemMockMvc;

    private TrainingItem trainingItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrainingItemResource trainingItemResource = new TrainingItemResource(trainingItemService);
        this.restTrainingItemMockMvc = MockMvcBuilders.standaloneSetup(trainingItemResource)
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
    public static TrainingItem createEntity(EntityManager em) {
        TrainingItem trainingItem = new TrainingItem()
            .date(DEFAULT_DATE)
            .trainingApplicable(DEFAULT_TRAINING_APPLICABLE)
            .certificateValidity(DEFAULT_CERTIFICATE_VALIDITY)
            .hoursOfTraining(DEFAULT_HOURS_OF_TRAINING);
        return trainingItem;
    }

    @Before
    public void initTest() {
        trainingItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrainingItem() throws Exception {
        int databaseSizeBeforeCreate = trainingItemRepository.findAll().size();

        // Create the TrainingItem
        TrainingItemDTO trainingItemDTO = trainingItemMapper.toDto(trainingItem);
        restTrainingItemMockMvc.perform(post("/api/training-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingItemDTO)))
            .andExpect(status().isCreated());

        // Validate the TrainingItem in the database
        List<TrainingItem> trainingItemList = trainingItemRepository.findAll();
        assertThat(trainingItemList).hasSize(databaseSizeBeforeCreate + 1);
        TrainingItem testTrainingItem = trainingItemList.get(trainingItemList.size() - 1);
        assertThat(testTrainingItem.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTrainingItem.getTrainingApplicable()).isEqualTo(DEFAULT_TRAINING_APPLICABLE);
        assertThat(testTrainingItem.getCertificateValidity()).isEqualTo(DEFAULT_CERTIFICATE_VALIDITY);
        assertThat(testTrainingItem.getHoursOfTraining()).isEqualTo(DEFAULT_HOURS_OF_TRAINING);
    }

    @Test
    @Transactional
    public void createTrainingItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trainingItemRepository.findAll().size();

        // Create the TrainingItem with an existing ID
        trainingItem.setId(1L);
        TrainingItemDTO trainingItemDTO = trainingItemMapper.toDto(trainingItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrainingItemMockMvc.perform(post("/api/training-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrainingItem in the database
        List<TrainingItem> trainingItemList = trainingItemRepository.findAll();
        assertThat(trainingItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrainingItems() throws Exception {
        // Initialize the database
        trainingItemRepository.saveAndFlush(trainingItem);

        // Get all the trainingItemList
        restTrainingItemMockMvc.perform(get("/api/training-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trainingItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].trainingApplicable").value(hasItem(DEFAULT_TRAINING_APPLICABLE.toString())))
            .andExpect(jsonPath("$.[*].certificateValidity").value(hasItem(DEFAULT_CERTIFICATE_VALIDITY)))
            .andExpect(jsonPath("$.[*].hoursOfTraining").value(hasItem(DEFAULT_HOURS_OF_TRAINING)));
    }
    

    @Test
    @Transactional
    public void getTrainingItem() throws Exception {
        // Initialize the database
        trainingItemRepository.saveAndFlush(trainingItem);

        // Get the trainingItem
        restTrainingItemMockMvc.perform(get("/api/training-items/{id}", trainingItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trainingItem.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.trainingApplicable").value(DEFAULT_TRAINING_APPLICABLE.toString()))
            .andExpect(jsonPath("$.certificateValidity").value(DEFAULT_CERTIFICATE_VALIDITY))
            .andExpect(jsonPath("$.hoursOfTraining").value(DEFAULT_HOURS_OF_TRAINING));
    }
    @Test
    @Transactional
    public void getNonExistingTrainingItem() throws Exception {
        // Get the trainingItem
        restTrainingItemMockMvc.perform(get("/api/training-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrainingItem() throws Exception {
        // Initialize the database
        trainingItemRepository.saveAndFlush(trainingItem);

        int databaseSizeBeforeUpdate = trainingItemRepository.findAll().size();

        // Update the trainingItem
        TrainingItem updatedTrainingItem = trainingItemRepository.findById(trainingItem.getId()).get();
        // Disconnect from session so that the updates on updatedTrainingItem are not directly saved in db
        em.detach(updatedTrainingItem);
        updatedTrainingItem
            .date(UPDATED_DATE)
            .trainingApplicable(UPDATED_TRAINING_APPLICABLE)
            .certificateValidity(UPDATED_CERTIFICATE_VALIDITY)
            .hoursOfTraining(UPDATED_HOURS_OF_TRAINING);
        TrainingItemDTO trainingItemDTO = trainingItemMapper.toDto(updatedTrainingItem);

        restTrainingItemMockMvc.perform(put("/api/training-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingItemDTO)))
            .andExpect(status().isOk());

        // Validate the TrainingItem in the database
        List<TrainingItem> trainingItemList = trainingItemRepository.findAll();
        assertThat(trainingItemList).hasSize(databaseSizeBeforeUpdate);
        TrainingItem testTrainingItem = trainingItemList.get(trainingItemList.size() - 1);
        assertThat(testTrainingItem.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTrainingItem.getTrainingApplicable()).isEqualTo(UPDATED_TRAINING_APPLICABLE);
        assertThat(testTrainingItem.getCertificateValidity()).isEqualTo(UPDATED_CERTIFICATE_VALIDITY);
        assertThat(testTrainingItem.getHoursOfTraining()).isEqualTo(UPDATED_HOURS_OF_TRAINING);
    }

    @Test
    @Transactional
    public void updateNonExistingTrainingItem() throws Exception {
        int databaseSizeBeforeUpdate = trainingItemRepository.findAll().size();

        // Create the TrainingItem
        TrainingItemDTO trainingItemDTO = trainingItemMapper.toDto(trainingItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restTrainingItemMockMvc.perform(put("/api/training-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrainingItem in the database
        List<TrainingItem> trainingItemList = trainingItemRepository.findAll();
        assertThat(trainingItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrainingItem() throws Exception {
        // Initialize the database
        trainingItemRepository.saveAndFlush(trainingItem);

        int databaseSizeBeforeDelete = trainingItemRepository.findAll().size();

        // Get the trainingItem
        restTrainingItemMockMvc.perform(delete("/api/training-items/{id}", trainingItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TrainingItem> trainingItemList = trainingItemRepository.findAll();
        assertThat(trainingItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrainingItem.class);
        TrainingItem trainingItem1 = new TrainingItem();
        trainingItem1.setId(1L);
        TrainingItem trainingItem2 = new TrainingItem();
        trainingItem2.setId(trainingItem1.getId());
        assertThat(trainingItem1).isEqualTo(trainingItem2);
        trainingItem2.setId(2L);
        assertThat(trainingItem1).isNotEqualTo(trainingItem2);
        trainingItem1.setId(null);
        assertThat(trainingItem1).isNotEqualTo(trainingItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrainingItemDTO.class);
        TrainingItemDTO trainingItemDTO1 = new TrainingItemDTO();
        trainingItemDTO1.setId(1L);
        TrainingItemDTO trainingItemDTO2 = new TrainingItemDTO();
        assertThat(trainingItemDTO1).isNotEqualTo(trainingItemDTO2);
        trainingItemDTO2.setId(trainingItemDTO1.getId());
        assertThat(trainingItemDTO1).isEqualTo(trainingItemDTO2);
        trainingItemDTO2.setId(2L);
        assertThat(trainingItemDTO1).isNotEqualTo(trainingItemDTO2);
        trainingItemDTO1.setId(null);
        assertThat(trainingItemDTO1).isNotEqualTo(trainingItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trainingItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trainingItemMapper.fromId(null)).isNull();
    }
}
