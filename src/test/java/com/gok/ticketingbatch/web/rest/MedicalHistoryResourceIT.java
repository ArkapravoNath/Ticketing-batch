package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.MedicalHistory;
import com.gok.ticketingbatch.repository.MedicalHistoryRepository;
import com.gok.ticketingbatch.service.MedicalHistoryService;
import com.gok.ticketingbatch.service.dto.MedicalHistoryDTO;
import com.gok.ticketingbatch.service.mapper.MedicalHistoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MedicalHistoryResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class MedicalHistoryResourceIT {

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final String DEFAULT_CANCER = "AAAAAAAAAA";
    private static final String UPDATED_CANCER = "BBBBBBBBBB";

    private static final String DEFAULT_TB_OR_HIV = "AAAAAAAAAA";
    private static final String UPDATED_TB_OR_HIV = "BBBBBBBBBB";

    private static final String DEFAULT_RENAL_PROBLEM = "AAAAAAAAAA";
    private static final String UPDATED_RENAL_PROBLEM = "BBBBBBBBBB";

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private MedicalHistoryMapper medicalHistoryMapper;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalHistoryMockMvc;

    private MedicalHistory medicalHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalHistory createEntity(EntityManager em) {
        MedicalHistory medicalHistory = new MedicalHistory()
            .metadata(DEFAULT_METADATA)
            .cancer(DEFAULT_CANCER)
            .tbOrHiv(DEFAULT_TB_OR_HIV)
            .renalProblem(DEFAULT_RENAL_PROBLEM);
        return medicalHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalHistory createUpdatedEntity(EntityManager em) {
        MedicalHistory medicalHistory = new MedicalHistory()
            .metadata(UPDATED_METADATA)
            .cancer(UPDATED_CANCER)
            .tbOrHiv(UPDATED_TB_OR_HIV)
            .renalProblem(UPDATED_RENAL_PROBLEM);
        return medicalHistory;
    }

    @BeforeEach
    public void initTest() {
        medicalHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalHistory() throws Exception {
        int databaseSizeBeforeCreate = medicalHistoryRepository.findAll().size();
        // Create the MedicalHistory
        MedicalHistoryDTO medicalHistoryDTO = medicalHistoryMapper.toDto(medicalHistory);
        restMedicalHistoryMockMvc.perform(post("/api/medical-histories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalHistory in the database
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        assertThat(medicalHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalHistory testMedicalHistory = medicalHistoryList.get(medicalHistoryList.size() - 1);
        assertThat(testMedicalHistory.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testMedicalHistory.getCancer()).isEqualTo(DEFAULT_CANCER);
        assertThat(testMedicalHistory.getTbOrHiv()).isEqualTo(DEFAULT_TB_OR_HIV);
        assertThat(testMedicalHistory.getRenalProblem()).isEqualTo(DEFAULT_RENAL_PROBLEM);
    }

    @Test
    @Transactional
    public void createMedicalHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalHistoryRepository.findAll().size();

        // Create the MedicalHistory with an existing ID
        medicalHistory.setId(1L);
        MedicalHistoryDTO medicalHistoryDTO = medicalHistoryMapper.toDto(medicalHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalHistoryMockMvc.perform(post("/api/medical-histories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalHistory in the database
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        assertThat(medicalHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalHistories() throws Exception {
        // Initialize the database
        medicalHistoryRepository.saveAndFlush(medicalHistory);

        // Get all the medicalHistoryList
        restMedicalHistoryMockMvc.perform(get("/api/medical-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].cancer").value(hasItem(DEFAULT_CANCER)))
            .andExpect(jsonPath("$.[*].tbOrHiv").value(hasItem(DEFAULT_TB_OR_HIV)))
            .andExpect(jsonPath("$.[*].renalProblem").value(hasItem(DEFAULT_RENAL_PROBLEM)));
    }
    
    @Test
    @Transactional
    public void getMedicalHistory() throws Exception {
        // Initialize the database
        medicalHistoryRepository.saveAndFlush(medicalHistory);

        // Get the medicalHistory
        restMedicalHistoryMockMvc.perform(get("/api/medical-histories/{id}", medicalHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalHistory.getId().intValue()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.cancer").value(DEFAULT_CANCER))
            .andExpect(jsonPath("$.tbOrHiv").value(DEFAULT_TB_OR_HIV))
            .andExpect(jsonPath("$.renalProblem").value(DEFAULT_RENAL_PROBLEM));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalHistory() throws Exception {
        // Get the medicalHistory
        restMedicalHistoryMockMvc.perform(get("/api/medical-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalHistory() throws Exception {
        // Initialize the database
        medicalHistoryRepository.saveAndFlush(medicalHistory);

        int databaseSizeBeforeUpdate = medicalHistoryRepository.findAll().size();

        // Update the medicalHistory
        MedicalHistory updatedMedicalHistory = medicalHistoryRepository.findById(medicalHistory.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalHistory are not directly saved in db
        em.detach(updatedMedicalHistory);
        updatedMedicalHistory
            .metadata(UPDATED_METADATA)
            .cancer(UPDATED_CANCER)
            .tbOrHiv(UPDATED_TB_OR_HIV)
            .renalProblem(UPDATED_RENAL_PROBLEM);
        MedicalHistoryDTO medicalHistoryDTO = medicalHistoryMapper.toDto(updatedMedicalHistory);

        restMedicalHistoryMockMvc.perform(put("/api/medical-histories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalHistory in the database
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        assertThat(medicalHistoryList).hasSize(databaseSizeBeforeUpdate);
        MedicalHistory testMedicalHistory = medicalHistoryList.get(medicalHistoryList.size() - 1);
        assertThat(testMedicalHistory.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testMedicalHistory.getCancer()).isEqualTo(UPDATED_CANCER);
        assertThat(testMedicalHistory.getTbOrHiv()).isEqualTo(UPDATED_TB_OR_HIV);
        assertThat(testMedicalHistory.getRenalProblem()).isEqualTo(UPDATED_RENAL_PROBLEM);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalHistory() throws Exception {
        int databaseSizeBeforeUpdate = medicalHistoryRepository.findAll().size();

        // Create the MedicalHistory
        MedicalHistoryDTO medicalHistoryDTO = medicalHistoryMapper.toDto(medicalHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalHistoryMockMvc.perform(put("/api/medical-histories").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalHistory in the database
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        assertThat(medicalHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalHistory() throws Exception {
        // Initialize the database
        medicalHistoryRepository.saveAndFlush(medicalHistory);

        int databaseSizeBeforeDelete = medicalHistoryRepository.findAll().size();

        // Delete the medicalHistory
        restMedicalHistoryMockMvc.perform(delete("/api/medical-histories/{id}", medicalHistory.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findAll();
        assertThat(medicalHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
