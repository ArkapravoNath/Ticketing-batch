package com.gok.ticketingbatch.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.ReportedSymptom} entity.
 */
public class ReportedSymptomDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String communicationMode;

    private String fever;

    private String temperatureCaptured;

    private String bloodPressure;

    private String cough;

    private String cold;

    private String breathlessness;

    private String contactedAnotherPatient;

    private String visitCrowdedPlace;

    private String score;

    private String otcMedicines;

    private String riskLevel;

    private String anyOtherSymptom;

    private String ehrRecordId;

    private Instant recordCreatedDate;

    private String dataCapturedFrom;

    private String diarrhea;

    private String lossOfTasteOrSmell;

    private String soreThroat;

    private String migrated;

    private String contactedCovidPositiveRelatedPatient;


    private Long patientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommunicationMode() {
        return communicationMode;
    }

    public void setCommunicationMode(String communicationMode) {
        this.communicationMode = communicationMode;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getTemperatureCaptured() {
        return temperatureCaptured;
    }

    public void setTemperatureCaptured(String temperatureCaptured) {
        this.temperatureCaptured = temperatureCaptured;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getCold() {
        return cold;
    }

    public void setCold(String cold) {
        this.cold = cold;
    }

    public String getBreathlessness() {
        return breathlessness;
    }

    public void setBreathlessness(String breathlessness) {
        this.breathlessness = breathlessness;
    }

    public String getContactedAnotherPatient() {
        return contactedAnotherPatient;
    }

    public void setContactedAnotherPatient(String contactedAnotherPatient) {
        this.contactedAnotherPatient = contactedAnotherPatient;
    }

    public String getVisitCrowdedPlace() {
        return visitCrowdedPlace;
    }

    public void setVisitCrowdedPlace(String visitCrowdedPlace) {
        this.visitCrowdedPlace = visitCrowdedPlace;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOtcMedicines() {
        return otcMedicines;
    }

    public void setOtcMedicines(String otcMedicines) {
        this.otcMedicines = otcMedicines;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getAnyOtherSymptom() {
        return anyOtherSymptom;
    }

    public void setAnyOtherSymptom(String anyOtherSymptom) {
        this.anyOtherSymptom = anyOtherSymptom;
    }

    public String getEhrRecordId() {
        return ehrRecordId;
    }

    public void setEhrRecordId(String ehrRecordId) {
        this.ehrRecordId = ehrRecordId;
    }

    public Instant getRecordCreatedDate() {
        return recordCreatedDate;
    }

    public void setRecordCreatedDate(Instant recordCreatedDate) {
        this.recordCreatedDate = recordCreatedDate;
    }

    public String getDataCapturedFrom() {
        return dataCapturedFrom;
    }

    public void setDataCapturedFrom(String dataCapturedFrom) {
        this.dataCapturedFrom = dataCapturedFrom;
    }

    public String getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }

    public String getLossOfTasteOrSmell() {
        return lossOfTasteOrSmell;
    }

    public void setLossOfTasteOrSmell(String lossOfTasteOrSmell) {
        this.lossOfTasteOrSmell = lossOfTasteOrSmell;
    }

    public String getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(String soreThroat) {
        this.soreThroat = soreThroat;
    }

    public String getMigrated() {
        return migrated;
    }

    public void setMigrated(String migrated) {
        this.migrated = migrated;
    }

    public String getContactedCovidPositiveRelatedPatient() {
        return contactedCovidPositiveRelatedPatient;
    }

    public void setContactedCovidPositiveRelatedPatient(String contactedCovidPositiveRelatedPatient) {
        this.contactedCovidPositiveRelatedPatient = contactedCovidPositiveRelatedPatient;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportedSymptomDTO)) {
            return false;
        }

        return id != null && id.equals(((ReportedSymptomDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportedSymptomDTO{" +
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
            ", patientId=" + getPatientId() +
            "}";
    }
}
