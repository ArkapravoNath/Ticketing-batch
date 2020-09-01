package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.RelativeContact;
import com.gok.ticketingbatch.repository.RelativeContactRepository;
import com.gok.ticketingbatch.service.RelativeContactService;
import com.gok.ticketingbatch.service.dto.RelativeContactDTO;
import com.gok.ticketingbatch.service.mapper.RelativeContactMapper;

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
 * Integration tests for the {@link RelativeContactResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class RelativeContactResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_TYPE = "BBBBBBBBBB";

    @Autowired
    private RelativeContactRepository relativeContactRepository;

    @Autowired
    private RelativeContactMapper relativeContactMapper;

    @Autowired
    private RelativeContactService relativeContactService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelativeContactMockMvc;

    private RelativeContact relativeContact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelativeContact createEntity(EntityManager em) {
        RelativeContact relativeContact = new RelativeContact()
            .type(DEFAULT_TYPE)
            .relationshipType(DEFAULT_RELATIONSHIP_TYPE);
        return relativeContact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelativeContact createUpdatedEntity(EntityManager em) {
        RelativeContact relativeContact = new RelativeContact()
            .type(UPDATED_TYPE)
            .relationshipType(UPDATED_RELATIONSHIP_TYPE);
        return relativeContact;
    }

    @BeforeEach
    public void initTest() {
        relativeContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelativeContact() throws Exception {
        int databaseSizeBeforeCreate = relativeContactRepository.findAll().size();
        // Create the RelativeContact
        RelativeContactDTO relativeContactDTO = relativeContactMapper.toDto(relativeContact);
        restRelativeContactMockMvc.perform(post("/api/relative-contacts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relativeContactDTO)))
            .andExpect(status().isCreated());

        // Validate the RelativeContact in the database
        List<RelativeContact> relativeContactList = relativeContactRepository.findAll();
        assertThat(relativeContactList).hasSize(databaseSizeBeforeCreate + 1);
        RelativeContact testRelativeContact = relativeContactList.get(relativeContactList.size() - 1);
        assertThat(testRelativeContact.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testRelativeContact.getRelationshipType()).isEqualTo(DEFAULT_RELATIONSHIP_TYPE);
    }

    @Test
    @Transactional
    public void createRelativeContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relativeContactRepository.findAll().size();

        // Create the RelativeContact with an existing ID
        relativeContact.setId(1L);
        RelativeContactDTO relativeContactDTO = relativeContactMapper.toDto(relativeContact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelativeContactMockMvc.perform(post("/api/relative-contacts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relativeContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelativeContact in the database
        List<RelativeContact> relativeContactList = relativeContactRepository.findAll();
        assertThat(relativeContactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRelativeContacts() throws Exception {
        // Initialize the database
        relativeContactRepository.saveAndFlush(relativeContact);

        // Get all the relativeContactList
        restRelativeContactMockMvc.perform(get("/api/relative-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relativeContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].relationshipType").value(hasItem(DEFAULT_RELATIONSHIP_TYPE)));
    }
    
    @Test
    @Transactional
    public void getRelativeContact() throws Exception {
        // Initialize the database
        relativeContactRepository.saveAndFlush(relativeContact);

        // Get the relativeContact
        restRelativeContactMockMvc.perform(get("/api/relative-contacts/{id}", relativeContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relativeContact.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.relationshipType").value(DEFAULT_RELATIONSHIP_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingRelativeContact() throws Exception {
        // Get the relativeContact
        restRelativeContactMockMvc.perform(get("/api/relative-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelativeContact() throws Exception {
        // Initialize the database
        relativeContactRepository.saveAndFlush(relativeContact);

        int databaseSizeBeforeUpdate = relativeContactRepository.findAll().size();

        // Update the relativeContact
        RelativeContact updatedRelativeContact = relativeContactRepository.findById(relativeContact.getId()).get();
        // Disconnect from session so that the updates on updatedRelativeContact are not directly saved in db
        em.detach(updatedRelativeContact);
        updatedRelativeContact
            .type(UPDATED_TYPE)
            .relationshipType(UPDATED_RELATIONSHIP_TYPE);
        RelativeContactDTO relativeContactDTO = relativeContactMapper.toDto(updatedRelativeContact);

        restRelativeContactMockMvc.perform(put("/api/relative-contacts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relativeContactDTO)))
            .andExpect(status().isOk());

        // Validate the RelativeContact in the database
        List<RelativeContact> relativeContactList = relativeContactRepository.findAll();
        assertThat(relativeContactList).hasSize(databaseSizeBeforeUpdate);
        RelativeContact testRelativeContact = relativeContactList.get(relativeContactList.size() - 1);
        assertThat(testRelativeContact.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testRelativeContact.getRelationshipType()).isEqualTo(UPDATED_RELATIONSHIP_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingRelativeContact() throws Exception {
        int databaseSizeBeforeUpdate = relativeContactRepository.findAll().size();

        // Create the RelativeContact
        RelativeContactDTO relativeContactDTO = relativeContactMapper.toDto(relativeContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelativeContactMockMvc.perform(put("/api/relative-contacts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(relativeContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelativeContact in the database
        List<RelativeContact> relativeContactList = relativeContactRepository.findAll();
        assertThat(relativeContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelativeContact() throws Exception {
        // Initialize the database
        relativeContactRepository.saveAndFlush(relativeContact);

        int databaseSizeBeforeDelete = relativeContactRepository.findAll().size();

        // Delete the relativeContact
        restRelativeContactMockMvc.perform(delete("/api/relative-contacts/{id}", relativeContact.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelativeContact> relativeContactList = relativeContactRepository.findAll();
        assertThat(relativeContactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
