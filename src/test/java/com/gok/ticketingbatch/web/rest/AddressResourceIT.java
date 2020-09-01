package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.TicketingBatchApp;
import com.gok.ticketingbatch.config.TestSecurityConfiguration;
import com.gok.ticketingbatch.domain.Address;
import com.gok.ticketingbatch.repository.AddressRepository;
import com.gok.ticketingbatch.service.AddressService;
import com.gok.ticketingbatch.service.dto.AddressDTO;
import com.gok.ticketingbatch.service.mapper.AddressMapper;

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
 * Integration tests for the {@link AddressResource} REST controller.
 */
@SpringBootTest(classes = { TicketingBatchApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class AddressResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PINCODE = "AAAAAAAAAA";
    private static final String UPDATED_PINCODE = "BBBBBBBBBB";

    private static final String DEFAULT_HOUSE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HOUSE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BUILDING = "AAAAAAAAAA";
    private static final String UPDATED_BUILDING = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALITY = "AAAAAAAAAA";
    private static final String UPDATED_LOCALITY = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_TALUKA = "AAAAAAAAAA";
    private static final String UPDATED_TALUKA = "BBBBBBBBBB";

    private static final String DEFAULT_GRAM_PANCHAYAT = "AAAAAAAAAA";
    private static final String UPDATED_GRAM_PANCHAYAT = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_OR_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_CITY_OR_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_WARD = "AAAAAAAAAA";
    private static final String UPDATED_WARD = "BBBBBBBBBB";

    private static final String DEFAULT_ZONE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_LANDMARK = "AAAAAAAAAA";
    private static final String UPDATED_LANDMARK = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSEMBLY_CONSTITUENCY_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ASSEMBLY_CONSTITUENCY_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_POLLING_BOOTH_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_POLLING_BOOTH_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_LINK_ON_GOOGLE_MAP = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_LINK_ON_GOOGLE_MAP = "BBBBBBBBBB";

    private static final String DEFAULT_GOOGLE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_GOOGLE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressMockMvc;

    private Address address;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createEntity(EntityManager em) {
        Address address = new Address()
            .type(DEFAULT_TYPE)
            .locationType(DEFAULT_LOCATION_TYPE)
            .pincode(DEFAULT_PINCODE)
            .houseNumber(DEFAULT_HOUSE_NUMBER)
            .building(DEFAULT_BUILDING)
            .locality(DEFAULT_LOCALITY)
            .street(DEFAULT_STREET)
            .district(DEFAULT_DISTRICT)
            .taluka(DEFAULT_TALUKA)
            .gramPanchayat(DEFAULT_GRAM_PANCHAYAT)
            .village(DEFAULT_VILLAGE)
            .cityOrTown(DEFAULT_CITY_OR_TOWN)
            .ward(DEFAULT_WARD)
            .zone(DEFAULT_ZONE)
            .state(DEFAULT_STATE)
            .landmark(DEFAULT_LANDMARK)
            .residenceType(DEFAULT_RESIDENCE_TYPE)
            .assemblyConstituencyNumber(DEFAULT_ASSEMBLY_CONSTITUENCY_NUMBER)
            .pollingBoothNumber(DEFAULT_POLLING_BOOTH_NUMBER)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .locationLinkOnGoogleMap(DEFAULT_LOCATION_LINK_ON_GOOGLE_MAP)
            .googleAddress(DEFAULT_GOOGLE_ADDRESS)
            .status(DEFAULT_STATUS);
        return address;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createUpdatedEntity(EntityManager em) {
        Address address = new Address()
            .type(UPDATED_TYPE)
            .locationType(UPDATED_LOCATION_TYPE)
            .pincode(UPDATED_PINCODE)
            .houseNumber(UPDATED_HOUSE_NUMBER)
            .building(UPDATED_BUILDING)
            .locality(UPDATED_LOCALITY)
            .street(UPDATED_STREET)
            .district(UPDATED_DISTRICT)
            .taluka(UPDATED_TALUKA)
            .gramPanchayat(UPDATED_GRAM_PANCHAYAT)
            .village(UPDATED_VILLAGE)
            .cityOrTown(UPDATED_CITY_OR_TOWN)
            .ward(UPDATED_WARD)
            .zone(UPDATED_ZONE)
            .state(UPDATED_STATE)
            .landmark(UPDATED_LANDMARK)
            .residenceType(UPDATED_RESIDENCE_TYPE)
            .assemblyConstituencyNumber(UPDATED_ASSEMBLY_CONSTITUENCY_NUMBER)
            .pollingBoothNumber(UPDATED_POLLING_BOOTH_NUMBER)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .locationLinkOnGoogleMap(UPDATED_LOCATION_LINK_ON_GOOGLE_MAP)
            .googleAddress(UPDATED_GOOGLE_ADDRESS)
            .status(UPDATED_STATUS);
        return address;
    }

    @BeforeEach
    public void initTest() {
        address = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddress() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();
        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);
        restAddressMockMvc.perform(post("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate + 1);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAddress.getLocationType()).isEqualTo(DEFAULT_LOCATION_TYPE);
        assertThat(testAddress.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testAddress.getHouseNumber()).isEqualTo(DEFAULT_HOUSE_NUMBER);
        assertThat(testAddress.getBuilding()).isEqualTo(DEFAULT_BUILDING);
        assertThat(testAddress.getLocality()).isEqualTo(DEFAULT_LOCALITY);
        assertThat(testAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddress.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testAddress.getTaluka()).isEqualTo(DEFAULT_TALUKA);
        assertThat(testAddress.getGramPanchayat()).isEqualTo(DEFAULT_GRAM_PANCHAYAT);
        assertThat(testAddress.getVillage()).isEqualTo(DEFAULT_VILLAGE);
        assertThat(testAddress.getCityOrTown()).isEqualTo(DEFAULT_CITY_OR_TOWN);
        assertThat(testAddress.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testAddress.getZone()).isEqualTo(DEFAULT_ZONE);
        assertThat(testAddress.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAddress.getLandmark()).isEqualTo(DEFAULT_LANDMARK);
        assertThat(testAddress.getResidenceType()).isEqualTo(DEFAULT_RESIDENCE_TYPE);
        assertThat(testAddress.getAssemblyConstituencyNumber()).isEqualTo(DEFAULT_ASSEMBLY_CONSTITUENCY_NUMBER);
        assertThat(testAddress.getPollingBoothNumber()).isEqualTo(DEFAULT_POLLING_BOOTH_NUMBER);
        assertThat(testAddress.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testAddress.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testAddress.getLocationLinkOnGoogleMap()).isEqualTo(DEFAULT_LOCATION_LINK_ON_GOOGLE_MAP);
        assertThat(testAddress.getGoogleAddress()).isEqualTo(DEFAULT_GOOGLE_ADDRESS);
        assertThat(testAddress.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address with an existing ID
        address.setId(1L);
        AddressDTO addressDTO = addressMapper.toDto(address);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressMockMvc.perform(post("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get all the addressList
        restAddressMockMvc.perform(get("/api/addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(address.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].locationType").value(hasItem(DEFAULT_LOCATION_TYPE)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE)))
            .andExpect(jsonPath("$.[*].houseNumber").value(hasItem(DEFAULT_HOUSE_NUMBER)))
            .andExpect(jsonPath("$.[*].building").value(hasItem(DEFAULT_BUILDING)))
            .andExpect(jsonPath("$.[*].locality").value(hasItem(DEFAULT_LOCALITY)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].taluka").value(hasItem(DEFAULT_TALUKA)))
            .andExpect(jsonPath("$.[*].gramPanchayat").value(hasItem(DEFAULT_GRAM_PANCHAYAT)))
            .andExpect(jsonPath("$.[*].village").value(hasItem(DEFAULT_VILLAGE)))
            .andExpect(jsonPath("$.[*].cityOrTown").value(hasItem(DEFAULT_CITY_OR_TOWN)))
            .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD)))
            .andExpect(jsonPath("$.[*].zone").value(hasItem(DEFAULT_ZONE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].residenceType").value(hasItem(DEFAULT_RESIDENCE_TYPE)))
            .andExpect(jsonPath("$.[*].assemblyConstituencyNumber").value(hasItem(DEFAULT_ASSEMBLY_CONSTITUENCY_NUMBER)))
            .andExpect(jsonPath("$.[*].pollingBoothNumber").value(hasItem(DEFAULT_POLLING_BOOTH_NUMBER)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)))
            .andExpect(jsonPath("$.[*].locationLinkOnGoogleMap").value(hasItem(DEFAULT_LOCATION_LINK_ON_GOOGLE_MAP.toString())))
            .andExpect(jsonPath("$.[*].googleAddress").value(hasItem(DEFAULT_GOOGLE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(address.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.locationType").value(DEFAULT_LOCATION_TYPE))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE))
            .andExpect(jsonPath("$.houseNumber").value(DEFAULT_HOUSE_NUMBER))
            .andExpect(jsonPath("$.building").value(DEFAULT_BUILDING))
            .andExpect(jsonPath("$.locality").value(DEFAULT_LOCALITY))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.taluka").value(DEFAULT_TALUKA))
            .andExpect(jsonPath("$.gramPanchayat").value(DEFAULT_GRAM_PANCHAYAT))
            .andExpect(jsonPath("$.village").value(DEFAULT_VILLAGE))
            .andExpect(jsonPath("$.cityOrTown").value(DEFAULT_CITY_OR_TOWN))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD))
            .andExpect(jsonPath("$.zone").value(DEFAULT_ZONE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.landmark").value(DEFAULT_LANDMARK))
            .andExpect(jsonPath("$.residenceType").value(DEFAULT_RESIDENCE_TYPE))
            .andExpect(jsonPath("$.assemblyConstituencyNumber").value(DEFAULT_ASSEMBLY_CONSTITUENCY_NUMBER))
            .andExpect(jsonPath("$.pollingBoothNumber").value(DEFAULT_POLLING_BOOTH_NUMBER))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE))
            .andExpect(jsonPath("$.locationLinkOnGoogleMap").value(DEFAULT_LOCATION_LINK_ON_GOOGLE_MAP.toString()))
            .andExpect(jsonPath("$.googleAddress").value(DEFAULT_GOOGLE_ADDRESS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingAddress() throws Exception {
        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Update the address
        Address updatedAddress = addressRepository.findById(address.getId()).get();
        // Disconnect from session so that the updates on updatedAddress are not directly saved in db
        em.detach(updatedAddress);
        updatedAddress
            .type(UPDATED_TYPE)
            .locationType(UPDATED_LOCATION_TYPE)
            .pincode(UPDATED_PINCODE)
            .houseNumber(UPDATED_HOUSE_NUMBER)
            .building(UPDATED_BUILDING)
            .locality(UPDATED_LOCALITY)
            .street(UPDATED_STREET)
            .district(UPDATED_DISTRICT)
            .taluka(UPDATED_TALUKA)
            .gramPanchayat(UPDATED_GRAM_PANCHAYAT)
            .village(UPDATED_VILLAGE)
            .cityOrTown(UPDATED_CITY_OR_TOWN)
            .ward(UPDATED_WARD)
            .zone(UPDATED_ZONE)
            .state(UPDATED_STATE)
            .landmark(UPDATED_LANDMARK)
            .residenceType(UPDATED_RESIDENCE_TYPE)
            .assemblyConstituencyNumber(UPDATED_ASSEMBLY_CONSTITUENCY_NUMBER)
            .pollingBoothNumber(UPDATED_POLLING_BOOTH_NUMBER)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .locationLinkOnGoogleMap(UPDATED_LOCATION_LINK_ON_GOOGLE_MAP)
            .googleAddress(UPDATED_GOOGLE_ADDRESS)
            .status(UPDATED_STATUS);
        AddressDTO addressDTO = addressMapper.toDto(updatedAddress);

        restAddressMockMvc.perform(put("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isOk());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
        Address testAddress = addressList.get(addressList.size() - 1);
        assertThat(testAddress.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAddress.getLocationType()).isEqualTo(UPDATED_LOCATION_TYPE);
        assertThat(testAddress.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testAddress.getHouseNumber()).isEqualTo(UPDATED_HOUSE_NUMBER);
        assertThat(testAddress.getBuilding()).isEqualTo(UPDATED_BUILDING);
        assertThat(testAddress.getLocality()).isEqualTo(UPDATED_LOCALITY);
        assertThat(testAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddress.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testAddress.getTaluka()).isEqualTo(UPDATED_TALUKA);
        assertThat(testAddress.getGramPanchayat()).isEqualTo(UPDATED_GRAM_PANCHAYAT);
        assertThat(testAddress.getVillage()).isEqualTo(UPDATED_VILLAGE);
        assertThat(testAddress.getCityOrTown()).isEqualTo(UPDATED_CITY_OR_TOWN);
        assertThat(testAddress.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testAddress.getZone()).isEqualTo(UPDATED_ZONE);
        assertThat(testAddress.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAddress.getLandmark()).isEqualTo(UPDATED_LANDMARK);
        assertThat(testAddress.getResidenceType()).isEqualTo(UPDATED_RESIDENCE_TYPE);
        assertThat(testAddress.getAssemblyConstituencyNumber()).isEqualTo(UPDATED_ASSEMBLY_CONSTITUENCY_NUMBER);
        assertThat(testAddress.getPollingBoothNumber()).isEqualTo(UPDATED_POLLING_BOOTH_NUMBER);
        assertThat(testAddress.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testAddress.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testAddress.getLocationLinkOnGoogleMap()).isEqualTo(UPDATED_LOCATION_LINK_ON_GOOGLE_MAP);
        assertThat(testAddress.getGoogleAddress()).isEqualTo(UPDATED_GOOGLE_ADDRESS);
        assertThat(testAddress.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAddress() throws Exception {
        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Create the Address
        AddressDTO addressDTO = addressMapper.toDto(address);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressMockMvc.perform(put("/api/addresses").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Address in the database
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddress() throws Exception {
        // Initialize the database
        addressRepository.saveAndFlush(address);

        int databaseSizeBeforeDelete = addressRepository.findAll().size();

        // Delete the address
        restAddressMockMvc.perform(delete("/api/addresses/{id}", address.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Address> addressList = addressRepository.findAll();
        assertThat(addressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
