package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.ReportedSymptom;
import com.gok.ticketingbatch.repository.ReportedSymptomRepository;
import com.gok.ticketingbatch.service.ReportedSymptomService;
import com.gok.ticketingbatch.service.dto.ReportedSymptomDTO;
import com.gok.ticketingbatch.service.mapper.ReportedSymptomMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReportedSymptomResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class ReportedSymptomResourceIT {

    private static final String DEFAULT_COMMUNICATION_MODE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATION_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_FEVER = "AAAAAAAAAA";
    private static final String UPDATED_FEVER = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPERATURE_CAPTURED = "AAAAAAAAAA";
    private static final String UPDATED_TEMPERATURE_CAPTURED = "BBBBBBBBBB";

    private static final String DEFAULT_BLOOD_PRESSURE = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_PRESSURE = "BBBBBBBBBB";

    private static final String DEFAULT_COUGH = "AAAAAAAAAA";
    private static final String UPDATED_COUGH = "BBBBBBBBBB";

    private static final String DEFAULT_COLD = "AAAAAAAAAA";
    private static final String UPDATED_COLD = "BBBBBBBBBB";

    private static final String DEFAULT_BREATHLESSNESS = "AAAAAAAAAA";
    private static final String UPDATED_BREATHLESSNESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTED_ANOTHER_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTED_ANOTHER_PATIENT = "BBBBBBBBBB";

    private static final String DEFAULT_VISIT_CROWDED_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_VISIT_CROWDED_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_OTC_MEDICINES = "AAAAAAAAAA";
    private static final String UPDATED_OTC_MEDICINES = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_RISK_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_ANY_OTHER_SYMPTOM = "AAAAAAAAAA";
    private static final String UPDATED_ANY_OTHER_SYMPTOM = "BBBBBBBBBB";

    private static final String DEFAULT_EHR_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_EHR_RECORD_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_RECORD_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECORD_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DATA_CAPTURED_FROM = "AAAAAAAAAA";
    private static final String UPDATED_DATA_CAPTURED_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_DIARRHEA = "AAAAAAAAAA";
    private static final String UPDATED_DIARRHEA = "BBBBBBBBBB";

    private static final String DEFAULT_LOSS_OF_TASTE_OR_SMELL = "AAAAAAAAAA";
    private static final String UPDATED_LOSS_OF_TASTE_OR_SMELL = "BBBBBBBBBB";

    private static final String DEFAULT_SORE_THROAT = "AAAAAAAAAA";
    private static final String UPDATED_SORE_THROAT = "BBBBBBBBBB";

    private static final String DEFAULT_MIGRATED = "AAAAAAAAAA";
    private static final String UPDATED_MIGRATED = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTED_COVID_POSITIVE_RELATED_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTED_COVID_POSITIVE_RELATED_PATIENT = "BBBBBBBBBB";

    @Autowired
    private ReportedSymptomRepository reportedSymptomRepository;

    @Autowired
    private ReportedSymptomMapper reportedSymptomMapper;

    @Autowired
    private ReportedSymptomService reportedSymptomService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportedSymptomMockMvc;

    private ReportedSymptom reportedSymptom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportedSymptom createEntity(EntityManager em) {
        ReportedSymptom reportedSymptom = new ReportedSymptom()
            .communicationMode(DEFAULT_COMMUNICATION_MODE)
            .fever(DEFAULT_FEVER)
            .temperatureCaptured(DEFAULT_TEMPERATURE_CAPTURED)
            .bloodPressure(DEFAULT_BLOOD_PRESSURE)
            .cough(DEFAULT_COUGH)
            .cold(DEFAULT_COLD)
            .breathlessness(DEFAULT_BREATHLESSNESS)
            .contactedAnotherPatient(DEFAULT_CONTACTED_ANOTHER_PATIENT)
            .visitCrowdedPlace(DEFAULT_VISIT_CROWDED_PLACE)
            .score(DEFAULT_SCORE)
            .otcMedicines(DEFAULT_OTC_MEDICINES)
            .riskLevel(DEFAULT_RISK_LEVEL)
            .anyOtherSymptom(DEFAULT_ANY_OTHER_SYMPTOM)
            .ehrRecordId(DEFAULT_EHR_RECORD_ID)
            .recordCreatedDate(DEFAULT_RECORD_CREATED_DATE)
            .dataCapturedFrom(DEFAULT_DATA_CAPTURED_FROM)
            .diarrhea(DEFAULT_DIARRHEA)
            .lossOfTasteOrSmell(DEFAULT_LOSS_OF_TASTE_OR_SMELL)
            .soreThroat(DEFAULT_SORE_THROAT)
            .migrated(DEFAULT_MIGRATED)
            .contactedCovidPositiveRelatedPatient(DEFAULT_CONTACTED_COVID_POSITIVE_RELATED_PATIENT);
        return reportedSymptom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportedSymptom createUpdatedEntity(EntityManager em) {
        ReportedSymptom reportedSymptom = new ReportedSymptom()
            .communicationMode(UPDATED_COMMUNICATION_MODE)
            .fever(UPDATED_FEVER)
            .temperatureCaptured(UPDATED_TEMPERATURE_CAPTURED)
            .bloodPressure(UPDATED_BLOOD_PRESSURE)
            .cough(UPDATED_COUGH)
            .cold(UPDATED_COLD)
            .breathlessness(UPDATED_BREATHLESSNESS)
            .contactedAnotherPatient(UPDATED_CONTACTED_ANOTHER_PATIENT)
            .visitCrowdedPlace(UPDATED_VISIT_CROWDED_PLACE)
            .score(UPDATED_SCORE)
            .otcMedicines(UPDATED_OTC_MEDICINES)
            .riskLevel(UPDATED_RISK_LEVEL)
            .anyOtherSymptom(UPDATED_ANY_OTHER_SYMPTOM)
            .ehrRecordId(UPDATED_EHR_RECORD_ID)
            .recordCreatedDate(UPDATED_RECORD_CREATED_DATE)
            .dataCapturedFrom(UPDATED_DATA_CAPTURED_FROM)
            .diarrhea(UPDATED_DIARRHEA)
            .lossOfTasteOrSmell(UPDATED_LOSS_OF_TASTE_OR_SMELL)
            .soreThroat(UPDATED_SORE_THROAT)
            .migrated(UPDATED_MIGRATED)
            .contactedCovidPositiveRelatedPatient(UPDATED_CONTACTED_COVID_POSITIVE_RELATED_PATIENT);
        return reportedSymptom;
    }

    @BeforeEach
    public void initTest() {
        reportedSymptom = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportedSymptom() throws Exception {
        int databaseSizeBeforeCreate = reportedSymptomRepository.findAll().size();
        // Create the ReportedSymptom
        ReportedSymptomDTO reportedSymptomDTO = reportedSymptomMapper.toDto(reportedSymptom);
        restReportedSymptomMockMvc.perform(post("/api/reported-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportedSymptomDTO)))
            .andExpect(status().isCreated());

        // Validate the ReportedSymptom in the database
        List<ReportedSymptom> reportedSymptomList = reportedSymptomRepository.findAll();
        assertThat(reportedSymptomList).hasSize(databaseSizeBeforeCreate + 1);
        ReportedSymptom testReportedSymptom = reportedSymptomList.get(reportedSymptomList.size() - 1);
        assertThat(testReportedSymptom.getCommunicationMode()).isEqualTo(DEFAULT_COMMUNICATION_MODE);
        assertThat(testReportedSymptom.getFever()).isEqualTo(DEFAULT_FEVER);
        assertThat(testReportedSymptom.getTemperatureCaptured()).isEqualTo(DEFAULT_TEMPERATURE_CAPTURED);
        assertThat(testReportedSymptom.getBloodPressure()).isEqualTo(DEFAULT_BLOOD_PRESSURE);
        assertThat(testReportedSymptom.getCough()).isEqualTo(DEFAULT_COUGH);
        assertThat(testReportedSymptom.getCold()).isEqualTo(DEFAULT_COLD);
        assertThat(testReportedSymptom.getBreathlessness()).isEqualTo(DEFAULT_BREATHLESSNESS);
        assertThat(testReportedSymptom.getContactedAnotherPatient()).isEqualTo(DEFAULT_CONTACTED_ANOTHER_PATIENT);
        assertThat(testReportedSymptom.getVisitCrowdedPlace()).isEqualTo(DEFAULT_VISIT_CROWDED_PLACE);
        assertThat(testReportedSymptom.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testReportedSymptom.getOtcMedicines()).isEqualTo(DEFAULT_OTC_MEDICINES);
        assertThat(testReportedSymptom.getRiskLevel()).isEqualTo(DEFAULT_RISK_LEVEL);
        assertThat(testReportedSymptom.getAnyOtherSymptom()).isEqualTo(DEFAULT_ANY_OTHER_SYMPTOM);
        assertThat(testReportedSymptom.getEhrRecordId()).isEqualTo(DEFAULT_EHR_RECORD_ID);
        assertThat(testReportedSymptom.getRecordCreatedDate()).isEqualTo(DEFAULT_RECORD_CREATED_DATE);
        assertThat(testReportedSymptom.getDataCapturedFrom()).isEqualTo(DEFAULT_DATA_CAPTURED_FROM);
        assertThat(testReportedSymptom.getDiarrhea()).isEqualTo(DEFAULT_DIARRHEA);
        assertThat(testReportedSymptom.getLossOfTasteOrSmell()).isEqualTo(DEFAULT_LOSS_OF_TASTE_OR_SMELL);
        assertThat(testReportedSymptom.getSoreThroat()).isEqualTo(DEFAULT_SORE_THROAT);
        assertThat(testReportedSymptom.getMigrated()).isEqualTo(DEFAULT_MIGRATED);
        assertThat(testReportedSymptom.getContactedCovidPositiveRelatedPatient()).isEqualTo(DEFAULT_CONTACTED_COVID_POSITIVE_RELATED_PATIENT);
    }

    @Test
    @Transactional
    public void createReportedSymptomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportedSymptomRepository.findAll().size();

        // Create the ReportedSymptom with an existing ID
        reportedSymptom.setId(1L);
        ReportedSymptomDTO reportedSymptomDTO = reportedSymptomMapper.toDto(reportedSymptom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportedSymptomMockMvc.perform(post("/api/reported-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportedSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportedSymptom in the database
        List<ReportedSymptom> reportedSymptomList = reportedSymptomRepository.findAll();
        assertThat(reportedSymptomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReportedSymptoms() throws Exception {
        // Initialize the database
        reportedSymptomRepository.saveAndFlush(reportedSymptom);

        // Get all the reportedSymptomList
        restReportedSymptomMockMvc.perform(get("/api/reported-symptoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportedSymptom.getId().intValue())))
            .andExpect(jsonPath("$.[*].communicationMode").value(hasItem(DEFAULT_COMMUNICATION_MODE)))
            .andExpect(jsonPath("$.[*].fever").value(hasItem(DEFAULT_FEVER)))
            .andExpect(jsonPath("$.[*].temperatureCaptured").value(hasItem(DEFAULT_TEMPERATURE_CAPTURED)))
            .andExpect(jsonPath("$.[*].bloodPressure").value(hasItem(DEFAULT_BLOOD_PRESSURE)))
            .andExpect(jsonPath("$.[*].cough").value(hasItem(DEFAULT_COUGH)))
            .andExpect(jsonPath("$.[*].cold").value(hasItem(DEFAULT_COLD)))
            .andExpect(jsonPath("$.[*].breathlessness").value(hasItem(DEFAULT_BREATHLESSNESS)))
            .andExpect(jsonPath("$.[*].contactedAnotherPatient").value(hasItem(DEFAULT_CONTACTED_ANOTHER_PATIENT)))
            .andExpect(jsonPath("$.[*].visitCrowdedPlace").value(hasItem(DEFAULT_VISIT_CROWDED_PLACE)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].otcMedicines").value(hasItem(DEFAULT_OTC_MEDICINES)))
            .andExpect(jsonPath("$.[*].riskLevel").value(hasItem(DEFAULT_RISK_LEVEL)))
            .andExpect(jsonPath("$.[*].anyOtherSymptom").value(hasItem(DEFAULT_ANY_OTHER_SYMPTOM)))
            .andExpect(jsonPath("$.[*].ehrRecordId").value(hasItem(DEFAULT_EHR_RECORD_ID)))
            .andExpect(jsonPath("$.[*].recordCreatedDate").value(hasItem(DEFAULT_RECORD_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].dataCapturedFrom").value(hasItem(DEFAULT_DATA_CAPTURED_FROM)))
            .andExpect(jsonPath("$.[*].diarrhea").value(hasItem(DEFAULT_DIARRHEA)))
            .andExpect(jsonPath("$.[*].lossOfTasteOrSmell").value(hasItem(DEFAULT_LOSS_OF_TASTE_OR_SMELL)))
            .andExpect(jsonPath("$.[*].soreThroat").value(hasItem(DEFAULT_SORE_THROAT)))
            .andExpect(jsonPath("$.[*].migrated").value(hasItem(DEFAULT_MIGRATED)))
            .andExpect(jsonPath("$.[*].contactedCovidPositiveRelatedPatient").value(hasItem(DEFAULT_CONTACTED_COVID_POSITIVE_RELATED_PATIENT)));
    }
    
    @Test
    @Transactional
    public void getReportedSymptom() throws Exception {
        // Initialize the database
        reportedSymptomRepository.saveAndFlush(reportedSymptom);

        // Get the reportedSymptom
        restReportedSymptomMockMvc.perform(get("/api/reported-symptoms/{id}", reportedSymptom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportedSymptom.getId().intValue()))
            .andExpect(jsonPath("$.communicationMode").value(DEFAULT_COMMUNICATION_MODE))
            .andExpect(jsonPath("$.fever").value(DEFAULT_FEVER))
            .andExpect(jsonPath("$.temperatureCaptured").value(DEFAULT_TEMPERATURE_CAPTURED))
            .andExpect(jsonPath("$.bloodPressure").value(DEFAULT_BLOOD_PRESSURE))
            .andExpect(jsonPath("$.cough").value(DEFAULT_COUGH))
            .andExpect(jsonPath("$.cold").value(DEFAULT_COLD))
            .andExpect(jsonPath("$.breathlessness").value(DEFAULT_BREATHLESSNESS))
            .andExpect(jsonPath("$.contactedAnotherPatient").value(DEFAULT_CONTACTED_ANOTHER_PATIENT))
            .andExpect(jsonPath("$.visitCrowdedPlace").value(DEFAULT_VISIT_CROWDED_PLACE))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.otcMedicines").value(DEFAULT_OTC_MEDICINES))
            .andExpect(jsonPath("$.riskLevel").value(DEFAULT_RISK_LEVEL))
            .andExpect(jsonPath("$.anyOtherSymptom").value(DEFAULT_ANY_OTHER_SYMPTOM))
            .andExpect(jsonPath("$.ehrRecordId").value(DEFAULT_EHR_RECORD_ID))
            .andExpect(jsonPath("$.recordCreatedDate").value(DEFAULT_RECORD_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.dataCapturedFrom").value(DEFAULT_DATA_CAPTURED_FROM))
            .andExpect(jsonPath("$.diarrhea").value(DEFAULT_DIARRHEA))
            .andExpect(jsonPath("$.lossOfTasteOrSmell").value(DEFAULT_LOSS_OF_TASTE_OR_SMELL))
            .andExpect(jsonPath("$.soreThroat").value(DEFAULT_SORE_THROAT))
            .andExpect(jsonPath("$.migrated").value(DEFAULT_MIGRATED))
            .andExpect(jsonPath("$.contactedCovidPositiveRelatedPatient").value(DEFAULT_CONTACTED_COVID_POSITIVE_RELATED_PATIENT));
    }
    @Test
    @Transactional
    public void getNonExistingReportedSymptom() throws Exception {
        // Get the reportedSymptom
        restReportedSymptomMockMvc.perform(get("/api/reported-symptoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportedSymptom() throws Exception {
        // Initialize the database
        reportedSymptomRepository.saveAndFlush(reportedSymptom);

        int databaseSizeBeforeUpdate = reportedSymptomRepository.findAll().size();

        // Update the reportedSymptom
        ReportedSymptom updatedReportedSymptom = reportedSymptomRepository.findById(reportedSymptom.getId()).get();
        // Disconnect from session so that the updates on updatedReportedSymptom are not directly saved in db
        em.detach(updatedReportedSymptom);
        updatedReportedSymptom
            .communicationMode(UPDATED_COMMUNICATION_MODE)
            .fever(UPDATED_FEVER)
            .temperatureCaptured(UPDATED_TEMPERATURE_CAPTURED)
            .bloodPressure(UPDATED_BLOOD_PRESSURE)
            .cough(UPDATED_COUGH)
            .cold(UPDATED_COLD)
            .breathlessness(UPDATED_BREATHLESSNESS)
            .contactedAnotherPatient(UPDATED_CONTACTED_ANOTHER_PATIENT)
            .visitCrowdedPlace(UPDATED_VISIT_CROWDED_PLACE)
            .score(UPDATED_SCORE)
            .otcMedicines(UPDATED_OTC_MEDICINES)
            .riskLevel(UPDATED_RISK_LEVEL)
            .anyOtherSymptom(UPDATED_ANY_OTHER_SYMPTOM)
            .ehrRecordId(UPDATED_EHR_RECORD_ID)
            .recordCreatedDate(UPDATED_RECORD_CREATED_DATE)
            .dataCapturedFrom(UPDATED_DATA_CAPTURED_FROM)
            .diarrhea(UPDATED_DIARRHEA)
            .lossOfTasteOrSmell(UPDATED_LOSS_OF_TASTE_OR_SMELL)
            .soreThroat(UPDATED_SORE_THROAT)
            .migrated(UPDATED_MIGRATED)
            .contactedCovidPositiveRelatedPatient(UPDATED_CONTACTED_COVID_POSITIVE_RELATED_PATIENT);
        ReportedSymptomDTO reportedSymptomDTO = reportedSymptomMapper.toDto(updatedReportedSymptom);

        restReportedSymptomMockMvc.perform(put("/api/reported-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportedSymptomDTO)))
            .andExpect(status().isOk());

        // Validate the ReportedSymptom in the database
        List<ReportedSymptom> reportedSymptomList = reportedSymptomRepository.findAll();
        assertThat(reportedSymptomList).hasSize(databaseSizeBeforeUpdate);
        ReportedSymptom testReportedSymptom = reportedSymptomList.get(reportedSymptomList.size() - 1);
        assertThat(testReportedSymptom.getCommunicationMode()).isEqualTo(UPDATED_COMMUNICATION_MODE);
        assertThat(testReportedSymptom.getFever()).isEqualTo(UPDATED_FEVER);
        assertThat(testReportedSymptom.getTemperatureCaptured()).isEqualTo(UPDATED_TEMPERATURE_CAPTURED);
        assertThat(testReportedSymptom.getBloodPressure()).isEqualTo(UPDATED_BLOOD_PRESSURE);
        assertThat(testReportedSymptom.getCough()).isEqualTo(UPDATED_COUGH);
        assertThat(testReportedSymptom.getCold()).isEqualTo(UPDATED_COLD);
        assertThat(testReportedSymptom.getBreathlessness()).isEqualTo(UPDATED_BREATHLESSNESS);
        assertThat(testReportedSymptom.getContactedAnotherPatient()).isEqualTo(UPDATED_CONTACTED_ANOTHER_PATIENT);
        assertThat(testReportedSymptom.getVisitCrowdedPlace()).isEqualTo(UPDATED_VISIT_CROWDED_PLACE);
        assertThat(testReportedSymptom.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testReportedSymptom.getOtcMedicines()).isEqualTo(UPDATED_OTC_MEDICINES);
        assertThat(testReportedSymptom.getRiskLevel()).isEqualTo(UPDATED_RISK_LEVEL);
        assertThat(testReportedSymptom.getAnyOtherSymptom()).isEqualTo(UPDATED_ANY_OTHER_SYMPTOM);
        assertThat(testReportedSymptom.getEhrRecordId()).isEqualTo(UPDATED_EHR_RECORD_ID);
        assertThat(testReportedSymptom.getRecordCreatedDate()).isEqualTo(UPDATED_RECORD_CREATED_DATE);
        assertThat(testReportedSymptom.getDataCapturedFrom()).isEqualTo(UPDATED_DATA_CAPTURED_FROM);
        assertThat(testReportedSymptom.getDiarrhea()).isEqualTo(UPDATED_DIARRHEA);
        assertThat(testReportedSymptom.getLossOfTasteOrSmell()).isEqualTo(UPDATED_LOSS_OF_TASTE_OR_SMELL);
        assertThat(testReportedSymptom.getSoreThroat()).isEqualTo(UPDATED_SORE_THROAT);
        assertThat(testReportedSymptom.getMigrated()).isEqualTo(UPDATED_MIGRATED);
        assertThat(testReportedSymptom.getContactedCovidPositiveRelatedPatient()).isEqualTo(UPDATED_CONTACTED_COVID_POSITIVE_RELATED_PATIENT);
    }

    @Test
    @Transactional
    public void updateNonExistingReportedSymptom() throws Exception {
        int databaseSizeBeforeUpdate = reportedSymptomRepository.findAll().size();

        // Create the ReportedSymptom
        ReportedSymptomDTO reportedSymptomDTO = reportedSymptomMapper.toDto(reportedSymptom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportedSymptomMockMvc.perform(put("/api/reported-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportedSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReportedSymptom in the database
        List<ReportedSymptom> reportedSymptomList = reportedSymptomRepository.findAll();
        assertThat(reportedSymptomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportedSymptom() throws Exception {
        // Initialize the database
        reportedSymptomRepository.saveAndFlush(reportedSymptom);

        int databaseSizeBeforeDelete = reportedSymptomRepository.findAll().size();

        // Delete the reportedSymptom
        restReportedSymptomMockMvc.perform(delete("/api/reported-symptoms/{id}", reportedSymptom.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportedSymptom> reportedSymptomList = reportedSymptomRepository.findAll();
        assertThat(reportedSymptomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
