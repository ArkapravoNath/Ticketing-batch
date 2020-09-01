package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Person;
import com.gok.ticketingbatch.repository.PersonRepository;
import com.gok.ticketingbatch.service.PersonService;
import com.gok.ticketingbatch.service.dto.PersonDTO;
import com.gok.ticketingbatch.service.mapper.PersonMapper;

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
 * Integration tests for the {@link PersonResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PersonResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_AGE = "AAAAAAAAAA";
    private static final String UPDATED_AGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DOB = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOB = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_CITIZEN_SHIP = "AAAAAAAAAA";
    private static final String UPDATED_CITIZEN_SHIP = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_TELEPHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_TELEPHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDAY_TELEPHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_SECONDAY_TELEPHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_OCCUPATION = "AAAAAAAAAA";
    private static final String UPDATED_OCCUPATION = "BBBBBBBBBB";

    private static final String DEFAULT_OCCUPATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OCCUPATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALE = "AAAAAAAAAA";
    private static final String UPDATED_LOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_HEAD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_HEAD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_HEAD_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_HEAD_RELATIONSHIP = "BBBBBBBBBB";

    private static final Long DEFAULT_FAMILY_ADULT_COUNT = 1L;
    private static final Long UPDATED_FAMILY_ADULT_COUNT = 2L;

    private static final String DEFAULT_GUARDIAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GUARDIAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonMockMvc;

    private Person person;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createEntity(EntityManager em) {
        Person person = new Person()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .gender(DEFAULT_GENDER)
            .age(DEFAULT_AGE)
            .dob(DEFAULT_DOB)
            .nationality(DEFAULT_NATIONALITY)
            .citizenShip(DEFAULT_CITIZEN_SHIP)
            .primaryTelephoneNo(DEFAULT_PRIMARY_TELEPHONE_NO)
            .secondayTelephoneNo(DEFAULT_SECONDAY_TELEPHONE_NO)
            .occupation(DEFAULT_OCCUPATION)
            .occupationName(DEFAULT_OCCUPATION_NAME)
            .alternateNumber(DEFAULT_ALTERNATE_NUMBER)
            .language(DEFAULT_LANGUAGE)
            .locale(DEFAULT_LOCALE)
            .familyHeadName(DEFAULT_FAMILY_HEAD_NAME)
            .familyHeadRelationship(DEFAULT_FAMILY_HEAD_RELATIONSHIP)
            .familyAdultCount(DEFAULT_FAMILY_ADULT_COUNT)
            .guardianName(DEFAULT_GUARDIAN_NAME)
            .status(DEFAULT_STATUS);
        return person;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createUpdatedEntity(EntityManager em) {
        Person person = new Person()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .fullName(UPDATED_FULL_NAME)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .dob(UPDATED_DOB)
            .nationality(UPDATED_NATIONALITY)
            .citizenShip(UPDATED_CITIZEN_SHIP)
            .primaryTelephoneNo(UPDATED_PRIMARY_TELEPHONE_NO)
            .secondayTelephoneNo(UPDATED_SECONDAY_TELEPHONE_NO)
            .occupation(UPDATED_OCCUPATION)
            .occupationName(UPDATED_OCCUPATION_NAME)
            .alternateNumber(UPDATED_ALTERNATE_NUMBER)
            .language(UPDATED_LANGUAGE)
            .locale(UPDATED_LOCALE)
            .familyHeadName(UPDATED_FAMILY_HEAD_NAME)
            .familyHeadRelationship(UPDATED_FAMILY_HEAD_RELATIONSHIP)
            .familyAdultCount(UPDATED_FAMILY_ADULT_COUNT)
            .guardianName(UPDATED_GUARDIAN_NAME)
            .status(UPDATED_STATUS);
        return person;
    }

    @BeforeEach
    public void initTest() {
        person = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerson() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();
        // Create the Person
        PersonDTO personDTO = personMapper.toDto(person);
        restPersonMockMvc.perform(post("/api/people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isCreated());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate + 1);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPerson.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testPerson.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPerson.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testPerson.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPerson.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPerson.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testPerson.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testPerson.getCitizenShip()).isEqualTo(DEFAULT_CITIZEN_SHIP);
        assertThat(testPerson.getPrimaryTelephoneNo()).isEqualTo(DEFAULT_PRIMARY_TELEPHONE_NO);
        assertThat(testPerson.getSecondayTelephoneNo()).isEqualTo(DEFAULT_SECONDAY_TELEPHONE_NO);
        assertThat(testPerson.getOccupation()).isEqualTo(DEFAULT_OCCUPATION);
        assertThat(testPerson.getOccupationName()).isEqualTo(DEFAULT_OCCUPATION_NAME);
        assertThat(testPerson.getAlternateNumber()).isEqualTo(DEFAULT_ALTERNATE_NUMBER);
        assertThat(testPerson.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testPerson.getLocale()).isEqualTo(DEFAULT_LOCALE);
        assertThat(testPerson.getFamilyHeadName()).isEqualTo(DEFAULT_FAMILY_HEAD_NAME);
        assertThat(testPerson.getFamilyHeadRelationship()).isEqualTo(DEFAULT_FAMILY_HEAD_RELATIONSHIP);
        assertThat(testPerson.getFamilyAdultCount()).isEqualTo(DEFAULT_FAMILY_ADULT_COUNT);
        assertThat(testPerson.getGuardianName()).isEqualTo(DEFAULT_GUARDIAN_NAME);
        assertThat(testPerson.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person with an existing ID
        person.setId(1L);
        PersonDTO personDTO = personMapper.toDto(person);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonMockMvc.perform(post("/api/people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPeople() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList
        restPersonMockMvc.perform(get("/api/people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].citizenShip").value(hasItem(DEFAULT_CITIZEN_SHIP)))
            .andExpect(jsonPath("$.[*].primaryTelephoneNo").value(hasItem(DEFAULT_PRIMARY_TELEPHONE_NO)))
            .andExpect(jsonPath("$.[*].secondayTelephoneNo").value(hasItem(DEFAULT_SECONDAY_TELEPHONE_NO)))
            .andExpect(jsonPath("$.[*].occupation").value(hasItem(DEFAULT_OCCUPATION)))
            .andExpect(jsonPath("$.[*].occupationName").value(hasItem(DEFAULT_OCCUPATION_NAME)))
            .andExpect(jsonPath("$.[*].alternateNumber").value(hasItem(DEFAULT_ALTERNATE_NUMBER)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].locale").value(hasItem(DEFAULT_LOCALE)))
            .andExpect(jsonPath("$.[*].familyHeadName").value(hasItem(DEFAULT_FAMILY_HEAD_NAME)))
            .andExpect(jsonPath("$.[*].familyHeadRelationship").value(hasItem(DEFAULT_FAMILY_HEAD_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].familyAdultCount").value(hasItem(DEFAULT_FAMILY_ADULT_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].guardianName").value(hasItem(DEFAULT_GUARDIAN_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getPerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", person.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(person.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.citizenShip").value(DEFAULT_CITIZEN_SHIP))
            .andExpect(jsonPath("$.primaryTelephoneNo").value(DEFAULT_PRIMARY_TELEPHONE_NO))
            .andExpect(jsonPath("$.secondayTelephoneNo").value(DEFAULT_SECONDAY_TELEPHONE_NO))
            .andExpect(jsonPath("$.occupation").value(DEFAULT_OCCUPATION))
            .andExpect(jsonPath("$.occupationName").value(DEFAULT_OCCUPATION_NAME))
            .andExpect(jsonPath("$.alternateNumber").value(DEFAULT_ALTERNATE_NUMBER))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.locale").value(DEFAULT_LOCALE))
            .andExpect(jsonPath("$.familyHeadName").value(DEFAULT_FAMILY_HEAD_NAME))
            .andExpect(jsonPath("$.familyHeadRelationship").value(DEFAULT_FAMILY_HEAD_RELATIONSHIP))
            .andExpect(jsonPath("$.familyAdultCount").value(DEFAULT_FAMILY_ADULT_COUNT.intValue()))
            .andExpect(jsonPath("$.guardianName").value(DEFAULT_GUARDIAN_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingPerson() throws Exception {
        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Update the person
        Person updatedPerson = personRepository.findById(person.getId()).get();
        // Disconnect from session so that the updates on updatedPerson are not directly saved in db
        em.detach(updatedPerson);
        updatedPerson
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .fullName(UPDATED_FULL_NAME)
            .gender(UPDATED_GENDER)
            .age(UPDATED_AGE)
            .dob(UPDATED_DOB)
            .nationality(UPDATED_NATIONALITY)
            .citizenShip(UPDATED_CITIZEN_SHIP)
            .primaryTelephoneNo(UPDATED_PRIMARY_TELEPHONE_NO)
            .secondayTelephoneNo(UPDATED_SECONDAY_TELEPHONE_NO)
            .occupation(UPDATED_OCCUPATION)
            .occupationName(UPDATED_OCCUPATION_NAME)
            .alternateNumber(UPDATED_ALTERNATE_NUMBER)
            .language(UPDATED_LANGUAGE)
            .locale(UPDATED_LOCALE)
            .familyHeadName(UPDATED_FAMILY_HEAD_NAME)
            .familyHeadRelationship(UPDATED_FAMILY_HEAD_RELATIONSHIP)
            .familyAdultCount(UPDATED_FAMILY_ADULT_COUNT)
            .guardianName(UPDATED_GUARDIAN_NAME)
            .status(UPDATED_STATUS);
        PersonDTO personDTO = personMapper.toDto(updatedPerson);

        restPersonMockMvc.perform(put("/api/people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isOk());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPerson.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPerson.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPerson.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testPerson.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPerson.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPerson.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testPerson.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testPerson.getCitizenShip()).isEqualTo(UPDATED_CITIZEN_SHIP);
        assertThat(testPerson.getPrimaryTelephoneNo()).isEqualTo(UPDATED_PRIMARY_TELEPHONE_NO);
        assertThat(testPerson.getSecondayTelephoneNo()).isEqualTo(UPDATED_SECONDAY_TELEPHONE_NO);
        assertThat(testPerson.getOccupation()).isEqualTo(UPDATED_OCCUPATION);
        assertThat(testPerson.getOccupationName()).isEqualTo(UPDATED_OCCUPATION_NAME);
        assertThat(testPerson.getAlternateNumber()).isEqualTo(UPDATED_ALTERNATE_NUMBER);
        assertThat(testPerson.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testPerson.getLocale()).isEqualTo(UPDATED_LOCALE);
        assertThat(testPerson.getFamilyHeadName()).isEqualTo(UPDATED_FAMILY_HEAD_NAME);
        assertThat(testPerson.getFamilyHeadRelationship()).isEqualTo(UPDATED_FAMILY_HEAD_RELATIONSHIP);
        assertThat(testPerson.getFamilyAdultCount()).isEqualTo(UPDATED_FAMILY_ADULT_COUNT);
        assertThat(testPerson.getGuardianName()).isEqualTo(UPDATED_GUARDIAN_NAME);
        assertThat(testPerson.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPerson() throws Exception {
        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Create the Person
        PersonDTO personDTO = personMapper.toDto(person);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonMockMvc.perform(put("/api/people").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeDelete = personRepository.findAll().size();

        // Delete the person
        restPersonMockMvc.perform(delete("/api/people/{id}", person.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
