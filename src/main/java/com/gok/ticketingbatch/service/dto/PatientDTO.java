package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Patient} entity.
 */
public class PatientDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String icmrReference;

    private String statePid;

    private String districtPid;

    private String respiratoryComplication;

    private String cardiovascularDisease;

    private String diabetes;

    private String bloodPressure;

    private String otherLongTermDesease;

    private Long personIdRef;

    private Long currentAddressIdRef;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcmrReference() {
        return icmrReference;
    }

    public void setIcmrReference(String icmrReference) {
        this.icmrReference = icmrReference;
    }

    public String getStatePid() {
        return statePid;
    }

    public void setStatePid(String statePid) {
        this.statePid = statePid;
    }

    public String getDistrictPid() {
        return districtPid;
    }

    public void setDistrictPid(String districtPid) {
        this.districtPid = districtPid;
    }

    public String getRespiratoryComplication() {
        return respiratoryComplication;
    }

    public void setRespiratoryComplication(String respiratoryComplication) {
        this.respiratoryComplication = respiratoryComplication;
    }

    public String getCardiovascularDisease() {
        return cardiovascularDisease;
    }

    public void setCardiovascularDisease(String cardiovascularDisease) {
        this.cardiovascularDisease = cardiovascularDisease;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getOtherLongTermDesease() {
        return otherLongTermDesease;
    }

    public void setOtherLongTermDesease(String otherLongTermDesease) {
        this.otherLongTermDesease = otherLongTermDesease;
    }

    public Long getPersonIdRef() {
        return personIdRef;
    }

    public void setPersonIdRef(Long personIdRef) {
        this.personIdRef = personIdRef;
    }

    public Long getCurrentAddressIdRef() {
        return currentAddressIdRef;
    }

    public void setCurrentAddressIdRef(Long currentAddressIdRef) {
        this.currentAddressIdRef = currentAddressIdRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDTO)) {
            return false;
        }

        return id != null && id.equals(((PatientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDTO{" +
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
