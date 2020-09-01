package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.KsrsacAddress;
import com.gok.ticketingbatch.repository.KsrsacAddressRepository;
import com.gok.ticketingbatch.service.KsrsacAddressService;
import com.gok.ticketingbatch.service.dto.KsrsacAddressDTO;
import com.gok.ticketingbatch.service.mapper.KsrsacAddressMapper;

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
 * Integration tests for the {@link KsrsacAddressResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class KsrsacAddressResourceIT {

    private static final String DEFAULT_DISTRICT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOBLI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOBLI_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEYNUM = "AAAAAAAAAA";
    private static final String UPDATED_SURVEYNUM = "BBBBBBBBBB";

    private static final String DEFAULT_TALUK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TALUK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TALUK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TALUK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOBLI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOBLI_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private KsrsacAddressRepository ksrsacAddressRepository;

    @Autowired
    private KsrsacAddressMapper ksrsacAddressMapper;

    @Autowired
    private KsrsacAddressService ksrsacAddressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKsrsacAddressMockMvc;

    private KsrsacAddress ksrsacAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KsrsacAddress createEntity(EntityManager em) {
        KsrsacAddress ksrsacAddress = new KsrsacAddress()
            .districtCode(DEFAULT_DISTRICT_CODE)
            .districtName(DEFAULT_DISTRICT_NAME)
            .hobliCode(DEFAULT_HOBLI_CODE)
            .surveynum(DEFAULT_SURVEYNUM)
            .talukCode(DEFAULT_TALUK_CODE)
            .talukName(DEFAULT_TALUK_NAME)
            .type(DEFAULT_TYPE)
            .villageCode(DEFAULT_VILLAGE_CODE)
            .villageName(DEFAULT_VILLAGE_NAME)
            .hobliName(DEFAULT_HOBLI_NAME)
            .message(DEFAULT_MESSAGE);
        return ksrsacAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KsrsacAddress createUpdatedEntity(EntityManager em) {
        KsrsacAddress ksrsacAddress = new KsrsacAddress()
            .districtCode(UPDATED_DISTRICT_CODE)
            .districtName(UPDATED_DISTRICT_NAME)
            .hobliCode(UPDATED_HOBLI_CODE)
            .surveynum(UPDATED_SURVEYNUM)
            .talukCode(UPDATED_TALUK_CODE)
            .talukName(UPDATED_TALUK_NAME)
            .type(UPDATED_TYPE)
            .villageCode(UPDATED_VILLAGE_CODE)
            .villageName(UPDATED_VILLAGE_NAME)
            .hobliName(UPDATED_HOBLI_NAME)
            .message(UPDATED_MESSAGE);
        return ksrsacAddress;
    }

    @BeforeEach
    public void initTest() {
        ksrsacAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createKsrsacAddress() throws Exception {
        int databaseSizeBeforeCreate = ksrsacAddressRepository.findAll().size();
        // Create the KsrsacAddress
        KsrsacAddressDTO ksrsacAddressDTO = ksrsacAddressMapper.toDto(ksrsacAddress);
        restKsrsacAddressMockMvc.perform(post("/api/ksrsac-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ksrsacAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the KsrsacAddress in the database
        List<KsrsacAddress> ksrsacAddressList = ksrsacAddressRepository.findAll();
        assertThat(ksrsacAddressList).hasSize(databaseSizeBeforeCreate + 1);
        KsrsacAddress testKsrsacAddress = ksrsacAddressList.get(ksrsacAddressList.size() - 1);
        assertThat(testKsrsacAddress.getDistrictCode()).isEqualTo(DEFAULT_DISTRICT_CODE);
        assertThat(testKsrsacAddress.getDistrictName()).isEqualTo(DEFAULT_DISTRICT_NAME);
        assertThat(testKsrsacAddress.getHobliCode()).isEqualTo(DEFAULT_HOBLI_CODE);
        assertThat(testKsrsacAddress.getSurveynum()).isEqualTo(DEFAULT_SURVEYNUM);
        assertThat(testKsrsacAddress.getTalukCode()).isEqualTo(DEFAULT_TALUK_CODE);
        assertThat(testKsrsacAddress.getTalukName()).isEqualTo(DEFAULT_TALUK_NAME);
        assertThat(testKsrsacAddress.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testKsrsacAddress.getVillageCode()).isEqualTo(DEFAULT_VILLAGE_CODE);
        assertThat(testKsrsacAddress.getVillageName()).isEqualTo(DEFAULT_VILLAGE_NAME);
        assertThat(testKsrsacAddress.getHobliName()).isEqualTo(DEFAULT_HOBLI_NAME);
        assertThat(testKsrsacAddress.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createKsrsacAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ksrsacAddressRepository.findAll().size();

        // Create the KsrsacAddress with an existing ID
        ksrsacAddress.setId(1L);
        KsrsacAddressDTO ksrsacAddressDTO = ksrsacAddressMapper.toDto(ksrsacAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKsrsacAddressMockMvc.perform(post("/api/ksrsac-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ksrsacAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KsrsacAddress in the database
        List<KsrsacAddress> ksrsacAddressList = ksrsacAddressRepository.findAll();
        assertThat(ksrsacAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllKsrsacAddresses() throws Exception {
        // Initialize the database
        ksrsacAddressRepository.saveAndFlush(ksrsacAddress);

        // Get all the ksrsacAddressList
        restKsrsacAddressMockMvc.perform(get("/api/ksrsac-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ksrsacAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].districtCode").value(hasItem(DEFAULT_DISTRICT_CODE)))
            .andExpect(jsonPath("$.[*].districtName").value(hasItem(DEFAULT_DISTRICT_NAME)))
            .andExpect(jsonPath("$.[*].hobliCode").value(hasItem(DEFAULT_HOBLI_CODE)))
            .andExpect(jsonPath("$.[*].surveynum").value(hasItem(DEFAULT_SURVEYNUM)))
            .andExpect(jsonPath("$.[*].talukCode").value(hasItem(DEFAULT_TALUK_CODE)))
            .andExpect(jsonPath("$.[*].talukName").value(hasItem(DEFAULT_TALUK_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].villageCode").value(hasItem(DEFAULT_VILLAGE_CODE)))
            .andExpect(jsonPath("$.[*].villageName").value(hasItem(DEFAULT_VILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].hobliName").value(hasItem(DEFAULT_HOBLI_NAME)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
    }
    
    @Test
    @Transactional
    public void getKsrsacAddress() throws Exception {
        // Initialize the database
        ksrsacAddressRepository.saveAndFlush(ksrsacAddress);

        // Get the ksrsacAddress
        restKsrsacAddressMockMvc.perform(get("/api/ksrsac-addresses/{id}", ksrsacAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ksrsacAddress.getId().intValue()))
            .andExpect(jsonPath("$.districtCode").value(DEFAULT_DISTRICT_CODE))
            .andExpect(jsonPath("$.districtName").value(DEFAULT_DISTRICT_NAME))
            .andExpect(jsonPath("$.hobliCode").value(DEFAULT_HOBLI_CODE))
            .andExpect(jsonPath("$.surveynum").value(DEFAULT_SURVEYNUM))
            .andExpect(jsonPath("$.talukCode").value(DEFAULT_TALUK_CODE))
            .andExpect(jsonPath("$.talukName").value(DEFAULT_TALUK_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.villageCode").value(DEFAULT_VILLAGE_CODE))
            .andExpect(jsonPath("$.villageName").value(DEFAULT_VILLAGE_NAME))
            .andExpect(jsonPath("$.hobliName").value(DEFAULT_HOBLI_NAME))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
    }
    @Test
    @Transactional
    public void getNonExistingKsrsacAddress() throws Exception {
        // Get the ksrsacAddress
        restKsrsacAddressMockMvc.perform(get("/api/ksrsac-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKsrsacAddress() throws Exception {
        // Initialize the database
        ksrsacAddressRepository.saveAndFlush(ksrsacAddress);

        int databaseSizeBeforeUpdate = ksrsacAddressRepository.findAll().size();

        // Update the ksrsacAddress
        KsrsacAddress updatedKsrsacAddress = ksrsacAddressRepository.findById(ksrsacAddress.getId()).get();
        // Disconnect from session so that the updates on updatedKsrsacAddress are not directly saved in db
        em.detach(updatedKsrsacAddress);
        updatedKsrsacAddress
            .districtCode(UPDATED_DISTRICT_CODE)
            .districtName(UPDATED_DISTRICT_NAME)
            .hobliCode(UPDATED_HOBLI_CODE)
            .surveynum(UPDATED_SURVEYNUM)
            .talukCode(UPDATED_TALUK_CODE)
            .talukName(UPDATED_TALUK_NAME)
            .type(UPDATED_TYPE)
            .villageCode(UPDATED_VILLAGE_CODE)
            .villageName(UPDATED_VILLAGE_NAME)
            .hobliName(UPDATED_HOBLI_NAME)
            .message(UPDATED_MESSAGE);
        KsrsacAddressDTO ksrsacAddressDTO = ksrsacAddressMapper.toDto(updatedKsrsacAddress);

        restKsrsacAddressMockMvc.perform(put("/api/ksrsac-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ksrsacAddressDTO)))
            .andExpect(status().isOk());

        // Validate the KsrsacAddress in the database
        List<KsrsacAddress> ksrsacAddressList = ksrsacAddressRepository.findAll();
        assertThat(ksrsacAddressList).hasSize(databaseSizeBeforeUpdate);
        KsrsacAddress testKsrsacAddress = ksrsacAddressList.get(ksrsacAddressList.size() - 1);
        assertThat(testKsrsacAddress.getDistrictCode()).isEqualTo(UPDATED_DISTRICT_CODE);
        assertThat(testKsrsacAddress.getDistrictName()).isEqualTo(UPDATED_DISTRICT_NAME);
        assertThat(testKsrsacAddress.getHobliCode()).isEqualTo(UPDATED_HOBLI_CODE);
        assertThat(testKsrsacAddress.getSurveynum()).isEqualTo(UPDATED_SURVEYNUM);
        assertThat(testKsrsacAddress.getTalukCode()).isEqualTo(UPDATED_TALUK_CODE);
        assertThat(testKsrsacAddress.getTalukName()).isEqualTo(UPDATED_TALUK_NAME);
        assertThat(testKsrsacAddress.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testKsrsacAddress.getVillageCode()).isEqualTo(UPDATED_VILLAGE_CODE);
        assertThat(testKsrsacAddress.getVillageName()).isEqualTo(UPDATED_VILLAGE_NAME);
        assertThat(testKsrsacAddress.getHobliName()).isEqualTo(UPDATED_HOBLI_NAME);
        assertThat(testKsrsacAddress.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingKsrsacAddress() throws Exception {
        int databaseSizeBeforeUpdate = ksrsacAddressRepository.findAll().size();

        // Create the KsrsacAddress
        KsrsacAddressDTO ksrsacAddressDTO = ksrsacAddressMapper.toDto(ksrsacAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKsrsacAddressMockMvc.perform(put("/api/ksrsac-addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ksrsacAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KsrsacAddress in the database
        List<KsrsacAddress> ksrsacAddressList = ksrsacAddressRepository.findAll();
        assertThat(ksrsacAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKsrsacAddress() throws Exception {
        // Initialize the database
        ksrsacAddressRepository.saveAndFlush(ksrsacAddress);

        int databaseSizeBeforeDelete = ksrsacAddressRepository.findAll().size();

        // Delete the ksrsacAddress
        restKsrsacAddressMockMvc.perform(delete("/api/ksrsac-addresses/{id}", ksrsacAddress.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KsrsacAddress> ksrsacAddressList = ksrsacAddressRepository.findAll();
        assertThat(ksrsacAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
