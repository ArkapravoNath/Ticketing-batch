package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A AmbulanceActivity.
 */
@Entity
@Table(name = "ambulance_activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AmbulanceActivity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ambulance_number")
    private String ambulanceNumber;

    @Column(name = "ambulance_type")
    private String ambulanceType;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_primary_telephone_no")
    private String driverPrimaryTelephoneNo;

    @Column(name = "driver_secondary_telephone_no")
    private String driverSecondaryTelephoneNo;

    @Column(name = "profession")
    private String profession;

    @Column(name = "origin_address")
    private String originAddress;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "ambulance_dispatch_flag")
    private Boolean ambulanceDispatchFlag;

    @Column(name = "pickup_date_time")
    private Instant pickupDateTime;

    @Column(name = "drop_date_time")
    private Instant dropDateTime;

    @Column(name = "facility_provider_id_ref")
    private Long facilityProviderIdRef;

    @Column(name = "ambulance_service_id_ref")
    private Long ambulanceServiceIdRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "ambulanceActivities", allowSetters = true)
    private PatientActivity patientActivity;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public AmbulanceActivity ambulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
        return this;
    }

    public void setAmbulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public AmbulanceActivity ambulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
        return this;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }

    public String getDriverName() {
        return driverName;
    }

    public AmbulanceActivity driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPrimaryTelephoneNo() {
        return driverPrimaryTelephoneNo;
    }

    public AmbulanceActivity driverPrimaryTelephoneNo(String driverPrimaryTelephoneNo) {
        this.driverPrimaryTelephoneNo = driverPrimaryTelephoneNo;
        return this;
    }

    public void setDriverPrimaryTelephoneNo(String driverPrimaryTelephoneNo) {
        this.driverPrimaryTelephoneNo = driverPrimaryTelephoneNo;
    }

    public String getDriverSecondaryTelephoneNo() {
        return driverSecondaryTelephoneNo;
    }

    public AmbulanceActivity driverSecondaryTelephoneNo(String driverSecondaryTelephoneNo) {
        this.driverSecondaryTelephoneNo = driverSecondaryTelephoneNo;
        return this;
    }

    public void setDriverSecondaryTelephoneNo(String driverSecondaryTelephoneNo) {
        this.driverSecondaryTelephoneNo = driverSecondaryTelephoneNo;
    }

    public String getProfession() {
        return profession;
    }

    public AmbulanceActivity profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public AmbulanceActivity originAddress(String originAddress) {
        this.originAddress = originAddress;
        return this;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public AmbulanceActivity destinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Boolean isAmbulanceDispatchFlag() {
        return ambulanceDispatchFlag;
    }

    public AmbulanceActivity ambulanceDispatchFlag(Boolean ambulanceDispatchFlag) {
        this.ambulanceDispatchFlag = ambulanceDispatchFlag;
        return this;
    }

    public void setAmbulanceDispatchFlag(Boolean ambulanceDispatchFlag) {
        this.ambulanceDispatchFlag = ambulanceDispatchFlag;
    }

    public Instant getPickupDateTime() {
        return pickupDateTime;
    }

    public AmbulanceActivity pickupDateTime(Instant pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
        return this;
    }

    public void setPickupDateTime(Instant pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public Instant getDropDateTime() {
        return dropDateTime;
    }

    public AmbulanceActivity dropDateTime(Instant dropDateTime) {
        this.dropDateTime = dropDateTime;
        return this;
    }

    public void setDropDateTime(Instant dropDateTime) {
        this.dropDateTime = dropDateTime;
    }

    public Long getFacilityProviderIdRef() {
        return facilityProviderIdRef;
    }

    public AmbulanceActivity facilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
        return this;
    }

    public void setFacilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
    }

    public Long getAmbulanceServiceIdRef() {
        return ambulanceServiceIdRef;
    }

    public AmbulanceActivity ambulanceServiceIdRef(Long ambulanceServiceIdRef) {
        this.ambulanceServiceIdRef = ambulanceServiceIdRef;
        return this;
    }

    public void setAmbulanceServiceIdRef(Long ambulanceServiceIdRef) {
        this.ambulanceServiceIdRef = ambulanceServiceIdRef;
    }

    public PatientActivity getPatientActivity() {
        return patientActivity;
    }

    public AmbulanceActivity patientActivity(PatientActivity patientActivity) {
        this.patientActivity = patientActivity;
        return this;
    }

    public void setPatientActivity(PatientActivity patientActivity) {
        this.patientActivity = patientActivity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AmbulanceActivity)) {
            return false;
        }
        return id != null && id.equals(((AmbulanceActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AmbulanceActivity{" +
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
            "}";
    }
}
