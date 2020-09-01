package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Mobile;
import com.gok.ticketingbatch.repository.MobileRepository;
import com.gok.ticketingbatch.service.MobileService;
import com.gok.ticketingbatch.service.dto.MobileDTO;
import com.gok.ticketingbatch.service.mapper.MobileMapper;

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
 * Integration tests for the {@link MobileResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class MobileResourceIT {

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private MobileMapper mobileMapper;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMobileMockMvc;

    private Mobile mobile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mobile createEntity(EntityManager em) {
        Mobile mobile = new Mobile()
            .number(DEFAULT_NUMBER)
            .metadata(DEFAULT_METADATA);
        return mobile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mobile createUpdatedEntity(EntityManager em) {
        Mobile mobile = new Mobile()
            .number(UPDATED_NUMBER)
            .metadata(UPDATED_METADATA);
        return mobile;
    }

    @BeforeEach
    public void initTest() {
        mobile = createEntity(em);
    }

    @Test
    @Transactional
    public void createMobile() throws Exception {
        int databaseSizeBeforeCreate = mobileRepository.findAll().size();
        // Create the Mobile
        MobileDTO mobileDTO = mobileMapper.toDto(mobile);
        restMobileMockMvc.perform(post("/api/mobiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mobileDTO)))
            .andExpect(status().isCreated());

        // Validate the Mobile in the database
        List<Mobile> mobileList = mobileRepository.findAll();
        assertThat(mobileList).hasSize(databaseSizeBeforeCreate + 1);
        Mobile testMobile = mobileList.get(mobileList.size() - 1);
        assertThat(testMobile.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testMobile.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    @Transactional
    public void createMobileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mobileRepository.findAll().size();

        // Create the Mobile with an existing ID
        mobile.setId(1L);
        MobileDTO mobileDTO = mobileMapper.toDto(mobile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMobileMockMvc.perform(post("/api/mobiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mobile in the database
        List<Mobile> mobileList = mobileRepository.findAll();
        assertThat(mobileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMobiles() throws Exception {
        // Initialize the database
        mobileRepository.saveAndFlush(mobile);

        // Get all the mobileList
        restMobileMockMvc.perform(get("/api/mobiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mobile.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)));
    }
    
    @Test
    @Transactional
    public void getMobile() throws Exception {
        // Initialize the database
        mobileRepository.saveAndFlush(mobile);

        // Get the mobile
        restMobileMockMvc.perform(get("/api/mobiles/{id}", mobile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mobile.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA));
    }
    @Test
    @Transactional
    public void getNonExistingMobile() throws Exception {
        // Get the mobile
        restMobileMockMvc.perform(get("/api/mobiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMobile() throws Exception {
        // Initialize the database
        mobileRepository.saveAndFlush(mobile);

        int databaseSizeBeforeUpdate = mobileRepository.findAll().size();

        // Update the mobile
        Mobile updatedMobile = mobileRepository.findById(mobile.getId()).get();
        // Disconnect from session so that the updates on updatedMobile are not directly saved in db
        em.detach(updatedMobile);
        updatedMobile
            .number(UPDATED_NUMBER)
            .metadata(UPDATED_METADATA);
        MobileDTO mobileDTO = mobileMapper.toDto(updatedMobile);

        restMobileMockMvc.perform(put("/api/mobiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mobileDTO)))
            .andExpect(status().isOk());

        // Validate the Mobile in the database
        List<Mobile> mobileList = mobileRepository.findAll();
        assertThat(mobileList).hasSize(databaseSizeBeforeUpdate);
        Mobile testMobile = mobileList.get(mobileList.size() - 1);
        assertThat(testMobile.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testMobile.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    @Transactional
    public void updateNonExistingMobile() throws Exception {
        int databaseSizeBeforeUpdate = mobileRepository.findAll().size();

        // Create the Mobile
        MobileDTO mobileDTO = mobileMapper.toDto(mobile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMobileMockMvc.perform(put("/api/mobiles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mobile in the database
        List<Mobile> mobileList = mobileRepository.findAll();
        assertThat(mobileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMobile() throws Exception {
        // Initialize the database
        mobileRepository.saveAndFlush(mobile);

        int databaseSizeBeforeDelete = mobileRepository.findAll().size();

        // Delete the mobile
        restMobileMockMvc.perform(delete("/api/mobiles/{id}", mobile.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mobile> mobileList = mobileRepository.findAll();
        assertThat(mobileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
