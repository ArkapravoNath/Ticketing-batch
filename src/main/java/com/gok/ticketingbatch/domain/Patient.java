package com.gok.ticketingbatch.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patient extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "icmr_reference")
    private String icmrReference;

    @Column(name = "state_pid")
    private String statePid;

    @Column(name = "district_pid")
    private String districtPid;

    @Column(name = "respiratory_complication")
    private String respiratoryComplication;

    @Column(name = "cardiovascular_disease")
    private String cardiovascularDisease;

    @Column(name = "diabetes")
    private String diabetes;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "other_long_term_desease")
    private String otherLongTermDesease;

    @Column(name = "person_id_ref")
    private Long personIdRef;

    @Column(name = "current_address_id_ref")
    private Long currentAddressIdRef;

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MedicalHistory> medicalHistories = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PatientActivity> patientActivities = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ReportedSymptom> reportedSymptoms = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcmrReference() {
        return icmrReference;
    }

    public Patient icmrReference(String icmrReference) {
        this.icmrReference = icmrReference;
        return this;
    }

    public void setIcmrReference(String icmrReference) {
        this.icmrReference = icmrReference;
    }

    public String getStatePid() {
        return statePid;
    }

    public Patient statePid(String statePid) {
        this.statePid = statePid;
        return this;
    }

    public void setStatePid(String statePid) {
        this.statePid = statePid;
    }

    public String getDistrictPid() {
        return districtPid;
    }

    public Patient districtPid(String districtPid) {
        this.districtPid = districtPid;
        return this;
    }

    public void setDistrictPid(String districtPid) {
        this.districtPid = districtPid;
    }

    public String getRespiratoryComplication() {
        return respiratoryComplication;
    }

    public Patient respiratoryComplication(String respiratoryComplication) {
        this.respiratoryComplication = respiratoryComplication;
        return this;
    }

    public void setRespiratoryComplication(String respiratoryComplication) {
        this.respiratoryComplication = respiratoryComplication;
    }

    public String getCardiovascularDisease() {
        return cardiovascularDisease;
    }

    public Patient cardiovascularDisease(String cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
        return this;
    }

    public void setCardiovascularDisease(String cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public Patient diabetes(String diabetes) {
        this.diabetes = diabetes;
        return this;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public Patient bloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
        return this;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getOtherLongTermDesease() {
        return otherLongTermDesease;
    }

    public Patient otherLongTermDesease(String otherLongTermDesease) {
        this.otherLongTermDesease = otherLongTermDesease;
        return this;
    }

    public void setOtherLongTermDesease(String otherLongTermDesease) {
        this.otherLongTermDesease = otherLongTermDesease;
    }

    public Long getPersonIdRef() {
        return personIdRef;
    }

    public Patient personIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
        return this;
    }

    public void setPersonIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
    }

    public Long getCurrentAddressIdRef() {
        return currentAddressIdRef;
    }

    public Patient currentAddressIdRef(Long currentAddressIdRef) {
        this.currentAddressIdRef = currentAddressIdRef;
        return this;
    }

    public void setCurrentAddressIdRef(Long currentAddressIdRef) {
        this.currentAddressIdRef = currentAddressIdRef;
    }

    public Set<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public Patient medicalHistories(Set<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
        return this;
    }

    public Patient addMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistories.add(medicalHistory);
        medicalHistory.setPatient(this);
        return this;
    }

    public Patient removeMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistories.remove(medicalHistory);
        medicalHistory.setPatient(null);
        return this;
    }

    public void setMedicalHistories(Set<MedicalHistory> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }

    public Set<PatientActivity> getPatientActivities() {
        return patientActivities;
    }

    public Patient patientActivities(Set<PatientActivity> patientActivities) {
        this.patientActivities = patientActivities;
        return this;
    }

    public Patient addPatientActivity(PatientActivity patientActivity) {
        this.patientActivities.add(patientActivity);
        patientActivity.setPatient(this);
        return this;
    }

    public Patient removePatientActivity(PatientActivity patientActivity) {
        this.patientActivities.remove(patientActivity);
        patientActivity.setPatient(null);
        return this;
    }

    public void setPatientActivities(Set<PatientActivity> patientActivities) {
        this.patientActivities = patientActivities;
    }

    public Set<ReportedSymptom> getReportedSymptoms() {
        return reportedSymptoms;
    }

    public Patient reportedSymptoms(Set<ReportedSymptom> reportedSymptoms) {
        this.reportedSymptoms = reportedSymptoms;
        return this;
    }

    public Patient addReportedSymptom(ReportedSymptom reportedSymptom) {
        this.reportedSymptoms.add(reportedSymptom);
        reportedSymptom.setPatient(this);
        return this;
    }

    public Patient removeReportedSymptom(ReportedSymptom reportedSymptom) {
        this.reportedSymptoms.remove(reportedSymptom);
        reportedSymptom.setPatient(null);
        return this;
    }

    public void setReportedSymptoms(Set<ReportedSymptom> reportedSymptoms) {
        this.reportedSymptoms = reportedSymptoms;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", icmrReference='" + getIcmrReference() + "'" +
            ", statePid='" + getStatePid() + "'" +
            ", districtPid='" + getDistrictPid() + "'" +
            ", respiratoryComplication='" + getRespiratoryComplication() + "'" +
            ", cardiovascularDisease='" + getCardiovascularDisease() + "'" +
            ", diabetes='" + getDiabetes() + "'" +
            ", bloodPressure='" + getBloodPressure() + "'" +
            ", otherLongTermDesease='" + getOtherLongTermDesease() + "'" +
            ", personIdRef=" + getPersonIdRef() +
            ", currentAddressIdRef=" + getCurrentAddressIdRef() +
            "}";
    }
}
