package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Address} entity.
 */
public class AddressDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String type;

    private String locationType;

    private String pincode;

    private String houseNumber;

    private String building;

    private String locality;

    private String street;

    private String district;

    private String taluka;

    private String gramPanchayat;

    private String village;

    private String cityOrTown;

    private String ward;

    private String zone;

    private String state;

    private String landmark;

    private String residenceType;

    private String assemblyConstituencyNumber;

    private String pollingBoothNumber;

    private String latitude;

    private String longitude;

    @Lob
    private String locationLinkOnGoogleMap;

    @Lob
    private String googleAddress;

    private String status;


    private Long ksrsacAddressId;

    private Long personId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getGramPanchayat() {
        return gramPanchayat;
    }

    public void setGramPanchayat(String gramPanchayat) {
        this.gramPanchayat = gramPanchayat;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public void setCityOrTown(String cityOrTown) {
        this.cityOrTown = cityOrTown;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getAssemblyConstituencyNumber() {
        return assemblyConstituencyNumber;
    }

    public void setAssemblyConstituencyNumber(String assemblyConstituencyNumber) {
        this.assemblyConstituencyNumber = assemblyConstituencyNumber;
    }

    public String getPollingBoothNumber() {
        return pollingBoothNumber;
    }

    public void setPollingBoothNumber(String pollingBoothNumber) {
        this.pollingBoothNumber = pollingBoothNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocationLinkOnGoogleMap() {
        return locationLinkOnGoogleMap;
    }

    public void setLocationLinkOnGoogleMap(String locationLinkOnGoogleMap) {
        this.locationLinkOnGoogleMap = locationLinkOnGoogleMap;
    }

    public String getGoogleAddress() {
        return googleAddress;
    }

    public void setGoogleAddress(String googleAddress) {
        this.googleAddress = googleAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getKsrsacAddressId() {
        return ksrsacAddressId;
    }

    public void setKsrsacAddressId(Long ksrsacAddressId) {
        this.ksrsacAddressId = ksrsacAddressId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }

        return id != null && id.equals(((AddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDTO{" +
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
            ", ksrsacAddressId=" + getKsrsacAddressId() +
            ", personId=" + getPersonId() +
            "}";
    }
}
