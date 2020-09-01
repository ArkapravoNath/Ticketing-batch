package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.PatientActivity;
import com.gok.ticketingbatch.repository.PatientActivityRepository;
import com.gok.ticketingbatch.service.PatientActivityService;
import com.gok.ticketingbatch.service.dto.PatientActivityDTO;
import com.gok.ticketingbatch.service.mapper.PatientActivityMapper;

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
 * Integration tests for the {@link PatientActivityResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PatientActivityResourceIT {

    private static final String DEFAULT_ACTIVITY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_RISK_LEVEL = "BBBBBBBBBB";

    private static final Long DEFAULT_PERSON_ID_REF = 1L;
    private static final Long UPDATED_PERSON_ID_REF = 2L;

    private static final Long DEFAULT_CURRENT_ADDRESS_ID_REF = 1L;
    private static final Long UPDATED_CURRENT_ADDRESS_ID_REF = 2L;

    @Autowired
    private PatientActivityRepository patientActivityRepository;

    @Autowired
    private PatientActivityMapper patientActivityMapper;

    @Autowired
    private PatientActivityService patientActivityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientActivityMockMvc;

    private PatientActivity patientActivity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientActivity createEntity(EntityManager em) {
        PatientActivity patientActivity = new PatientActivity()
            .activityType(DEFAULT_ACTIVITY_TYPE)
            .activityRemark(DEFAULT_ACTIVITY_REMARK)
            .status(DEFAULT_STATUS)
            .riskLevel(DEFAULT_RISK_LEVEL)
            .personIdRef(DEFAULT_PERSON_ID_REF)
            .currentAddressIdRef(DEFAULT_CURRENT_ADDRESS_ID_REF);
        return patientActivity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PatientActivity createUpdatedEntity(EntityManager em) {
        PatientActivity patientActivity = new PatientActivity()
            .activityType(UPDATED_ACTIVITY_TYPE)
            .activityRemark(UPDATED_ACTIVITY_REMARK)
            .status(UPDATED_STATUS)
            .riskLevel(UPDATED_RISK_LEVEL)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .currentAddressIdRef(UPDATED_CURRENT_ADDRESS_ID_REF);
        return patientActivity;
    }

    @BeforeEach
    public void initTest() {
        patientActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatientActivity() throws Exception {
        int databaseSizeBeforeCreate = patientActivityRepository.findAll().size();
        // Create the PatientActivity
        PatientActivityDTO patientActivityDTO = patientActivityMapper.toDto(patientActivity);
        restPatientActivityMockMvc.perform(post("/api/patient-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the PatientActivity in the database
        List<PatientActivity> patientActivityList = patientActivityRepository.findAll();
        assertThat(patientActivityList).hasSize(databaseSizeBeforeCreate + 1);
        PatientActivity testPatientActivity = patientActivityList.get(patientActivityList.size() - 1);
        assertThat(testPatientActivity.getActivityType()).isEqualTo(DEFAULT_ACTIVITY_TYPE);
        assertThat(testPatientActivity.getActivityRemark()).isEqualTo(DEFAULT_ACTIVITY_REMARK);
        assertThat(testPatientActivity.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPatientActivity.getRiskLevel()).isEqualTo(DEFAULT_RISK_LEVEL);
        assertThat(testPatientActivity.getPersonIdRef()).isEqualTo(DEFAULT_PERSON_ID_REF);
        assertThat(testPatientActivity.getCurrentAddressIdRef()).isEqualTo(DEFAULT_CURRENT_ADDRESS_ID_REF);
    }

    @Test
    @Transactional
    public void createPatientActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientActivityRepository.findAll().size();

        // Create the PatientActivity with an existing ID
        patientActivity.setId(1L);
        PatientActivityDTO patientActivityDTO = patientActivityMapper.toDto(patientActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientActivityMockMvc.perform(post("/api/patient-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PatientActivity in the database
        List<PatientActivity> patientActivityList = patientActivityRepository.findAll();
        assertThat(patientActivityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPatientActivities() throws Exception {
        // Initialize the database
        patientActivityRepository.saveAndFlush(patientActivity);

        // Get all the patientActivityList
        restPatientActivityMockMvc.perform(get("/api/patient-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patientActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityType").value(hasItem(DEFAULT_ACTIVITY_TYPE)))
            .andExpect(jsonPath("$.[*].activityRemark").value(hasItem(DEFAULT_ACTIVITY_REMARK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].riskLevel").value(hasItem(DEFAULT_RISK_LEVEL)))
            .andExpect(jsonPath("$.[*].personIdRef").value(hasItem(DEFAULT_PERSON_ID_REF.intValue())))
            .andExpect(jsonPath("$.[*].currentAddressIdRef").value(hasItem(DEFAULT_CURRENT_ADDRESS_ID_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getPatientActivity() throws Exception {
        // Initialize the database
        patientActivityRepository.saveAndFlush(patientActivity);

        // Get the patientActivity
        restPatientActivityMockMvc.perform(get("/api/patient-activities/{id}", patientActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patientActivity.getId().intValue()))
            .andExpect(jsonPath("$.activityType").value(DEFAULT_ACTIVITY_TYPE))
            .andExpect(jsonPath("$.activityRemark").value(DEFAULT_ACTIVITY_REMARK))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.riskLevel").value(DEFAULT_RISK_LEVEL))
            .andExpect(jsonPath("$.personIdRef").value(DEFAULT_PERSON_ID_REF.intValue()))
            .andExpect(jsonPath("$.currentAddressIdRef").value(DEFAULT_CURRENT_ADDRESS_ID_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPatientActivity() throws Exception {
        // Get the patientActivity
        restPatientActivityMockMvc.perform(get("/api/patient-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatientActivity() throws Exception {
        // Initialize the database
        patientActivityRepository.saveAndFlush(patientActivity);

        int databaseSizeBeforeUpdate = patientActivityRepository.findAll().size();

        // Update the patientActivity
        PatientActivity updatedPatientActivity = patientActivityRepository.findById(patientActivity.getId()).get();
        // Disconnect from session so that the updates on updatedPatientActivity are not directly saved in db
        em.detach(updatedPatientActivity);
        updatedPatientActivity
            .activityType(UPDATED_ACTIVITY_TYPE)
            .activityRemark(UPDATED_ACTIVITY_REMARK)
            .status(UPDATED_STATUS)
            .riskLevel(UPDATED_RISK_LEVEL)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .currentAddressIdRef(UPDATED_CURRENT_ADDRESS_ID_REF);
        PatientActivityDTO patientActivityDTO = patientActivityMapper.toDto(updatedPatientActivity);

        restPatientActivityMockMvc.perform(put("/api/patient-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientActivityDTO)))
            .andExpect(status().isOk());

        // Validate the PatientActivity in the database
        List<PatientActivity> patientActivityList = patientActivityRepository.findAll();
        assertThat(patientActivityList).hasSize(databaseSizeBeforeUpdate);
        PatientActivity testPatientActivity = patientActivityList.get(patientActivityList.size() - 1);
        assertThat(testPatientActivity.getActivityType()).isEqualTo(UPDATED_ACTIVITY_TYPE);
        assertThat(testPatientActivity.getActivityRemark()).isEqualTo(UPDATED_ACTIVITY_REMARK);
        assertThat(testPatientActivity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPatientActivity.getRiskLevel()).isEqualTo(UPDATED_RISK_LEVEL);
        assertThat(testPatientActivity.getPersonIdRef()).isEqualTo(UPDATED_PERSON_ID_REF);
        assertThat(testPatientActivity.getCurrentAddressIdRef()).isEqualTo(UPDATED_CURRENT_ADDRESS_ID_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingPatientActivity() throws Exception {
        int databaseSizeBeforeUpdate = patientActivityRepository.findAll().size();

        // Create the PatientActivity
        PatientActivityDTO patientActivityDTO = patientActivityMapper.toDto(patientActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientActivityMockMvc.perform(put("/api/patient-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PatientActivity in the database
        List<PatientActivity> patientActivityList = patientActivityRepository.findAll();
        assertThat(patientActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatientActivity() throws Exception {
        // Initialize the database
        patientActivityRepository.saveAndFlush(patientActivity);

        int databaseSizeBeforeDelete = patientActivityRepository.findAll().size();

        // Delete the patientActivity
        restPatientActivityMockMvc.perform(delete("/api/patient-activities/{id}", patientActivity.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PatientActivity> patientActivityList = patientActivityRepository.findAll();
        assertThat(patientActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
