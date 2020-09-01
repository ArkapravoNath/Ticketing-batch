package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.TicketStatus;
import com.gok.ticketingbatch.repository.TicketStatusRepository;
import com.gok.ticketingbatch.service.TicketStatusService;
import com.gok.ticketingbatch.service.dto.TicketStatusDTO;
import com.gok.ticketingbatch.service.mapper.TicketStatusMapper;

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
 * Integration tests for the {@link TicketStatusResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TicketStatusResourceIT {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSIGNEE_ID_REF = 1L;
    private static final Long UPDATED_ASSIGNEE_ID_REF = 2L;

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @Autowired
    private TicketStatusMapper ticketStatusMapper;

    @Autowired
    private TicketStatusService ticketStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTicketStatusMockMvc;

    private TicketStatus ticketStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketStatus createEntity(EntityManager em) {
        TicketStatus ticketStatus = new TicketStatus()
            .status(DEFAULT_STATUS)
            .comments(DEFAULT_COMMENTS)
            .assigneeIdRef(DEFAULT_ASSIGNEE_ID_REF);
        return ticketStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketStatus createUpdatedEntity(EntityManager em) {
        TicketStatus ticketStatus = new TicketStatus()
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .assigneeIdRef(UPDATED_ASSIGNEE_ID_REF);
        return ticketStatus;
    }

    @BeforeEach
    public void initTest() {
        ticketStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createTicketStatus() throws Exception {
        int databaseSizeBeforeCreate = ticketStatusRepository.findAll().size();
        // Create the TicketStatus
        TicketStatusDTO ticketStatusDTO = ticketStatusMapper.toDto(ticketStatus);
        restTicketStatusMockMvc.perform(post("/api/ticket-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the TicketStatus in the database
        List<TicketStatus> ticketStatusList = ticketStatusRepository.findAll();
        assertThat(ticketStatusList).hasSize(databaseSizeBeforeCreate + 1);
        TicketStatus testTicketStatus = ticketStatusList.get(ticketStatusList.size() - 1);
        assertThat(testTicketStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTicketStatus.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testTicketStatus.getAssigneeIdRef()).isEqualTo(DEFAULT_ASSIGNEE_ID_REF);
    }

    @Test
    @Transactional
    public void createTicketStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ticketStatusRepository.findAll().size();

        // Create the TicketStatus with an existing ID
        ticketStatus.setId(1L);
        TicketStatusDTO ticketStatusDTO = ticketStatusMapper.toDto(ticketStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketStatusMockMvc.perform(post("/api/ticket-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketStatus in the database
        List<TicketStatus> ticketStatusList = ticketStatusRepository.findAll();
        assertThat(ticketStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTicketStatuses() throws Exception {
        // Initialize the database
        ticketStatusRepository.saveAndFlush(ticketStatus);

        // Get all the ticketStatusList
        restTicketStatusMockMvc.perform(get("/api/ticket-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticketStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].assigneeIdRef").value(hasItem(DEFAULT_ASSIGNEE_ID_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getTicketStatus() throws Exception {
        // Initialize the database
        ticketStatusRepository.saveAndFlush(ticketStatus);

        // Get the ticketStatus
        restTicketStatusMockMvc.perform(get("/api/ticket-statuses/{id}", ticketStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticketStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.assigneeIdRef").value(DEFAULT_ASSIGNEE_ID_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTicketStatus() throws Exception {
        // Get the ticketStatus
        restTicketStatusMockMvc.perform(get("/api/ticket-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTicketStatus() throws Exception {
        // Initialize the database
        ticketStatusRepository.saveAndFlush(ticketStatus);

        int databaseSizeBeforeUpdate = ticketStatusRepository.findAll().size();

        // Update the ticketStatus
        TicketStatus updatedTicketStatus = ticketStatusRepository.findById(ticketStatus.getId()).get();
        // Disconnect from session so that the updates on updatedTicketStatus are not directly saved in db
        em.detach(updatedTicketStatus);
        updatedTicketStatus
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .assigneeIdRef(UPDATED_ASSIGNEE_ID_REF);
        TicketStatusDTO ticketStatusDTO = ticketStatusMapper.toDto(updatedTicketStatus);

        restTicketStatusMockMvc.perform(put("/api/ticket-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketStatusDTO)))
            .andExpect(status().isOk());

        // Validate the TicketStatus in the database
        List<TicketStatus> ticketStatusList = ticketStatusRepository.findAll();
        assertThat(ticketStatusList).hasSize(databaseSizeBeforeUpdate);
        TicketStatus testTicketStatus = ticketStatusList.get(ticketStatusList.size() - 1);
        assertThat(testTicketStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTicketStatus.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testTicketStatus.getAssigneeIdRef()).isEqualTo(UPDATED_ASSIGNEE_ID_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingTicketStatus() throws Exception {
        int databaseSizeBeforeUpdate = ticketStatusRepository.findAll().size();

        // Create the TicketStatus
        TicketStatusDTO ticketStatusDTO = ticketStatusMapper.toDto(ticketStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketStatusMockMvc.perform(put("/api/ticket-statuses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TicketStatus in the database
        List<TicketStatus> ticketStatusList = ticketStatusRepository.findAll();
        assertThat(ticketStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTicketStatus() throws Exception {
        // Initialize the database
        ticketStatusRepository.saveAndFlush(ticketStatus);

        int databaseSizeBeforeDelete = ticketStatusRepository.findAll().size();

        // Delete the ticketStatus
        restTicketStatusMockMvc.perform(delete("/api/ticket-statuses/{id}", ticketStatus.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TicketStatus> ticketStatusList = ticketStatusRepository.findAll();
        assertThat(ticketStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
