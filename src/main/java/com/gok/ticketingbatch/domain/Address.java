package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "location_type")
    private String locationType;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "building")
    private String building;

    @Column(name = "locality")
    private String locality;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "taluka")
    private String taluka;

    @Column(name = "gram_panchayat")
    private String gramPanchayat;

    @Column(name = "village")
    private String village;

    @Column(name = "city_or_town")
    private String cityOrTown;

    @Column(name = "ward")
    private String ward;

    @Column(name = "zone")
    private String zone;

    @Column(name = "state")
    private String state;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "residence_type")
    private String residenceType;

    @Column(name = "assembly_constituency_number")
    private String assemblyConstituencyNumber;

    @Column(name = "polling_booth_number")
    private String pollingBoothNumber;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "location_link_on_google_map")
    private String locationLinkOnGoogleMap;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "google_address")
    private String googleAddress;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(unique = true)
    private KsrsacAddress ksrsacAddress;

    @ManyToOne
    @JsonIgnoreProperties(value = "addresses", allowSetters = true)
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Address type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationType() {
        return locationType;
    }

    public Address locationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getPincode() {
        return pincode;
    }

    public Address pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Address houseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBuilding() {
        return building;
    }

    public Address building(String building) {
        this.building = building;
        return this;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLocality() {
        return locality;
    }

    public Address locality(String locality) {
        this.locality = locality;
        return this;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public Address district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public Address taluka(String taluka) {
        this.taluka = taluka;
        return this;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getGramPanchayat() {
        return gramPanchayat;
    }

    public Address gramPanchayat(String gramPanchayat) {
        this.gramPanchayat = gramPanchayat;
        return this;
    }

    public void setGramPanchayat(String gramPanchayat) {
        this.gramPanchayat = gramPanchayat;
    }

    public String getVillage() {
        return village;
    }

    public Address village(String village) {
        this.village = village;
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public Address cityOrTown(String cityOrTown) {
        this.cityOrTown = cityOrTown;
        return this;
    }

    public void setCityOrTown(String cityOrTown) {
        this.cityOrTown = cityOrTown;
    }

    public String getWard() {
        return ward;
    }

    public Address ward(String ward) {
        this.ward = ward;
        return this;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getZone() {
        return zone;
    }

    public Address zone(String zone) {
        this.zone = zone;
        return this;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getState() {
        return state;
    }

    public Address state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public Address landmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public Address residenceType(String residenceType) {
        this.residenceType = residenceType;
        return this;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getAssemblyConstituencyNumber() {
        return assemblyConstituencyNumber;
    }

    public Address assemblyConstituencyNumber(String assemblyConstituencyNumber) {
        this.assemblyConstituencyNumber = assemblyConstituencyNumber;
        return this;
    }

    public void setAssemblyConstituencyNumber(String assemblyConstituencyNumber) {
        this.assemblyConstituencyNumber = assemblyConstituencyNumber;
    }

    public String getPollingBoothNumber() {
        return pollingBoothNumber;
    }

    public Address pollingBoothNumber(String pollingBoothNumber) {
        this.pollingBoothNumber = pollingBoothNumber;
        return this;
    }

    public void setPollingBoothNumber(String pollingBoothNumber) {
        this.pollingBoothNumber = pollingBoothNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public Address latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Address longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocationLinkOnGoogleMap() {
        return locationLinkOnGoogleMap;
    }

    public Address locationLinkOnGoogleMap(String locationLinkOnGoogleMap) {
        this.locationLinkOnGoogleMap = locationLinkOnGoogleMap;
        return this;
    }

    public void setLocationLinkOnGoogleMap(String locationLinkOnGoogleMap) {
        this.locationLinkOnGoogleMap = locationLinkOnGoogleMap;
    }

    public String getGoogleAddress() {
        return googleAddress;
    }

    public Address googleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
        return this;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getStatus() {
        return status;
    }

    public Address status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KsrsacAddress getKsrsacAddress() {
        return ksrsacAddress;
    }

    public Address ksrsacAddress(KsrsacAddress ksrsacAddress) {
        this.ksrsacAddress = ksrsacAddress;
        return this;
    }

    public void setKsrsacAddress(KsrsacAddress ksrsacAddress) {
        this.ksrsacAddress = ksrsacAddress;
    }

    public Person getPerson() {
        return person;
    }

    public Address person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", locationType='" + getLocationType() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", building='" + getBuilding() + "'" +
            ", locality='" + getLocality() + "'" +
            ", street='" + getStreet() + "'" +
            ", district='" + getDistrict() + "'" +
            ", taluka='" + getTaluka() + "'" +
            ", gramPanchayat='" + getGramPanchayat() + "'" +
            ", village='" + getVillage() + "'" +
            ", cityOrTown='" + getCityOrTown() + "'" +
            ", ward='" + getWard() + "'" +
            ", zone='" + getZone() + "'" +
            ", state='" + getState() + "'" +
            ", landmark='" + getLandmark() + "'" +
            ", residenceType='" + getResidenceType() + "'" +
            ", assemblyConstituencyNumber='" + getAssemblyConstituencyNumber() + "'" +
            ", pollingBoothNumber='" + getPollingBoothNumber() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", locationLinkOnGoogleMap='" + getLocationLinkOnGoogleMap() + "'" +
            ", googleAddress='" + getGoogleAddress() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
