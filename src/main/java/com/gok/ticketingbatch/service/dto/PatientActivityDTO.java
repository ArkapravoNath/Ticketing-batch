package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.PatientActivity} entity.
 */
public class PatientActivityDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String activityType;

    private String activityRemark;

    private String status;

    private String riskLevel;

    private Long personIdRef;

    private Long currentAddressIdRef;


    private Long patientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityRemark() {
        return activityRemark;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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
        if (!(o instanceof PatientActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((PatientActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientActivityDTO{" +
            "id=" + getId() +
            ", activityType='" + getActivityType() + "'" +
            ", activityRemark='" + getActivityRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", riskLevel='" + getRiskLevel() + "'" +
            ", personIdRef=" + getPersonIdRef() +
            ", currentAddressIdRef=" + getCurrentAddressIdRef() +
            ", patientId=" + getPatientId() +
            "}";
    }
}
