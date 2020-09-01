package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Tags;
import com.gok.ticketingbatch.repository.TagsRepository;
import com.gok.ticketingbatch.service.TagsService;
import com.gok.ticketingbatch.service.dto.TagsDTO;
import com.gok.ticketingbatch.service.mapper.TagsMapper;

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
 * Integration tests for the {@link TagsResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TagsResourceIT {

    private static final String DEFAULT_TAG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_TAG_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_HEALTH_CONDITION_TAG = "AAAAAAAAAA";
    private static final String UPDATED_HEALTH_CONDITION_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_COVID_POSITIVE_TAG = "AAAAAAAAAA";
    private static final String UPDATED_COVID_POSITIVE_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_FOLLOW_UP_TAG = "AAAAAAAAAA";
    private static final String UPDATED_FOLLOW_UP_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_TREATMENT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TREATMENT_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagsMockMvc;

    private Tags tags;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tags createEntity(EntityManager em) {
        Tags tags = new Tags()
            .tagName(DEFAULT_TAG_NAME)
            .tagValue(DEFAULT_TAG_VALUE)
            .healthConditionTag(DEFAULT_HEALTH_CONDITION_TAG)
            .covidPositiveTag(DEFAULT_COVID_POSITIVE_TAG)
            .followUpTag(DEFAULT_FOLLOW_UP_TAG)
            .treatmentTag(DEFAULT_TREATMENT_TAG)
            .metadata(DEFAULT_METADATA);
        return tags;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tags createUpdatedEntity(EntityManager em) {
        Tags tags = new Tags()
            .tagName(UPDATED_TAG_NAME)
            .tagValue(UPDATED_TAG_VALUE)
            .healthConditionTag(UPDATED_HEALTH_CONDITION_TAG)
            .covidPositiveTag(UPDATED_COVID_POSITIVE_TAG)
            .followUpTag(UPDATED_FOLLOW_UP_TAG)
            .treatmentTag(UPDATED_TREATMENT_TAG)
            .metadata(UPDATED_METADATA);
        return tags;
    }

    @BeforeEach
    public void initTest() {
        tags = createEntity(em);
    }

    @Test
    @Transactional
    public void createTags() throws Exception {
        int databaseSizeBeforeCreate = tagsRepository.findAll().size();
        // Create the Tags
        TagsDTO tagsDTO = tagsMapper.toDto(tags);
        restTagsMockMvc.perform(post("/api/tags").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isCreated());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeCreate + 1);
        Tags testTags = tagsList.get(tagsList.size() - 1);
        assertThat(testTags.getTagName()).isEqualTo(DEFAULT_TAG_NAME);
        assertThat(testTags.getTagValue()).isEqualTo(DEFAULT_TAG_VALUE);
        assertThat(testTags.getHealthConditionTag()).isEqualTo(DEFAULT_HEALTH_CONDITION_TAG);
        assertThat(testTags.getCovidPositiveTag()).isEqualTo(DEFAULT_COVID_POSITIVE_TAG);
        assertThat(testTags.getFollowUpTag()).isEqualTo(DEFAULT_FOLLOW_UP_TAG);
        assertThat(testTags.getTreatmentTag()).isEqualTo(DEFAULT_TREATMENT_TAG);
        assertThat(testTags.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    @Transactional
    public void createTagsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tagsRepository.findAll().size();

        // Create the Tags with an existing ID
        tags.setId(1L);
        TagsDTO tagsDTO = tagsMapper.toDto(tags);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagsMockMvc.perform(post("/api/tags").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        // Get all the tagsList
        restTagsMockMvc.perform(get("/api/tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tags.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagName").value(hasItem(DEFAULT_TAG_NAME)))
            .andExpect(jsonPath("$.[*].tagValue").value(hasItem(DEFAULT_TAG_VALUE)))
            .andExpect(jsonPath("$.[*].healthConditionTag").value(hasItem(DEFAULT_HEALTH_CONDITION_TAG)))
            .andExpect(jsonPath("$.[*].covidPositiveTag").value(hasItem(DEFAULT_COVID_POSITIVE_TAG)))
            .andExpect(jsonPath("$.[*].followUpTag").value(hasItem(DEFAULT_FOLLOW_UP_TAG)))
            .andExpect(jsonPath("$.[*].treatmentTag").value(hasItem(DEFAULT_TREATMENT_TAG)))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)));
    }
    
    @Test
    @Transactional
    public void getTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        // Get the tags
        restTagsMockMvc.perform(get("/api/tags/{id}", tags.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tags.getId().intValue()))
            .andExpect(jsonPath("$.tagName").value(DEFAULT_TAG_NAME))
            .andExpect(jsonPath("$.tagValue").value(DEFAULT_TAG_VALUE))
            .andExpect(jsonPath("$.healthConditionTag").value(DEFAULT_HEALTH_CONDITION_TAG))
            .andExpect(jsonPath("$.covidPositiveTag").value(DEFAULT_COVID_POSITIVE_TAG))
            .andExpect(jsonPath("$.followUpTag").value(DEFAULT_FOLLOW_UP_TAG))
            .andExpect(jsonPath("$.treatmentTag").value(DEFAULT_TREATMENT_TAG))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA));
    }
    @Test
    @Transactional
    public void getNonExistingTags() throws Exception {
        // Get the tags
        restTagsMockMvc.perform(get("/api/tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        int databaseSizeBeforeUpdate = tagsRepository.findAll().size();

        // Update the tags
        Tags updatedTags = tagsRepository.findById(tags.getId()).get();
        // Disconnect from session so that the updates on updatedTags are not directly saved in db
        em.detach(updatedTags);
        updatedTags
            .tagName(UPDATED_TAG_NAME)
            .tagValue(UPDATED_TAG_VALUE)
            .healthConditionTag(UPDATED_HEALTH_CONDITION_TAG)
            .covidPositiveTag(UPDATED_COVID_POSITIVE_TAG)
            .followUpTag(UPDATED_FOLLOW_UP_TAG)
            .treatmentTag(UPDATED_TREATMENT_TAG)
            .metadata(UPDATED_METADATA);
        TagsDTO tagsDTO = tagsMapper.toDto(updatedTags);

        restTagsMockMvc.perform(put("/api/tags").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isOk());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeUpdate);
        Tags testTags = tagsList.get(tagsList.size() - 1);
        assertThat(testTags.getTagName()).isEqualTo(UPDATED_TAG_NAME);
        assertThat(testTags.getTagValue()).isEqualTo(UPDATED_TAG_VALUE);
        assertThat(testTags.getHealthConditionTag()).isEqualTo(UPDATED_HEALTH_CONDITION_TAG);
        assertThat(testTags.getCovidPositiveTag()).isEqualTo(UPDATED_COVID_POSITIVE_TAG);
        assertThat(testTags.getFollowUpTag()).isEqualTo(UPDATED_FOLLOW_UP_TAG);
        assertThat(testTags.getTreatmentTag()).isEqualTo(UPDATED_TREATMENT_TAG);
        assertThat(testTags.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    @Transactional
    public void updateNonExistingTags() throws Exception {
        int databaseSizeBeforeUpdate = tagsRepository.findAll().size();

        // Create the Tags
        TagsDTO tagsDTO = tagsMapper.toDto(tags);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagsMockMvc.perform(put("/api/tags").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tags in the database
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTags() throws Exception {
        // Initialize the database
        tagsRepository.saveAndFlush(tags);

        int databaseSizeBeforeDelete = tagsRepository.findAll().size();

        // Delete the tags
        restTagsMockMvc.perform(delete("/api/tags/{id}", tags.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tags> tagsList = tagsRepository.findAll();
        assertThat(tagsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
