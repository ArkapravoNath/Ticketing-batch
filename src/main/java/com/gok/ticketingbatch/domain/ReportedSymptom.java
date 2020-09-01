package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ReportedSymptom.
 */
@Entity
@Table(name = "reported_symptom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportedSymptom extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "communication_mode")
    private String communicationMode;

    @Column(name = "fever")
    private String fever;

    @Column(name = "temperature_captured")
    private String temperatureCaptured;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "cough")
    private String cough;

    @Column(name = "cold")
    private String cold;

    @Column(name = "breathlessness")
    private String breathlessness;

    @Column(name = "contacted_another_patient")
    private String contactedAnotherPatient;

    @Column(name = "visit_crowded_place")
    private String visitCrowdedPlace;

    @Column(name = "score")
    private String score;

    @Column(name = "otc_medicines")
    private String otcMedicines;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "any_other_symptom")
    private String anyOtherSymptom;

    @Column(name = "ehr_record_id")
    private String ehrRecordId;

    @Column(name = "record_created_date")
    private Instant recordCreatedDate;

    @Column(name = "data_captured_from")
    private String dataCapturedFrom;

    @Column(name = "diarrhea")
    private String diarrhea;

    @Column(name = "loss_of_taste_or_smell")
    private String lossOfTasteOrSmell;

    @Column(name = "sore_throat")
    private String soreThroat;

    @Column(name = "migrated")
    private String migrated;

    @Column(name = "contacted_covid_positive_related_patient")
    private String contactedCovidPositiveRelatedPatient;

    @ManyToOne
    @JsonIgnoreProperties(value = "reportedSymptoms", allowSetters = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunicationMode() {
        return communicationMode;
    }

    public ReportedSymptom communicationMode(String communicationMode) {
        this.communicationMode = communicationMode;
        return this;
    }

    public void setCommunicationMode(String communicationMode) {
        this.communicationMode = communicationMode;
    }

    public String getFever() {
        return fever;
    }

    public ReportedSymptom fever(String fever) {
        this.fever = fever;
        return this;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getTemperatureCaptured() {
        return temperatureCaptured;
    }

    public ReportedSymptom temperatureCaptured(String temperatureCaptured) {
        this.temperatureCaptured = temperatureCaptured;
        return this;
    }

    public void setTemperatureCaptured(String temperatureCaptured) {
        this.temperatureCaptured = temperatureCaptured;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public ReportedSymptom bloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
        return this;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getCough() {
        return cough;
    }

    public ReportedSymptom cough(String cough) {
        this.cough = cough;
        return this;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getCold() {
        return cold;
    }

    public ReportedSymptom cold(String cold) {
        this.cold = cold;
        return this;
    }

    public void setCold(String cold) {
        this.cold = cold;
    }

    public String getBreathlessness() {
        return breathlessness;
    }

    public ReportedSymptom breathlessness(String breathlessness) {
        this.breathlessness = breathlessness;
        return this;
    }

    public void setBreathlessness(String breathlessness) {
        this.breathlessness = breathlessness;
    }

    public String getContactedAnotherPatient() {
        return contactedAnotherPatient;
    }

    public ReportedSymptom contactedAnotherPatient(String contactedAnotherPatient) {
        this.contactedAnotherPatient = contactedAnotherPatient;
        return this;
    }

    public void setContactedAnotherPatient(String contactedAnotherPatient) {
        this.contactedAnotherPatient = contactedAnotherPatient;
    }

    public String getVisitCrowdedPlace() {
        return visitCrowdedPlace;
    }

    public ReportedSymptom visitCrowdedPlace(String visitCrowdedPlace) {
        this.visitCrowdedPlace = visitCrowdedPlace;
        return this;
    }

    public void setVisitCrowdedPlace(String visitCrowdedPlace) {
        this.visitCrowdedPlace = visitCrowdedPlace;
    }

    public String getScore() {
        return score;
    }

    public ReportedSymptom score(String score) {
        this.score = score;
        return this;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOtcMedicines() {
        return otcMedicines;
    }

    public ReportedSymptom otcMedicines(String otcMedicines) {
        this.otcMedicines = otcMedicines;
        return this;
    }

    public void setOtcMedicines(String otcMedicines) {
        this.otcMedicines = otcMedicines;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public ReportedSymptom riskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAnyOtherSymptom() {
        return anyOtherSymptom;
    }

    public ReportedSymptom anyOtherSymptom(String anyOtherSymptom) {
        this.anyOtherSymptom = anyOtherSymptom;
        return this;
    }

    public void setAnyOtherSymptom(String anyOtherSymptom) {
        this.anyOtherSymptom = anyOtherSymptom;
    }

    public String getEhrRecordId() {
        return ehrRecordId;
    }

    public ReportedSymptom ehrRecordId(String ehrRecordId) {
        this.ehrRecordId = ehrRecordId;
        return this;
    }

    public void setEhrRecordId(String ehrRecordId) {
        this.ehrRecordId = ehrRecordId;
    }

    public Instant getRecordCreatedDate() {
        return recordCreatedDate;
    }

    public ReportedSymptom recordCreatedDate(Instant recordCreatedDate) {
        this.recordCreatedDate = recordCreatedDate;
        return this;
    }

    public void setRecordCreatedDate(Instant recordCreatedDate) {
        this.recordCreatedDate = recordCreatedDate;
    }

    public String getDataCapturedFrom() {
        return dataCapturedFrom;
    }

    public ReportedSymptom dataCapturedFrom(String dataCapturedFrom) {
        this.dataCapturedFrom = dataCapturedFrom;
        return this;
    }

    public void setDataCapturedFrom(String dataCapturedFrom) {
        this.dataCapturedFrom = dataCapturedFrom;
    }

    public String getDiarrhea() {
        return diarrhea;
    }

    public ReportedSymptom diarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
        return this;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getLossOfTasteOrSmell() {
        return lossOfTasteOrSmell;
    }

    public ReportedSymptom lossOfTasteOrSmell(String lossOfTasteOrSmell) {
        this.lossOfTasteOrSmell = lossOfTasteOrSmell;
        return this;
    }

    public void setLossOfTasteOrSmell(String lossOfTasteOrSmell) {
        this.lossOfTasteOrSmell = lossOfTasteOrSmell;
    }

    public String getSoreThroat() {
        return soreThroat;
    }

    public ReportedSymptom soreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
        return this;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    public String getMigrated() {
        return migrated;
    }

    public ReportedSymptom migrated(String migrated) {
        this.migrated = migrated;
        return this;
    }

    public void setMigrated(String migrated) {
        this.migrated = migrated;
    }

    public String getContactedCovidPositiveRelatedPatient() {
        return contactedCovidPositiveRelatedPatient;
    }

    public ReportedSymptom contactedCovidPositiveRelatedPatient(String contactedCovidPositiveRelatedPatient) {
        this.contactedCovidPositiveRelatedPatient = contactedCovidPositiveRelatedPatient;
        return this;
    }

    public void setContactedCovidPositiveRelatedPatient(String contactedCovidPositiveRelatedPatient) {
        this.contactedCovidPositiveRelatedPatient = contactedCovidPositiveRelatedPatient;
    }

    public Patient getPatient() {
        return patient;
    }

    public ReportedSymptom patient(Patient patient) {
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
        if (!(o instanceof ReportedSymptom)) {
            return false;
        }
        return id != null && id.equals(((ReportedSymptom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportedSymptom{" +
            "id=" + getId() +
            ", communicationMode='" + getCommunicationMode() + "'" +
            ", fever='" + getFever() + "'" +
            ", temperatureCaptured='" + getTemperatureCaptured() + "'" +
            ", bloodPressure='" + getBloodPressure() + "'" +
            ", cough='" + getCough() + "'" +
            ", cold='" + getCold() + "'" +
            ", breathlessness='" + getBreathlessness() + "'" +
            ", contactedAnotherPatient='" + getContactedAnotherPatient() + "'" +
            ", visitCrowdedPlace='" + getVisitCrowdedPlace() + "'" +
            ", score='" + getScore() + "'" +
            ", otcMedicines='" + getOtcMedicines() + "'" +
            ", riskLevel='" + getRiskLevel() + "'" +
            ", anyOtherSymptom='" + getAnyOtherSymptom() + "'" +
            ", ehrRecordId='" + getEhrRecordId() + "'" +
            ", recordCreatedDate='" + getRecordCreatedDate() + "'" +
            ", dataCapturedFrom='" + getDataCapturedFrom() + "'" +
            ", diarrhea='" + getDiarrhea() + "'" +
            ", lossOfTasteOrSmell='" + getLossOfTasteOrSmell() + "'" +
            ", soreThroat='" + getSoreThroat() + "'" +
            ", migrated='" + getMigrated() + "'" +
            ", contactedCovidPositiveRelatedPatient='" + getContactedCovidPositiveRelatedPatient() + "'" +
            "}";
    }
}
