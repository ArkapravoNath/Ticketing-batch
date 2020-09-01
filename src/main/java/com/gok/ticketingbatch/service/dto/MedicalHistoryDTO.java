package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.MedicalHistory} entity.
 */
public class MedicalHistoryDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String metadata;

    private String cancer;

    private String tbOrHiv;

    private String renalProblem;


    private Long patientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public String getTbOrHiv() {
        return tbOrHiv;
    }

    public void setTbOrHiv(String tbOrHiv) {
        this.tbOrHiv = tbOrHiv;
    }

    public String getRenalProblem() {
        return renalProblem;
    }

    public void setRenalProblem(String renalProblem) {
        this.renalProblem = renalProblem;
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
        if (!(o instanceof MedicalHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalHistoryDTO{" +
            "id=" + getId() +
            ", metadata='" + getMetadata() + "'" +
            ", cancer='" + getCancer() + "'" +
            ", tbOrHiv='" + getTbOrHiv() + "'" +
            ", renalProblem='" + getRenalProblem() + "'" +
            ", patientId=" + getPatientId() +
            "}";
    }
}
