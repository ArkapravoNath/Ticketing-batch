package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Prescription;
import com.gok.ticketingbatch.repository.PrescriptionRepository;
import com.gok.ticketingbatch.service.PrescriptionService;
import com.gok.ticketingbatch.service.dto.PrescriptionDTO;
import com.gok.ticketingbatch.service.mapper.PrescriptionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PrescriptionResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PrescriptionResourceIT {

    private static final String DEFAULT_OTC_MEDS_PRESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_OTC_MEDS_PRESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ANY_OTHER_PRESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ANY_OTHER_PRESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADVISORY_ISSUES = "AAAAAAAAAA";
    private static final String UPDATED_ADVISORY_ISSUES = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD_OF_CAPTURE = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_OF_CAPTURE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRED_CLINIC = "AAAAAAAAAA";
    private static final String UPDATED_REFERRED_CLINIC = "BBBBBBBBBB";

    private static final String DEFAULT_REFERRED_CLINIC_ID = "AAAAAAAAAA";
    private static final String UPDATED_REFERRED_CLINIC_ID = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PRESCRIPTION_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PRESCRIPTION_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PRESCRIBED_BY_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRESCRIBED_BY_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_FACILITY_PROVIDER_ID_REF = 1L;
    private static final Long UPDATED_FACILITY_PROVIDER_ID_REF = 2L;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrescriptionMockMvc;

    private Prescription prescription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .otcMedsPrescription(DEFAULT_OTC_MEDS_PRESCRIPTION)
            .anyOtherPrescription(DEFAULT_ANY_OTHER_PRESCRIPTION)
            .advisoryIssues(DEFAULT_ADVISORY_ISSUES)
            .methodOfCapture(DEFAULT_METHOD_OF_CAPTURE)
            .referredClinic(DEFAULT_REFERRED_CLINIC)
            .referredClinicId(DEFAULT_REFERRED_CLINIC_ID)
            .prescriptionImage(DEFAULT_PRESCRIPTION_IMAGE)
            .prescriptionImageContentType(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescriptionImageContentType(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescribedById(DEFAULT_PRESCRIBED_BY_ID)
            .facilityProviderIdRef(DEFAULT_FACILITY_PROVIDER_ID_REF);
        return prescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createUpdatedEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .otcMedsPrescription(UPDATED_OTC_MEDS_PRESCRIPTION)
            .anyOtherPrescription(UPDATED_ANY_OTHER_PRESCRIPTION)
            .advisoryIssues(UPDATED_ADVISORY_ISSUES)
            .methodOfCapture(UPDATED_METHOD_OF_CAPTURE)
            .referredClinic(UPDATED_REFERRED_CLINIC)
            .referredClinicId(UPDATED_REFERRED_CLINIC_ID)
            .prescriptionImage(UPDATED_PRESCRIPTION_IMAGE)
            .prescriptionImageContentType(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescriptionImageContentType(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescribedById(UPDATED_PRESCRIBED_BY_ID)
            .facilityProviderIdRef(UPDATED_FACILITY_PROVIDER_ID_REF);
        return prescription;
    }

    @BeforeEach
    public void initTest() {
        prescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrescription() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();
        // Create the Prescription
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);
        restPrescriptionMockMvc.perform(post("/api/prescriptions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.getOtcMedsPrescription()).isEqualTo(DEFAULT_OTC_MEDS_PRESCRIPTION);
        assertThat(testPrescription.getAnyOtherPrescription()).isEqualTo(DEFAULT_ANY_OTHER_PRESCRIPTION);
        assertThat(testPrescription.getAdvisoryIssues()).isEqualTo(DEFAULT_ADVISORY_ISSUES);
        assertThat(testPrescription.getMethodOfCapture()).isEqualTo(DEFAULT_METHOD_OF_CAPTURE);
        assertThat(testPrescription.getReferredClinic()).isEqualTo(DEFAULT_REFERRED_CLINIC);
        assertThat(testPrescription.getReferredClinicId()).isEqualTo(DEFAULT_REFERRED_CLINIC_ID);
        assertThat(testPrescription.getPrescriptionImage()).isEqualTo(DEFAULT_PRESCRIPTION_IMAGE);
        assertThat(testPrescription.getPrescriptionImageContentType()).isEqualTo(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE);
        assertThat(testPrescription.getPrescriptionImageContentType()).isEqualTo(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE);
        assertThat(testPrescription.getPrescribedById()).isEqualTo(DEFAULT_PRESCRIBED_BY_ID);
        assertThat(testPrescription.getFacilityProviderIdRef()).isEqualTo(DEFAULT_FACILITY_PROVIDER_ID_REF);
    }

    @Test
    @Transactional
    public void createPrescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();

        // Create the Prescription with an existing ID
        prescription.setId(1L);
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrescriptionMockMvc.perform(post("/api/prescriptions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPrescriptions() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList
        restPrescriptionMockMvc.perform(get("/api/prescriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].otcMedsPrescription").value(hasItem(DEFAULT_OTC_MEDS_PRESCRIPTION)))
            .andExpect(jsonPath("$.[*].anyOtherPrescription").value(hasItem(DEFAULT_ANY_OTHER_PRESCRIPTION)))
            .andExpect(jsonPath("$.[*].advisoryIssues").value(hasItem(DEFAULT_ADVISORY_ISSUES)))
            .andExpect(jsonPath("$.[*].methodOfCapture").value(hasItem(DEFAULT_METHOD_OF_CAPTURE)))
            .andExpect(jsonPath("$.[*].referredClinic").value(hasItem(DEFAULT_REFERRED_CLINIC)))
            .andExpect(jsonPath("$.[*].referredClinicId").value(hasItem(DEFAULT_REFERRED_CLINIC_ID)))
            .andExpect(jsonPath("$.[*].prescriptionImageContentType").value(hasItem(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].prescriptionImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_PRESCRIPTION_IMAGE))))
            .andExpect(jsonPath("$.[*].prescriptionImageContentType").value(hasItem(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].prescribedById").value(hasItem(DEFAULT_PRESCRIBED_BY_ID)))
            .andExpect(jsonPath("$.[*].facilityProviderIdRef").value(hasItem(DEFAULT_FACILITY_PROVIDER_ID_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getPrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get the prescription
        restPrescriptionMockMvc.perform(get("/api/prescriptions/{id}", prescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prescription.getId().intValue()))
            .andExpect(jsonPath("$.otcMedsPrescription").value(DEFAULT_OTC_MEDS_PRESCRIPTION))
            .andExpect(jsonPath("$.anyOtherPrescription").value(DEFAULT_ANY_OTHER_PRESCRIPTION))
            .andExpect(jsonPath("$.advisoryIssues").value(DEFAULT_ADVISORY_ISSUES))
            .andExpect(jsonPath("$.methodOfCapture").value(DEFAULT_METHOD_OF_CAPTURE))
            .andExpect(jsonPath("$.referredClinic").value(DEFAULT_REFERRED_CLINIC))
            .andExpect(jsonPath("$.referredClinicId").value(DEFAULT_REFERRED_CLINIC_ID))
            .andExpect(jsonPath("$.prescriptionImageContentType").value(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.prescriptionImage").value(Base64Utils.encodeToString(DEFAULT_PRESCRIPTION_IMAGE)))
            .andExpect(jsonPath("$.prescriptionImageContentType").value(DEFAULT_PRESCRIPTION_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.prescribedById").value(DEFAULT_PRESCRIBED_BY_ID))
            .andExpect(jsonPath("$.facilityProviderIdRef").value(DEFAULT_FACILITY_PROVIDER_ID_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPrescription() throws Exception {
        // Get the prescription
        restPrescriptionMockMvc.perform(get("/api/prescriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // Update the prescription
        Prescription updatedPrescription = prescriptionRepository.findById(prescription.getId()).get();
        // Disconnect from session so that the updates on updatedPrescription are not directly saved in db
        em.detach(updatedPrescription);
        updatedPrescription
            .otcMedsPrescription(UPDATED_OTC_MEDS_PRESCRIPTION)
            .anyOtherPrescription(UPDATED_ANY_OTHER_PRESCRIPTION)
            .advisoryIssues(UPDATED_ADVISORY_ISSUES)
            .methodOfCapture(UPDATED_METHOD_OF_CAPTURE)
            .referredClinic(UPDATED_REFERRED_CLINIC)
            .referredClinicId(UPDATED_REFERRED_CLINIC_ID)
            .prescriptionImage(UPDATED_PRESCRIPTION_IMAGE)
            .prescriptionImageContentType(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescriptionImageContentType(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE)
            .prescribedById(UPDATED_PRESCRIBED_BY_ID)
            .facilityProviderIdRef(UPDATED_FACILITY_PROVIDER_ID_REF);
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(updatedPrescription);

        restPrescriptionMockMvc.perform(put("/api/prescriptions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.getOtcMedsPrescription()).isEqualTo(UPDATED_OTC_MEDS_PRESCRIPTION);
        assertThat(testPrescription.getAnyOtherPrescription()).isEqualTo(UPDATED_ANY_OTHER_PRESCRIPTION);
        assertThat(testPrescription.getAdvisoryIssues()).isEqualTo(UPDATED_ADVISORY_ISSUES);
        assertThat(testPrescription.getMethodOfCapture()).isEqualTo(UPDATED_METHOD_OF_CAPTURE);
        assertThat(testPrescription.getReferredClinic()).isEqualTo(UPDATED_REFERRED_CLINIC);
        assertThat(testPrescription.getReferredClinicId()).isEqualTo(UPDATED_REFERRED_CLINIC_ID);
        assertThat(testPrescription.getPrescriptionImage()).isEqualTo(UPDATED_PRESCRIPTION_IMAGE);
        assertThat(testPrescription.getPrescriptionImageContentType()).isEqualTo(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE);
        assertThat(testPrescription.getPrescriptionImageContentType()).isEqualTo(UPDATED_PRESCRIPTION_IMAGE_CONTENT_TYPE);
        assertThat(testPrescription.getPrescribedById()).isEqualTo(UPDATED_PRESCRIBED_BY_ID);
        assertThat(testPrescription.getFacilityProviderIdRef()).isEqualTo(UPDATED_FACILITY_PROVIDER_ID_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingPrescription() throws Exception {
        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // Create the Prescription
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrescriptionMockMvc.perform(put("/api/prescriptions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        int databaseSizeBeforeDelete = prescriptionRepository.findAll().size();

        // Delete the prescription
        restPrescriptionMockMvc.perform(delete("/api/prescriptions/{id}", prescription.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
