package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Ticket;
import com.gok.ticketingbatch.repository.TicketRepository;
import com.gok.ticketingbatch.service.TicketService;
import com.gok.ticketingbatch.service.dto.TicketDTO;
import com.gok.ticketingbatch.service.mapper.TicketMapper;

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
 * Integration tests for the {@link TicketResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TicketResourceIT {

    private static final String DEFAULT_TICKET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TICKET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUBCATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SUBCATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_PATINET_ID_REF = 1L;
    private static final Long UPDATED_PATINET_ID_REF = 2L;

    private static final Long DEFAULT_TICKET_ID_REF = 1L;
    private static final Long UPDATED_TICKET_ID_REF = 2L;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTicketMockMvc;

    private Ticket ticket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .ticketName(DEFAULT_TICKET_NAME)
            .status(DEFAULT_STATUS)
            .state(DEFAULT_STATE)
            .channel(DEFAULT_CHANNEL)
            .category(DEFAULT_CATEGORY)
            .subcategory(DEFAULT_SUBCATEGORY)
            .contactDetails(DEFAULT_CONTACT_DETAILS)
            .mobileNumber(DEFAULT_MOBILE_NUMBER)
            .patinetIdRef(DEFAULT_PATINET_ID_REF)
            .ticketIdRef(DEFAULT_TICKET_ID_REF);
        return ticket;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createUpdatedEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .ticketName(UPDATED_TICKET_NAME)
            .status(UPDATED_STATUS)
            .state(UPDATED_STATE)
            .channel(UPDATED_CHANNEL)
            .category(UPDATED_CATEGORY)
            .subcategory(UPDATED_SUBCATEGORY)
            .contactDetails(UPDATED_CONTACT_DETAILS)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .patinetIdRef(UPDATED_PATINET_ID_REF)
            .ticketIdRef(UPDATED_TICKET_ID_REF);
        return ticket;
    }

    @BeforeEach
    public void initTest() {
        ticket = createEntity(em);
    }

    @Test
    @Transactional
    public void createTicket() throws Exception {
        int databaseSizeBeforeCreate = ticketRepository.findAll().size();
        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);
        restTicketMockMvc.perform(post("/api/tickets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isCreated());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate + 1);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTicketName()).isEqualTo(DEFAULT_TICKET_NAME);
        assertThat(testTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTicket.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testTicket.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testTicket.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTicket.getSubcategory()).isEqualTo(DEFAULT_SUBCATEGORY);
        assertThat(testTicket.getContactDetails()).isEqualTo(DEFAULT_CONTACT_DETAILS);
        assertThat(testTicket.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testTicket.getPatinetIdRef()).isEqualTo(DEFAULT_PATINET_ID_REF);
        assertThat(testTicket.getTicketIdRef()).isEqualTo(DEFAULT_TICKET_ID_REF);
    }

    @Test
    @Transactional
    public void createTicketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ticketRepository.findAll().size();

        // Create the Ticket with an existing ID
        ticket.setId(1L);
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketMockMvc.perform(post("/api/tickets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTickets() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get all the ticketList
        restTicketMockMvc.perform(get("/api/tickets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticket.getId().intValue())))
            .andExpect(jsonPath("$.[*].ticketName").value(hasItem(DEFAULT_TICKET_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].subcategory").value(hasItem(DEFAULT_SUBCATEGORY)))
            .andExpect(jsonPath("$.[*].contactDetails").value(hasItem(DEFAULT_CONTACT_DETAILS)))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].patinetIdRef").value(hasItem(DEFAULT_PATINET_ID_REF.intValue())))
            .andExpect(jsonPath("$.[*].ticketIdRef").value(hasItem(DEFAULT_TICKET_ID_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get the ticket
        restTicketMockMvc.perform(get("/api/tickets/{id}", ticket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticket.getId().intValue()))
            .andExpect(jsonPath("$.ticketName").value(DEFAULT_TICKET_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.subcategory").value(DEFAULT_SUBCATEGORY))
            .andExpect(jsonPath("$.contactDetails").value(DEFAULT_CONTACT_DETAILS))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER))
            .andExpect(jsonPath("$.patinetIdRef").value(DEFAULT_PATINET_ID_REF.intValue()))
            .andExpect(jsonPath("$.ticketIdRef").value(DEFAULT_TICKET_ID_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTicket() throws Exception {
        // Get the ticket
        restTicketMockMvc.perform(get("/api/tickets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket
        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).get();
        // Disconnect from session so that the updates on updatedTicket are not directly saved in db
        em.detach(updatedTicket);
        updatedTicket
            .ticketName(UPDATED_TICKET_NAME)
            .status(UPDATED_STATUS)
            .state(UPDATED_STATE)
            .channel(UPDATED_CHANNEL)
            .category(UPDATED_CATEGORY)
            .subcategory(UPDATED_SUBCATEGORY)
            .contactDetails(UPDATED_CONTACT_DETAILS)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .patinetIdRef(UPDATED_PATINET_ID_REF)
            .ticketIdRef(UPDATED_TICKET_ID_REF);
        TicketDTO ticketDTO = ticketMapper.toDto(updatedTicket);

        restTicketMockMvc.perform(put("/api/tickets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getTicketName()).isEqualTo(UPDATED_TICKET_NAME);
        assertThat(testTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTicket.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testTicket.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testTicket.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTicket.getSubcategory()).isEqualTo(UPDATED_SUBCATEGORY);
        assertThat(testTicket.getContactDetails()).isEqualTo(UPDATED_CONTACT_DETAILS);
        assertThat(testTicket.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testTicket.getPatinetIdRef()).isEqualTo(UPDATED_PATINET_ID_REF);
        assertThat(testTicket.getTicketIdRef()).isEqualTo(UPDATED_TICKET_ID_REF);
    }

    @Test
    @Transactional
    public void updateNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc.perform(put("/api/tickets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeDelete = ticketRepository.findAll().size();

        // Delete the ticket
        restTicketMockMvc.perform(delete("/api/tickets/{id}", ticket.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
