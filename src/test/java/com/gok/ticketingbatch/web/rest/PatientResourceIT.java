package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Patient;
import com.gok.ticketingbatch.repository.PatientRepository;
import com.gok.ticketingbatch.service.PatientService;
import com.gok.ticketingbatch.service.dto.PatientDTO;
import com.gok.ticketingbatch.service.mapper.PatientMapper;

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
 * Integration tests for the {@link PatientResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PatientResourceIT {

    private static final String DEFAULT_ICMR_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_ICMR_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_PID = "AAAAAAAAAA";
    private static final String UPDATED_STATE_PID = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT_PID = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_PID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPIRATORY_COMPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_RESPIRATORY_COMPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_CARDIOVASCULAR_DISEASE = "AAAAAAAAAA";
    private static final String UPDATED_CARDIOVASCULAR_DISEASE = "BBBBBBBBBB";

    private static final String DEFAULT_DIABETES = "AAAAAAAAAA";
    private static final String UPDATED_DIABETES = "BBBBBBBBBB";

    private static final String DEFAULT_BLOOD_PRESSURE = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_PRESSURE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_LONG_TERM_DESEASE = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_LONG_TERM_DESEASE = "BBBBBBBBBB";

    private static final Long DEFAULT_PERSON_ID_REF = 1L;
    private static final Long UPDATED_PERSON_ID_REF = 2L;

    private static final Long DEFAULT_CURRENT_ADDRESS_ID_REF = 1L;
    private static final Long UPDATED_CURRENT_ADDRESS_ID_REF = 2L;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientMockMvc;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .icmrReference(DEFAULT_ICMR_REFERENCE)
            .statePid(DEFAULT_STATE_PID)
            .districtPid(DEFAULT_DISTRICT_PID)
            .respiratoryComplication(DEFAULT_RESPIRATORY_COMPLICATION)
            .cardiovascularDisease(DEFAULT_CARDIOVASCULAR_DISEASE)
            .diabetes(DEFAULT_DIABETES)
            .bloodPressure(DEFAULT_BLOOD_PRESSURE)
            .otherLongTermDesease(DEFAULT_OTHER_LONG_TERM_DESEASE)
            .personIdRef(DEFAULT_PERSON_ID_REF)
            .currentAddressIdRef(DEFAULT_CURRENT_ADDRESS_ID_REF);
        return patient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity(EntityManager em) {
        Patient patient = new Patient()
            .icmrReference(UPDATED_ICMR_REFERENCE)
            .statePid(UPDATED_STATE_PID)
            .districtPid(UPDATED_DISTRICT_PID)
            .respiratoryComplication(UPDATED_RESPIRATORY_COMPLICATION)
            .cardiovascularDisease(UPDATED_CARDIOVASCULAR_DISEASE)
            .diabetes(UPDATED_DIABETES)
            .bloodPressure(UPDATED_BLOOD_PRESSURE)
            .otherLongTermDesease(UPDATED_OTHER_LONG_TERM_DESEASE)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .currentAddressIdRef(UPDATED_CURRENT_ADDRESS_ID_REF);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();
        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);
        restPatientMockMvc.perform(post("/api/patients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getIcmrReference()).isEqualTo(DEFAULT_ICMR_REFERENCE);
        assertThat(testPatient.getStatePid()).isEqualTo(DEFAULT_STATE_PID);
        assertThat(testPatient.getDistrictPid()).isEqualTo(DEFAULT_DISTRICT_PID);
        assertThat(testPatient.getRespiratoryComplication()).isEqualTo(DEFAULT_RESPIRATORY_COMPLICATION);
        assertThat(testPatient.getCardiovascularDisease()).isEqualTo(DEFAULT_CARDIOVASCULAR_DISEASE);
        assertThat(testPatient.getDiabetes()).isEqualTo(DEFAULT_DIABETES);
        assertThat(testPatient.getBloodPressure()).isEqualTo(DEFAULT_BLOOD_PRESSURE);
        assertThat(testPatient.getOtherLongTermDesease()).isEqualTo(DEFAULT_OTHER_LONG_TERM_DESEASE);
        assertThat(testPatient.getPersonIdRef()).isEqualTo(DEFAULT_PERSON_ID_REF);
        assertThat(testPatient.getCurrentAddressIdRef()).isEqualTo(DEFAULT_CURRENT_ADDRESS_ID_REF);
    }

    @Test
    @Transactional
    public void createPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient with an existing ID
        patient.setId(1L);
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc.perform(post("/api/patients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].icmrReference").value(hasItem(DEFAULT_ICMR_REFERENCE)))
            .andExpect(jsonPath("$.[*].statePid").value(hasItem(DEFAULT_STATE_PID)))
            .andExpect(jsonPath("$.[*].districtPid").value(hasItem(DEFAULT_DISTRICT_PID)))
            .andExpect(jsonPath("$.[*].respiratoryComplication").value(hasItem(DEFAULT_RESPIRATORY_COMPLICATION)))
            .andExpect(jsonPath("$.[*].cardiovascularDisease").value(hasItem(DEFAULT_CARDIOVASCULAR_DISEASE)))
            .andExpect(jsonPath("$.[*].diabetes").value(hasItem(DEFAULT_DIABETES)))
            .andExpect(jsonPath("$.[*].bloodPressure").value(hasItem(DEFAULT_BLOOD_PRESSURE)))
            .andExpect(jsonPath("$.[*].otherLongTermDesease").value(hasItem(DEFAULT_OTHER_LONG_TERM_DESEASE)))
            .andExpect(jsonPath("$.[*].personIdRef").value(hasItem(DEFAULT_PERSON_ID_REF.intValue())))
            .andExpect(jsonPath("$.[*].currentAddressIdRef").value(hasItem(DEFAULT_CURRENT_ADDRESS_ID_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().intValue()))
            .andExpect(jsonPath("$.icmrReference").value(DEFAULT_ICMR_REFERENCE))
            .andExpect(jsonPath("$.statePid").value(DEFAULT_STATE_PID))
            .andExpect(jsonPath("$.districtPid").value(DEFAULT_DISTRICT_PID))
            .andExpect(jsonPath("$.respiratoryComplication").value(DEFAULT_RESPIRATORY_COMPLICATION))
            .andExpect(jsonPath("$.cardiovascularDisease").value(DEFAULT_CARDIOVASCULAR_DISEASE))
            .andExpect(jsonPath("$.diabetes").value(DEFAULT_DIABETES))
            .andExpect(jsonPath("$.bloodPressure").value(DEFAULT_BLOOD_PRESSURE))
            .andExpect(jsonPath("$.otherLongTermDesease").value(DEFAULT_OTHER_LONG_TERM_DESEASE))
            .andExpect(jsonPath("$.personIdRef").value(DEFAULT_PERSON_ID_REF.intValue()))
            .andExpect(jsonPath("$.currentAddressIdRef").value(DEFAULT_CURRENT_ADDRESS_ID_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .icmrReference(UPDATED_ICMR_REFERENCE)
            .statePid(UPDATED_STATE_PID)
            .districtPid(UPDATED_DISTRICT_PID)
            .respiratoryComplication(UPDATED_RESPIRATORY_COMPLICATION)
            .cardiovascularDisease(UPDATED_CARDIOVASCULAR_DISEASE)
            .diabetes(UPDATED_DIABETES)
            .bloodPressure(UPDATED_BLOOD_PRESSURE)
            .otherLongTermDesease(UPDATED_OTHER_LONG_TERM_DESEASE)
            .personIdRef(UPDATED_PERSON_ID_REF)
            .currentAddressIdRef(UPDATED_CURRENT_ADDRESS_ID_REF);
        PatientDTO patientDTO = patientMapper.toDto(updatedPatient);

        restPatientMockMvc.perform(put("/api/patients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getIcmrReference()).isEqualTo(UPDATED_ICMR_REFERENCE);
        assertThat(testPatient.getStatePid()).isEqualTo(UPDATED_STATE_PID);
        assertThat(testPatient.getDistrictPid()).isEqualTo(UPDATED_DISTRICT_PID);
        assertThat(testPatient.getRespiratoryComplication()).isEqualTo(UPDATED_RESPIRATORY_COMPLICATION);
        assertThat(testPatient.getCardiovascularDisease()).isEqualTo(UPDATED_CARDIOVASCULAR_DISEASE);
        assertThat(testPatient.getDiabetes()).isEqualTo(UPDATED_DIABETES);
        assertThat(testPatient.getBloodPressure()).isEqualTo(UPDATED_BLOOD_PRESSURE);
        assertThat(testPatient.getOtherLongTermDesease()).isEqualTo(UPDATED_OTHER_LONG_TERM_DESEASE);
        assertThat(testPatient.getPersonIdRef()).isEqualTo(UPDATED_PERSON_ID_REF);
        assertThat(testPatient.getCurrentAddressIdRef()).isEqualTo(UPDATED_CURRENT_ADDRESS_ID_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc.perform(put("/api/patients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc.perform(delete("/api/patients/{id}", patient.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
