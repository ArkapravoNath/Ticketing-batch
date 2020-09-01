package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PatientActivity.
 */
@Entity
@Table(name = "patient_activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PatientActivity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_remark")
    private String activityRemark;

    @Column(name = "status")
    private String status;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "person_id_ref")
    private Long personIdRef;

    @Column(name = "current_address_id_ref")
    private Long currentAddressIdRef;

    @OneToMany(mappedBy = "patientActivity")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AmbulanceActivity> ambulanceActivities = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "patientActivities", allowSetters = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public PatientActivity activityType(String activityType) {
        this.activityType = activityType;
        return this;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityRemark() {
        return activityRemark;
    }

    public PatientActivity activityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
        return this;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public String getStatus() {
        return status;
    }

    public PatientActivity status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public PatientActivity riskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Long getPersonIdRef() {
        return personIdRef;
    }

    public PatientActivity personIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
        return this;
    }

    public void setPersonIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
    }

    public Long getCurrentAddressIdRef() {
        return currentAddressIdRef;
    }

    public PatientActivity currentAddressIdRef(Long currentAddressIdRef) {
        this.currentAddressIdRef = currentAddressIdRef;
        return this;
    }

    public void setCurrentAddressIdRef(Long currentAddressIdRef) {
        this.currentAddressIdRef = currentAddressIdRef;
    }

    public Set<AmbulanceActivity> getAmbulanceActivities() {
        return ambulanceActivities;
    }

    public PatientActivity ambulanceActivities(Set<AmbulanceActivity> ambulanceActivities) {
        this.ambulanceActivities = ambulanceActivities;
        return this;
    }

    public PatientActivity addAmbulanceActivity(AmbulanceActivity ambulanceActivity) {
        this.ambulanceActivities.add(ambulanceActivity);
        ambulanceActivity.setPatientActivity(this);
        return this;
    }

    public PatientActivity removeAmbulanceActivity(AmbulanceActivity ambulanceActivity) {
        this.ambulanceActivities.remove(ambulanceActivity);
        ambulanceActivity.setPatientActivity(null);
        return this;
    }

    public void setAmbulanceActivities(Set<AmbulanceActivity> ambulanceActivities) {
        this.ambulanceActivities = ambulanceActivities;
    }

    public Patient getPatient() {
        return patient;
    }

    public PatientActivity patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientActivity)) {
            return false;
        }
        return id != null && id.equals(((PatientActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientActivity{" +
            "id=" + getId() +
            ", activityType='" + getActivityType() + "'" +
            ", activityRemark='" + getActivityRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", riskLevel='" + getRiskLevel() + "'" +
            ", personIdRef=" + getPersonIdRef() +
            ", currentAddressIdRef=" + getCurrentAddressIdRef() +
            "}";
    }
}
