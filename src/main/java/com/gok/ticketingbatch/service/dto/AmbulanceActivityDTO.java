package com.gok.ticketingbatch.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.AmbulanceActivity} entity.
 */
public class AmbulanceActivityDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String ambulanceNumber;

    private String ambulanceType;

    private String driverName;

    private String driverPrimaryTelephoneNo;

    private String driverSecondaryTelephoneNo;

    private String profession;

    private String originAddress;

    private String destinationAddress;

    private Boolean ambulanceDispatchFlag;

    private Instant pickupDateTime;

    private Instant dropDateTime;

    private Long facilityProviderIdRef;

    private Long ambulanceServiceIdRef;


    private Long patientActivityId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPrimaryTelephoneNo() {
        return driverPrimaryTelephoneNo;
    }

    public void setDriverPrimaryTelephoneNo(String driverPrimaryTelephoneNo) {
        this.driverPrimaryTelephoneNo = driverPrimaryTelephoneNo;
    }

    public String getDriverSecondaryTelephoneNo() {
        return driverSecondaryTelephoneNo;
    }

    public void setDriverSecondaryTelephoneNo(String driverSecondaryTelephoneNo) {
        this.driverSecondaryTelephoneNo = driverSecondaryTelephoneNo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Boolean isAmbulanceDispatchFlag() {
        return ambulanceDispatchFlag;
    }

    public void setAmbulanceDispatchFlag(Boolean ambulanceDispatchFlag) {
        this.ambulanceDispatchFlag = ambulanceDispatchFlag;
    }

    public Instant getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(Instant pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public Instant getDropDateTime() {
        return dropDateTime;
    }

    public void setDropDateTime(Instant dropDateTime) {
        this.dropDateTime = dropDateTime;
    }

    public Long getFacilityProviderIdRef() {
        return facilityProviderIdRef;
    }

    public void setFacilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
    }

    public Long getAmbulanceServiceIdRef() {
        return ambulanceServiceIdRef;
    }

    public void setAmbulanceServiceIdRef(Long ambulanceServiceIdRef) {
        this.ambulanceServiceIdRef = ambulanceServiceIdRef;
    }

    public Long getPatientActivityId() {
        return patientActivityId;
    }

    public void setPatientActivityId(Long patientActivityId) {
        this.patientActivityId = patientActivityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AmbulanceActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((AmbulanceActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AmbulanceActivityDTO{" +
            "id=" + getId() +
            ", ambulanceNumber='" + getAmbulanceNumber() + "'" +
            ", ambulanceType='" + getAmbulanceType() + "'" +
            ", driverName='" + getDriverName() + "'" +
            ", driverPrimaryTelephoneNo='" + getDriverPrimaryTelephoneNo() + "'" +
            ", driverSecondaryTelephoneNo='" + getDriverSecondaryTelephoneNo() + "'" +
            ", profession='" + getProfession() + "'" +
            ", originAddress='" + getOriginAddress() + "'" +
            ", destinationAddress='" + getDestinationAddress() + "'" +
            ", ambulanceDispatchFlag='" + isAmbulanceDispatchFlag() + "'" +
            ", pickupDateTime='" + getPickupDateTime() + "'" +
            ", dropDateTime='" + getDropDateTime() + "'" +
            ", facilityProviderIdRef=" + getFacilityProviderIdRef() +
            ", ambulanceServiceIdRef=" + getAmbulanceServiceIdRef() +
            ", patientActivityId=" + getPatientActivityId() +
            "}";
    }
}
